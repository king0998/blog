package hgrx.service;

import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dao.BaseDao;
import hgrx.dto.ArticleDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ArticleDetailVO getAdvoById(Long id) {
        ArticleDetailVO advo = new ArticleDetailVO(baseDao.getArticleById(id));
        advo.setUsername(baseDao.getUsernameById(id));
//        advo.setLikeNum(baseDao.getLikeNumById(id));
//        advo.setStarNum(baseDao.getStarNumById(id));
        //TODO 直接在article中加了starNum与likeNum两个变量，考虑是继续使用触发器还是程序来控制这两个值
        advo.setTags(baseDao.listTagsWithArticleId(id));
        return advo;
    }

    public List<ArticleDetailVO> listAdvoByUserId(Long id) {
        List<ArticleDetailVO> list = baseDao.listAdvoWithoutTagsByUserId(id);
        // 使用mybatis配置很麻烦，join的话也不太好写
        for (ArticleDetailVO advo : list) {
            advo.setTags(baseDao.listTagsWithArticleId(advo.getId()));
        }
        return list;
    }

    public User getUserById(Long id) {
        return baseDao.getUserById(id);
    }

    public List<Tag> listTagsByUserId(Long id) {
        return baseDao.listTagsByUserId(id);
    }

    public String getAboutMeByUserId(Long id) {
        return baseDao.getAboutByUserId(id);
    }

    public List<ArticleDetailVO> listAllAdvo() {
        return baseDao.listAllAdvo();
    }
}
