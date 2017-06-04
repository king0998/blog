package hgrx.service;

import hgrx.bean.*;
import hgrx.dao.AdminDao;
import hgrx.dto.ArticleDetailVO;
import hgrx.util.MyUtils;
import hgrx.util.RegexUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    }

    public List<User> listFollowingListByUserId(Long id) {
        return adminDao.listFollowingListByUserId(id);
    }

    public List<User> listFollowerListByUserId(Long id) {
        return adminDao.listFollowerListByUserId(id);
    }

    public List<ArticleDetailVO> listStarArticleByUserId(Long id) {
        return adminDao.listStarArticleByUserId(id);
    }

    public Boolean deleteFollowing(Follow follow) {
        return adminDao.deleteFollowing(follow);
    }

    /**
     * 取消收藏,针对个人
     */
    public Boolean deleteStar(Star star) {
        //TODO key : star-#{star.userId} value : Set<Article>
        //  首先做出假设:收藏或取消收藏的行为少,更多是展示是否收藏以及收藏的数量
        // 为每个用户保存一个收藏列表,增加和删除都同时对列表进行操作再写入数据库
        // 读取数据时仅读取缓存

        adminDao.updateStarNumDelete(star.getArticleId());
        return adminDao.deleteStar(star);
    }

    public Boolean addStar(Star star) {
        try {
            //TODO key : star-#{star.userId} value : Set<Article>
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
}
