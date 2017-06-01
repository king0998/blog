package hgrx.controller;

import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("square/new")
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
        //TODO
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_discuss";
    }

    @RequestMapping("square/archives")
    public String squareArchives(Model model) {
        //TODO
        model.addAttribute("tags", baseService.listAllTagsWithSize());
        return "square/square_page_archives";
    }


    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
