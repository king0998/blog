package hgrx.controller;

import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by HGRX on 2017/6/1
 */
@Controller
public class SquareController {

    final
    BaseService baseService;

    final
    AdminService adminService;

    @Autowired
    public SquareController(BaseService baseService, AdminService adminService) {
        this.adminService = adminService;
        this.baseService = baseService;
    }

    @RequestMapping(value = {"square/new", "square"}, method = RequestMethod.GET)
    public String gotoSquare(HttpSession session, Model model) {

        // 只取最新20篇文章,且做缓存处理-,-
        List<ArticleDetailVO> advoList = baseService.listAllAdvo();
        model.addAttribute("advoList", advoList);

        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_new";
    }

    @RequestMapping("square/hot")
    public String hotArticle(Model model) {
        //同样只取20篇文章
        List<ArticleDetailVO> hotAdvoList = baseService.listHotAdvo();
        model.addAttribute("advoList", hotAdvoList);

        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_hot";
    }

    @RequestMapping("square/following")
    public String followingArticle(HttpSession session, Model model) {
        User user = getUser(session);
        if (user != null) {
            List<ArticleDetailVO> followAdvoList = baseService.listFollowingAdvoList(user.getId());
            model.addAttribute("followAdvoList", followAdvoList);
        } else {
            model.addAttribute("msg", "请先登录!");
        }
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_following";
    }

    @RequestMapping("square/discuss")
    public String discuss(Model model) {
        //TODO 讨论版计划是先做出来评论系统,然后再以评论系统为基础弄一个出来
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_discuss";
    }

    @RequestMapping("square/archives/{tagName}")
    public String squareArchives(Model model, @PathVariable String tagName) {
        List<ArticleDetailVO> list = baseService.listAllAdvoByTags(tagName);

        Map<String, List<ArticleDetailVO>> yearMap = BaseService.getYearMap(list);
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        model.addAttribute("yearMap", yearMap);
        model.addAttribute("desc", tagName);
        return "square/square_page_archives";
    }

    @RequestMapping("square/archives")
    public String allArticle(Model model) {
        List<ArticleDetailVO> list = baseService.listAllAdvoInSquare();

        Map<String, List<ArticleDetailVO>> yearMap = BaseService.getYearMap(list);
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        model.addAttribute("yearMap", yearMap);
        return "square/square_page_archives";
    }


    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
