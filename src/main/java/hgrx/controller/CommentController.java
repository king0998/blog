package hgrx.controller;

import hgrx.bean.Comment;
import hgrx.service.CommentService;
import hgrx.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HGRX on 2017/6/12
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;


    @RequestMapping("/comment/addHandle")
    public String addComment(Comment comment) {
        //TODO 参数校验
        comment.setCreatedDate(System.currentTimeMillis());
        commentService.addComment(comment);
        String urlSuffix;
        switch (comment.getEntityType()) {
            case MyUtils.COMMENT_ENTITY_TYPE_ARTICLE:
                urlSuffix = "/article/" + comment.getEntityId();
                break;
            case MyUtils.COMMENT_ENTITY_TYPE_ABOUT:
                urlSuffix = "/about/" + comment.getEntityId();
                break;
            default:
                urlSuffix = "login";
        }
        return "redirect:" + urlSuffix;
    }

}
