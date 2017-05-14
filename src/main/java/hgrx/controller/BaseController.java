package hgrx.controller;

import hgrx.entity.ArticleDetailVO;
import hgrx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HGRX on 2017/5/11
 */
@Controller
public class BaseController {

    @Autowired
    BaseService baseService;


    public BaseService getBaseService() {
        return baseService;
    }

    @RequestMapping("/article/{id}")
    public String getArticle(@PathVariable Long id,Model model){
        ArticleDetailVO advo = baseService.getArticleById(id);
        //TODO 没有该文章
        model.addAttribute("advo",advo);
        return "article";
    }

}
