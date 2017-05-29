package hgrx.service;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dao.BaseDao;
import hgrx.dto.ArticleDetailVO;
import hgrx.dto.TagWithSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ArticleDetailVO advo = baseDao.getAdvoById(id);
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

    public List<Article> listLatestArticleByUserId(Long userId) {
        return baseDao.listLatestArticleByUserId(userId);
    }

    public List<TagWithSize> listTagsWithSizeByUserId(Long userId) {
        return addSizeToTag(baseDao.listTagsByUserId(userId));
    }

    /**
     * 算出每个tag所包含的文章数
     */
    private List<TagWithSize> addSizeToTag(List<Tag> tagWithSizes) {
        //TODO 缓存   还要解决的一个问题是每次循环都会将连接返回池子再拿出来,希望能一次性跑完再放回去
        List<TagWithSize> sizeList = new ArrayList<>(tagWithSizes.size());
        tagWithSizes.forEach(it -> {
            sizeList.add(new TagWithSize(it, baseDao.getSizeOfTag(it.getId())));
        });
        return sizeList;
    }

    public List<ArticleDetailVO> listAdvoByUserIdAndKeyword(String keyword, Long userId) {
        Map<String, Object> par = new HashMap<>();
        par.put("keyword", keyword);
        par.put("userId", userId);
        return baseDao.listAdvoByUserIdAndKeyword(par);
    }

    public List<ArticleDetailVO> listAdvoWithPartContentByUserId(Long id) {
        List<ArticleDetailVO> list = listAdvoByUserId(id);
        list.forEach(
                //TODO 缓存?感觉每次都截取一次有点蠢
                it -> {
                    String tmp = it.getContent();
                    int end = tmp.indexOf("<--->");
                    tmp = tmp.substring(0, end == -1 ? (tmp.length() > 200 ? 200 : tmp.length()) : end);
                    it.setContent(tmp);
                }
        );
        return list;
    }
}
