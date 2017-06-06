package hgrx.service;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dao.BaseDao;
import hgrx.dto.ArticleDetailVO;
import hgrx.dto.TagWithSize;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import static hgrx.util.CacheUtils.MyCache;

/**
 * Created by HGRX on 2017/5/11
 */

@Service
public class BaseService {

    Log log = LogFactory.getLog(this.getClass());

    BaseDao baseDao;

    @Autowired
    public BaseService(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public ArticleDetailVO getAdvoById(Long id) {
        ArticleDetailVO advo = baseDao.getAdvoById(id);
        baseDao.updateReadNum(id);
        //TODO 阅读量 缓存   key : article-readNum-#{articleId} value : int
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
        //TODO 缓存   key : showAllArticle  value : List<ArticleDetailVO>
        //维护一个unix时间戳,记录每次从数据库获取数据的时间,每次访问都判断是否从数据库拿数据
        //或者开一个线程专门用来更新数据
        // TODO 保存数据用的List和Set也要使用线程安全的容器,或者使用普通容器,每次更新容器都new一个新的去更新引用
        List<ArticleDetailVO> list = baseDao.listAllAdvo();
        sliceAdvoListContent(list);
        return list;
    }

    public List<Article> listLatestArticleByUserId(Long userId) {
        return baseDao.listLatestArticleByUserId(userId);
    }

    @SuppressWarnings("unchecked")
    public List<TagWithSize> listTagsWithSizeByUserId(Long userId) {
        //TODO 缓存   key : listTags-#{userId}  value : List<TagWithSize>
        // 维护两个变量 listTags-#{userId}-A listTags-#{userId}-B
        // 查询,如果A和B都为0,则到数据库查询数据,并将数据缓存到map中,A++,B++
        // 如果出现了修改,如editArticle或addArticle,则将A++
        // 查询,如果A>B,则向数据库请求最新数据覆盖map中的数据,并令B=A
        // 否则直接返回map中的缓存数据
        List<Tag> tmp;
        String identityId = "listTags-" + userId;

        Lock readLock = MyCache.getRWLock(identityId).readLock();
        Lock writeLock = MyCache.getRWLock(identityId).writeLock();
        readLock.lock();
        if (MyCache.isChanged(identityId)) {
            log.debug("---------------------------");
            log.debug("isChanged,更新了缓存");
            readLock.unlock();
            writeLock.lock();
            tmp = baseDao.listTagsByUserId(userId);
            MyCache.updateCache(identityId, tmp);
            writeLock.unlock();

        } else {
            log.debug("---------------------------");
            log.debug("使用了现有的缓存");
            tmp = (List<Tag>) MyCache.getCache(identityId);
            readLock.unlock();
        }
        return addSizeToTag(tmp);
    }

    /**
     * 算出每个tag所包含的文章数
     */
    private List<TagWithSize> addSizeToTag(List<Tag> tagWithSizes) {
        //TODO 缓存  key : tags-size-#{tagsId}   value : int 这个搞个 volatile 关键字
        //TODO 还要解决的一个问题是每次循环都会将连接返回池子再拿出来,希望能一次性跑完再放回去

        List<TagWithSize> sizeList = new ArrayList<>(tagWithSizes.size());
        tagWithSizes.forEach(it -> {
            sizeList.add(new TagWithSize(it, baseDao.getSizeOfTag(it.getId())));
        });
        return sizeList;
    }


    public List<TagWithSize> listAllTagsWithSize() {
        //TODO 所有标签及大小  key : allTagsWithSizeList  List<TagWithSize>
        //TODO 按照时间更新
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
        //TODO  key : hotAdvo  value List<ArticleDetailVo>
        // 理论上是统计每篇文章的访问量,当然是理论上...
        List<ArticleDetailVO> hotAdvoList = baseDao.listHotAdvo();
        sliceAdvoListContent(hotAdvoList);
        return hotAdvoList;
    }

    private void sliceAdvoListContent(List<ArticleDetailVO> hotAdvoList) {
        hotAdvoList.forEach(
                it -> {
                    String tmp = it.getContent();
                    int end = tmp.indexOf("<--->");
                    tmp = tmp.substring(0, end == -1 ? (tmp.length() > 200 ? 200 : tmp.length()) : end);
                    it.setContent(tmp);
                }
        );
    }
}
