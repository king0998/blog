package hgrx.service;

import hgrx.bean.Article;
import hgrx.bean.User;
import hgrx.dao.AdminDao;
import hgrx.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HGRX on 2017/5/14
 */
@Service
public class AdminService {

    final
    AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public boolean addUser(User user){
        return adminDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        if(!RegexUtils.isUsername(username)){
            return null;
        }
        return adminDao.getUserByUsername(username);
    }

    public Boolean addArticle(Article article) {
        //拆分tags,实际
        adminDao.addArticle(article);
        //TODO 将tags拆分成list存入tags表并在link表中建立联系

    }
}
