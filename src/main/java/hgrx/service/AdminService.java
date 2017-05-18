package hgrx.service;

import hgrx.bean.*;
import hgrx.dao.AdminDao;
import hgrx.dto.ArticleDetailVO;
import hgrx.util.MyUtils;
import hgrx.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //TODO 暂时只考虑实现，后期考虑使用缓存之类的进行控制

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
        return adminDao.addFollow(follow);
    }

    public Boolean deleteArticleByUserIdAndId(Long userId, Long id) {
        Map<String, Long> par = new HashMap<>();
        par.put("userId", userId);
        par.put("id", id);
        return adminDao.deleteArticleByPar(par);

    }

    public void updateArticle(ArticleDetailVO advo, String tags, Boolean updateTimestamp) {
        Map<String, Object> par = new HashMap<>();
        par.put("advo", advo);
        // 在外面先更新timestamp,sql中决定是否更新到数据库
        advo.setTimestamp(System.currentTimeMillis());
        par.put("updateTimestamp", updateTimestamp);
        adminDao.updateArticle(par);

        setTags(new Article(advo), tags);

    }

    public List<User> listFollowingListByUserId(Long id) {
        return adminDao.listFollowingListByUserId(id);
    }
}
