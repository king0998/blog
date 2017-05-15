package hgrx.controller;

import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.entity.ArticleDetailVO;
import hgrx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by HGRX on 2017/5/11
 */
@Controller
public class BaseController {

    final
    BaseService baseService;

    @Autowired
    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }


    @RequestMapping("/article/{id}")
    public String getArticle(@PathVariable Long id,Model model){
        ArticleDetailVO advo = baseService.getArticleById(id);
        //TODO 没有该文章
        model.addAttribute("advo",advo);
        return "article";
    }

    @RequestMapping("/home-page/{id}")
    public String homePage(@PathVariable Long id, Model model) {
        //TODO id不为数字
        User user = baseService.getUserById(id);
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("advoList", advoList);
        return "home-page";
    }

    @RequestMapping("/archives/{id}")
    public String archives(@PathVariable Long id, Model model) {
        //TODO 暂时都不考虑分页
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(id);
        model.addAttribute("num", advoList.size());
        model.addAttribute("advoList", advoList);
        return "article-list";

    }

    @RequestMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model) {
        List<Tag> tagList = baseService.listTagsByUserId(id);
        User user = baseService.getUserById(id);
        model.addAttribute("tagList", tagList);
        model.addAttribute("user", user);
        return "tags";
    }

    @RequestMapping("about/{id}")
    public String about(@PathVariable Long id, Model model) {
        String about = baseService.getAboutMeByUserId(id);
        User user = baseService.getUserById(id);
        model.addAttribute("about", about);
        model.addAttribute("user", user);
        return "about";
    }

}
