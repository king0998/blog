package hgrx.controller;

import hgrx.bean.Article;
import hgrx.bean.User;
import hgrx.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by HGRX on 2017/5/14
 */
@Controller
public class AdminController {

    Log log = LogFactory.getLog(this.getClass());

    final
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("registerHandle")
    public String handleRegister(User user, HttpSession session){
        //TODO 校验参数，暂时假设全部都是完美输入条件
        adminService.addUser(user);

        log.info("新注册了用户:" + user);
        session.setAttribute("user",user);
        return "square";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("loginHandle")
    public String loginHandle(User user,Model model,HttpSession session){

        User u = adminService.getUserByUsername(user.getUsername());
        if(u == null) {   //用户名不合规范和找不到用户名都是返回null
            model.addAttribute("msg","用户不存在！");
            return "login";
        }
        if(!u.getPassword().equals(user.getPassword())){
            model.addAttribute("msg","密码错误！");
            return "login";
        }
        session.setAttribute("user",u);
        return "square";
    }

    @RequestMapping("admin/article/add")
    public String addArticle(){
        return "admin/add_article";
    }

    @RequestMapping("admin/article/addHandle")
    public String addArticleHandle(Article article,HttpSession session){
        //TODO 校验article,tags参数
        User user = getUser(session);
        article.setUserId(user.getId());
        adminService.addArticle(article);

    }

    private User getUser(HttpSession session) {
        return (User)session.getAttribute("user");
    }

}
