package hgrx.controller;

import hgrx.bean.*;
import hgrx.dto.ArticleDetailVO;
import hgrx.dto.LoginDTO;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import hgrx.util.RegexUtils;
import hgrx.util.VerifyCodeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by HGRX on 2017/5/14
 */
@Controller
public class AdminController {

    Log log = LogFactory.getLog(this.getClass());

    final
    AdminService adminService;

    final BaseService baseService;

    @Autowired
    public AdminController(AdminService adminService, BaseService baseService) {
        this.baseService = baseService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "registerHandle", method = RequestMethod.POST)
    public String handleRegister(User user, HttpSession session, Model model) {

        //校验参数
        if (!verifyUser(user) || adminService.addUser(user)) {
            model.addAttribute("msg", "注册信息不符合规范,请检查后重新输入");
            return "error";
        }

        log.info("新注册了用户:" + user);
        session.setAttribute("user", user);
        return "redirect:/square/new";
    }

    private boolean verifyUser(User user) {
        return (!RegexUtils.isUsername(user.getUsername()) || !RegexUtils.isPassword(user.getPassword())
                || !RegexUtils.isEmail(user.getEmail()) || !RegexUtils.isNickname(user.getNickname()));
    }


    @RequestMapping(value = "autoLogin", method = RequestMethod.GET)
    public String autoLogin(HttpSession session) {
        User u = adminService.getUserByUsername("hgrx");
        session.setAttribute("user", u);
        return "redirect:/admin/center";
    }

    @RequestMapping(value = "loginVerify", method = RequestMethod.GET)
    @ResponseBody
    public Map getYzm(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        String loginVerifyCodeId = UUID.randomUUID().toString();
        session.setAttribute("loginVerifyCodeId", loginVerifyCodeId);
        session.setAttribute("loginVerifyCode", verifyCode.toLowerCase());
        //生成图片
        int w = 146, h = 33;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, byteArrayOutputStream, verifyCode);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String base64Code = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        Map<String, Object> map = new HashMap<>();
        map.put("base64Code", "data:image/jpeg;base64," + base64Code);
        map.put("loginVerifyCodeId", loginVerifyCodeId);
        return map;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "loginHandle", method = RequestMethod.POST)
    public String loginHandle(LoginDTO loginDTO, Model model, HttpSession session) {

        String verifyCode = (String) session.getAttribute("loginVerifyCode");
        String verifyCodeId = (String) session.getAttribute("loginVerifyCodeId");
        if (!loginDTO.getVerify().toLowerCase().equals(verifyCode)) {
            model.addAttribute("msg", "验证码错误!");
            return "login";
        }

        User u = adminService.getUserByUsername(loginDTO.getUsername());
        if (u == null) {   //用户名不合规范和找不到用户名都是返回null
            model.addAttribute("msg", "用户不存在！");
            return "login";
        }
        if (checkPassword(loginDTO, u)) {
            model.addAttribute("msg", "密码错误！");
            return "login";
        }
        session.setAttribute("user", u);
        return "redirect:/square/new";
    }

    /**
     * 校验密码
     */
    private boolean checkPassword(LoginDTO loginDTO, User u) {
        return !u.getPassword().equals(DigestUtils.sha1Hex(loginDTO.getPassword() + loginDTO.getPassword().length()));
    }

    @RequestMapping(value = "admin/article/add", method = RequestMethod.GET)
    public String addArticle() {
        return "admin/add_article";
    }

    @RequestMapping(value = "admin/article/addHandle", method = RequestMethod.POST)
    public String addArticleHandle(Article article, String tags, HttpSession session, Model model) {
        //TODO 校验article,tags参数
        User user = getUser(session);
        article.init(user.getId());
        try {
            adminService.addArticle(article, tags);
        } catch (RuntimeException e) {
            e.printStackTrace();
            model.addAttribute("msg", "添加文章失败，请重试");
            return "error";
        }
        return "redirect:/square/new";
    }

    @RequestMapping(value = "admin/article/edit/{id}", method = RequestMethod.GET)
    public String editArticle(@PathVariable Long id, Model model, HttpSession session) {
        ArticleDetailVO advo = baseService.getAdvoById(id);
        // 先将tags存到session中,在提交表单上来的时候检查变动并做相应操作
        session.setAttribute("beforeEdit", advo.getTags());
        model.addAttribute("advo", advo);
        return "admin/article_edit";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "admin/article/editHandle", method = RequestMethod.POST)
    public String editArticleHandle(ArticleDetailVO advo, String tags, Boolean updateTimestamp, HttpSession session, Model model) {
        //TODO 校验article与tags参数
        // 因为id是从客户端过来的,需要加入session中的userId作限制条件以避免可以随意修改他人的文章
        advo.setUserId(getUser(session).getId());
        List<String> beforeEdit = (ArrayList) session.getAttribute("beforeEdit");
        adminService.updateArticle(advo, tags, updateTimestamp, beforeEdit);
        return "redirect:/admin/article/manage";
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }


    @RequestMapping(value = "admin/follow/add/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String addFollow(@PathVariable Long id, HttpSession session, HttpServletResponse response) {
        User user = getUser(session);
        boolean f = adminService.addFollow(new Follow(id, user.getId()));
        //TODO 用ajax发送请求
        return "" + f;
    }

    @RequestMapping(value = "admin/article/manage", method = RequestMethod.GET)
    public String articleManage(HttpSession session, Model model) {
        User user = getUser(session);
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(user.getId());
        model.addAttribute("advoList", advoList);
        //TODO 草稿可通过ajax修改
        return "admin/article_manage";
    }

    @RequestMapping(value = "admin/article/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String articleDelete(@PathVariable Long id, HttpSession session, Model model) {
        //TODO CSRF
        User user = getUser(session);
        Boolean f = adminService.deleteArticleByUserIdAndId(user.getId(), id);
        //TODO ajax操作,返回提示
        return "" + f;
    }

    @RequestMapping(value = "admin/center", method = RequestMethod.GET)
    public String mainCenter(HttpSession session, Model model) {
        User user = getUser(session);
        model.addAttribute("user", user);
        return "admin/main_center";
    }

    /**
     * 我的关注
     */
    @RequestMapping(value = "admin/following", method = RequestMethod.GET)
    public String following(HttpSession session, Model model) {
        User user = getUser(session);
        List<User> followingList = adminService.listFollowingListByUserId(user.getId());
        model.addAttribute("followingList", followingList);
        return "admin/following";
    }

    /**
     * 关注我的
     */
    @RequestMapping(value = "admin/follower", method = RequestMethod.GET)
    public String follower(HttpSession session, Model model) {
        User user = getUser(session);
        List<User> followerList = adminService.listFollowerListByUserId(user.getId());
        System.out.println(followerList);
        model.addAttribute("followerList", followerList);
        return "admin/follower";
    }

    @RequestMapping(value = "admin/star/list", method = RequestMethod.GET)
    public String listStar(HttpSession session, Model model) {
        User user = getUser(session);
        List<ArticleDetailVO> advoList = adminService.listStarArticleByUserId(user.getId());
        model.addAttribute("advoList", advoList);
        return "admin/star_page";
    }

    @RequestMapping(value = "admin/following/deleteHandle", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteFollowing(Long id, HttpSession session) {
        User user = getUser(session);
        Follow follow = new Follow(id, user.getId());
        return adminService.deleteFollowing(follow);
    }

    @RequestMapping(value = "admin/star/deleteHandle", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteStar(Long id, HttpSession session) {
        //TODO  CSRF
        User user = getUser(session);
        Star star = new Star(id, user.getId());
        return adminService.deleteStar(star);
    }

    @RequestMapping(value = "admin/star/addHandle", method = RequestMethod.POST)
    @ResponseBody
    public String addStar(Long id, HttpSession session) {
        //TODO ajax CSRF 状态检测?
        User user = getUser(session);
        Star star = new Star(id, user.getId());
        return adminService.addStar(star) + "";
    }

    @RequestMapping(value = "admin/like/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String addLike(@PathVariable Long id, HttpSession session) {
        //TODO ajax CSRF
        User user = getUser(session);
        Like like = new Like(id, user.getId());
        return adminService.addLike(like) + "";
    }

    @RequestMapping(value = "/admin/article/preview", method = RequestMethod.POST)
    public String previewArticle(@RequestParam String content,
                                 @RequestParam String title, Model model, HttpSession session) {
        User user = getUser(session);
        model.addAttribute("user", user);
        ArticleDetailVO advo = new ArticleDetailVO();
        System.out.println(content);
        advo.setTitle(title);
        // 避免替换\n
        String replaceStr = content.replaceAll("(?<!\\\\)\\\\n", "\n");
        //  前后各有一个 " 号
        advo.setContent(replaceStr.substring(1, replaceStr.length() - 1));
        advo.setTimestamp(System.currentTimeMillis());
        model.addAttribute("advo", advo);
        return "/admin/preview_article";
    }

}
