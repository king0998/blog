package hgrx.controller;

import hgrx.bean.Article;
import hgrx.bean.Follow;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("registerHandle")
    public String handleRegister(User user, HttpSession session) {
        //TODO 校验参数，暂时假设全部都是完美输入条件
        adminService.addUser(user);

        log.info("新注册了用户:" + user);
        session.setAttribute("user", user);
        return "forward:/square";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("autoLogin")
    public String autoLogin(HttpSession session) {
        User u = adminService.getUserByUsername("hgrx");
        session.setAttribute("user", u);
        return "redirect:/admin/center";
    }

    @RequestMapping("loginHandle")
    public String loginHandle(User user, Model model, HttpSession session) {

        User u = adminService.getUserByUsername(user.getUsername());
        if (u == null) {   //用户名不合规范和找不到用户名都是返回null
            model.addAttribute("msg", "用户不存在！");
            return "login";
        }
        if (!u.getPassword().equals(user.getPassword())) {
            model.addAttribute("msg", "密码错误！");
            return "login";
        }
        session.setAttribute("user", u);
        return "forward:/square";
    }

    @RequestMapping("admin/article/add")
    public String addArticle() {
        return "admin/add_article";
    }

    @RequestMapping("admin/article/addHandle")
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
        return "forward:/square";
    }

    @RequestMapping("admin/article/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        //TODO 查询文章详细内容并填充
        ArticleDetailVO advo = baseService.getAdvoById(id);
        model.addAttribute("advo", advo);
        return "admin/article_edit";
    }

    @RequestMapping("admin/article/editHandle")
    public String editArticleHandle(ArticleDetailVO advo, String tags, Boolean updateTimestamp, HttpSession session, Model model) {
        //TODO 校验article与tags参数
        // 因为id是从客户端过来的,需要加入session中的userId作限制条件以避免可以随意修改他人的文章
        advo.setUserId(getUser(session).getId());
        adminService.updateArticle(advo, tags, updateTimestamp);
        return "redirect:/admin/article/manage";
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }


    @RequestMapping("admin/follow/add/{id}")
    @ResponseBody
    public String addFollow(@PathVariable Long id, HttpSession session, HttpServletResponse response) {
        User user = getUser(session);
        boolean f = adminService.addFollow(new Follow(id, user.getId()));
        //TODO 用ajax发送请求
        return "" + f;
    }

    @RequestMapping("admin/article/manage")
    public String articleManage(HttpSession session, Model model) {
        User user = getUser(session);
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(user.getId());
        model.addAttribute("advoList", advoList);
        return "admin/article_manage";
    }

    @RequestMapping("admin/article/delete/{id}")
    @ResponseBody
    public String articleDelete(@PathVariable Long id, HttpSession session, Model model) {
        User user = getUser(session);
        Boolean f = adminService.deleteArticleByUserIdAndId(user.getId(), id);
        //TODO ajax操作,返回提示
        return "" + f;
    }

    @RequestMapping("admin/center")
    public String mainCenter(HttpSession session, Model model) {
        User user = getUser(session);
        model.addAttribute("user", user);
        return "admin/main_center";
    }

    /**
     * 我的关注
     */
    @RequestMapping("admin/following")
    public String following(HttpSession session, Model model) {
        User user = getUser(session);
        List<User> followingList = adminService.listFollowingListByUserId(user.getId());
        model.addAttribute("followingList", followingList);
        return "admin/following";
    }

    /**
     * 关注我的
     */
    @RequestMapping("admin/follower")
    public String follower(HttpSession session, Model model) {
        User user = getUser(session);
        List<User> followerList = adminService.listFollowerListByUserId(user.getId());
        System.out.println(followerList);
        model.addAttribute("followerList", followerList);
        return "admin/follower";
    }

    @RequestMapping("admin/star/list")
    public String listStar(HttpSession session,Model model){
        User user = getUser(session);
        List<ArticleDetailVO> advoList = adminService.listStarArticleByUserId(user.getId());
        model.addAttribute("advoList",advoList);
        return "admin/star_page";
    }


}
