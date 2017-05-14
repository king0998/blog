package hgrx.service;

import hgrx.dao.BaseDao;
import hgrx.entity.ArticleDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HGRX on 2017/5/11
 */

@Service
public class BaseService {

    BaseDao baseDao;

    @Autowired
    public BaseService(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public ArticleDetailVO getArticleById(Long id) {
        //TODO 数据库表设计
        ArticleDetailVO advo = new ArticleDetailVO(baseDao.getArticleById(id));
        advo.setUsername(baseDao.getUsernameById(id));
        advo.setLikeNum(baseDao.getLikeNumById(id));
        advo.setStarNum(baseDao.getStarNumById(id));
        return advo;
    }
}
