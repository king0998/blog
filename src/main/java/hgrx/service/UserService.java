package hgrx.service;

import hgrx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HGRX on 2017/6/12
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public Long getUserIdByArticleId(Long id) {
        return userDao.getUserIdByArticle(id);
    }
}
