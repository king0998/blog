package hgrx.service;

import hgrx.bean.*;
import hgrx.dao.AdminDao;
import hgrx.dao.BaseDao;
import hgrx.dto.ArticleDetailVO;
import hgrx.util.CacheIdentity;
import hgrx.util.MyUtils;
import hgrx.util.RegexUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by HGRX on 2017/5/14
 */
@Service
public class AdminService {

    Log log = LogFactory.getLog(this.getClass());

    final
    AdminDao adminDao;

    final BaseDao baseDao;

    @Autowired
    public AdminService(AdminDao adminDao, BaseDao baseDao) {
        this.baseDao = baseDao;
        this.adminDao = adminDao;
    }

    public boolean addUser(User user) {
        user.setPassword(DigestUtils.sha1Hex(user.getPassword() + user.getPassword().length()));
        return adminDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        if (!RegexUtils.isUsername(username)) {
            return null;
        }
        return adminDao.getUserByUsername(username);
    }

    public void addArticle(Article article, String tags) throws RuntimeException {
        //捕获到异常则退出，后期可以考虑在这个地方实践一下事务？
        //存入表后取出id
        adminDao.addArticle(article);

        setTags(article, tags);

    }

    private void setTags(Article article, String tags) {
        List<String> tagsList = MyUtils.transformTagsToList(tags);
        for (String tag : tagsList) {
            Long tagId = getTagId(new Tag(tag, article.getUserId()));
            adminDao.addTagLink(new ArticleTagsLink(article.getId(), tagId));
        }
    }


    /**
     * @param tag Tag 对象，包含了name和userId两个属性
     * @return 如果该tag第一次出现，则插入数据库并返回id
     */
    public Long getTagId(Tag tag) {
        Long tagId = adminDao.getTagId(tag);
        if (tagId == null) {  //该tag第一次出现，插入数据库
            adminDao.addTag(tag);
            return tag.getId();
        }
        return tagId;
    }

    public Boolean addFollow(Follow follow) {
        try {
            //设置了unique键,如果重复插入会抛异常
            return adminDao.addFollow(follow);
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean deleteArticleByUserIdAndId(Long userId, Long id) {
        Map<String, Long> par = new HashMap<>();
        par.put("userId", userId);
        par.put("id", id);
        return adminDao.deleteArticleByPar(par);

    }

    @SuppressWarnings("unchecked")
    public void updateArticle(ArticleDetailVO advo, String tags, Boolean updateTimestamp, List<String> beforeEdit) {

        Map<String, Object> par = new HashMap<>();
        par.put("advo", advo);
        // 在外面先更新timestamp,sql中决定是否更新到数据库
        advo.setTimestamp(System.currentTimeMillis());
        par.put("updateTimestamp", updateTimestamp);
        adminDao.updateArticle(par);

        // 处理变动的tags,步骤是获取两者的交集,再从before与after中去除该交集
        // before中留下的是要被删除的,after中留下的新增的
        List<String> afterEdit = MyUtils.transformTagsToList(tags);
        List<String> intersection = (ArrayList) CollectionUtils.retainAll(beforeEdit, afterEdit);

        beforeEdit.removeAll(intersection);
        afterEdit.removeAll(intersection);

        for (String tag : beforeEdit) {
            // 可以保证before中一定是"数据库中存在的tag
            ArticleTagsLink atl = new ArticleTagsLink(advo.getId(), getTagId(new Tag(tag, advo.getUserId())));
            adminDao.deleteTagLink(atl);
        }

        for (String tag : afterEdit) {
            ArticleTagsLink atl = new ArticleTagsLink(advo.getId(), getTagId(new Tag(tag, advo.getUserId())));
            adminDao.addTagLink(atl);
        }

        MyCache.updateAbValue(CacheIdentity.LIST_TAGS_WITH_USERID + "" + advo.getUserId());
        MyCache.updateAbValue(CacheIdentity.LIST_ALL_TAGS.toString());
    }

    public List<User> listFollowingListByUserId(Long id) {
        return adminDao.listFollowingListByUserId(id);
    }

    public List<User> listFollowerListByUserId(Long id) {
        return adminDao.listFollowerListByUserId(id);
    }

    @SuppressWarnings("unchecked")
    public List<ArticleDetailVO> listStarArticleByUserId(Long id) {
        String identity = CacheIdentity.USER_STAR_LIST_WITH_USERID.toString() + id;
        Lock readLock = MyCache.getRWLock(identity).writeLock();
        readLock.lock();

        List<ArticleDetailVO> list = (List<ArticleDetailVO>) MyCache.getCache(identity);
        if (list == null) {
            list = adminDao.listStarArticleByUserId(id);
            log.debug("################################");
            log.debug("第一次加载缓存");
            MyCache.putCache(identity, list);
        }
        log.debug("################################");
        log.debug("我的收藏:使用了缓存");
        readLock.unlock();
        return list;
    }

    public Boolean deleteFollowing(Follow follow) {
        return adminDao.deleteFollowing(follow);
    }

    /**
     * 取消收藏,针对个人
     */
    @SuppressWarnings("unchecked")
    public Boolean deleteStar(Star star) {
        //TODO key : star-#{star.userId} value : Set<Article>
        //  首先做出假设:收藏或取消收藏的行为少,更多是展示是否收藏以及收藏的数量
        // 为每个用户保存一个收藏列表,增加和删除都同时对列表进行操作再写入数据库
        // 读取数据时仅读取缓存

        String identity = CacheIdentity.USER_STAR_LIST_WITH_USERID.toString() + star.getUserId();
        Lock writeLock = MyCache.getRWLock(identity).writeLock();
        //程序读取star列表仅读取该
        writeLock.lock();
        List<ArticleDetailVO> starListWIthUserId = (List<ArticleDetailVO>) MyCache.getCache(identity);
        //不能传函数进去好蠢=_=
        if (starListWIthUserId == null) {
            starListWIthUserId = adminDao.listStarArticleByUserId(star.getUserId());
            MyCache.putCache(identity, starListWIthUserId);
        }
        //删除
        starListWIthUserId.removeIf(it -> it.getUserId() == star.getUserId());
        writeLock.unlock();

        adminDao.updateStarNumDelete(star.getArticleId());
        return adminDao.deleteStar(star);
    }

    @SuppressWarnings("unchecked")
    public Boolean addStar(Star star) {
        try {

            //star cache operate

            String identity = CacheIdentity.USER_STAR_LIST_WITH_USERID.toString() + star.getUserId();
            Lock writeLock = MyCache.getRWLock(identity).writeLock();
            writeLock.lock();
            //程序读取star列表仅读取该列表
            List<ArticleDetailVO> starListWIthUserId = (List<ArticleDetailVO>) MyCache.getCache(identity);
            //不能传函数进去好蠢=_=,每个读取的地方都要判断缓存是否为空
            if (starListWIthUserId == null) {
                starListWIthUserId = adminDao.listStarArticleByUserId(star.getUserId());
                log.debug("################################");
                log.debug("第一次加载缓存");
                MyCache.putCache(identity, starListWIthUserId);
            }
            //添加
            log.debug("#############################");
            log.debug("更新了缓存");
            starListWIthUserId.add(baseDao.getAdvoById(star.getArticleId()));
            writeLock.unlock();

            //end

            adminDao.updateStarNumAdd(star.getArticleId());
            return adminDao.addStar(star);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean addLike(Like like) {
        return adminDao.addLike(like);
    }

    public boolean hasStarArticle(User user, Long articleId) {
        if (user == null) {
            return false;
        }
        List<ArticleDetailVO> list = listStarArticleByUserId(user.getId());
        return list.contains(new ArticleDetailVO(articleId));
    }
}
