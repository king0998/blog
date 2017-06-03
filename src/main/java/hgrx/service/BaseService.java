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
        baseDao.updateReadNum(id);
        //TODO 阅读量 缓存
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
        //TODO 缓存
        List<ArticleDetailVO> list = baseDao.listAllAdvo();
        sliceAdvoListContent(list);
        return list;
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


    public List<TagWithSize> listAllTagsWithSize() {
        //TODO 缓存
        List<Tag> tags = baseDao.listAllTags();
        List<TagWithSize> tagWithSizes = addSizeToTag(tags);
        Map<String, TagWithSize> maps = new HashMap<>();
        tagWithSizes.forEach(it -> {
            TagWithSize tmp = maps.get(it.getName());
            if (tmp == null) {
                maps.put(it.getName(), it);
            } else {
                tmp.setSize(it.getSize() + tmp.getSize());
            }
        });
        return new ArrayList<>(maps.values());
    }

    public List<ArticleDetailVO> listAdvoByUserIdAndKeyword(String keyword, Long userId) {
        Map<String, Object> par = new HashMap<>();
        par.put("keyword", keyword);
        par.put("userId", userId);
        return baseDao.listAdvoByUserIdAndKeyword(par);
    }

    public List<ArticleDetailVO> listAdvoWithPartContentByUserId(Long id) {
        List<ArticleDetailVO> list = listAdvoByUserId(id);
        sliceAdvoListContent(list);
        return list;
    }

    public List<ArticleDetailVO> listFollowingAdvoList(Long id) {
        List<Integer> following = baseDao.listFollowingUser(id);
        return baseDao.listFollowingAdvo(following);
    }

    public List<ArticleDetailVO> listHotAdvo() {
        //TODO  缓存,按照时间更新
        List<ArticleDetailVO> hotAdvoList = baseDao.listHotAdvo();
        sliceAdvoListContent(hotAdvoList);
        return hotAdvoList;
    }

    private void sliceAdvoListContent(List<ArticleDetailVO> hotAdvoList) {
        hotAdvoList.forEach(
                //TODO 缓存?感觉每次都截取一次有点蠢,这个地方缓存要设在文章处
                it -> {
                    String tmp = it.getContent();
                    int end = tmp.indexOf("<--->");
                    tmp = tmp.substring(0, end == -1 ? (tmp.length() > 200 ? 200 : tmp.length()) : end);
                    it.setContent(tmp);
                }
        );
    }
}
