package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.dto.ArticleDetailVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HGRX on 2017/5/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/applicationContext.xml"})
public class AdminDaoTest {

    @Autowired
    AdminDao adminDao;

    @Test
    public void addTag() throws Exception {
        Tag tag = new Tag("springMVC");
        adminDao.addTag(tag);
        System.err.println(tag);
    }

    @Test
    public void addArticle() {
        Article article = new Article(2L, "用户2的第一篇测试发布文章", System.currentTimeMillis(), "测试文章的content啊啊啊啊啊啊");
        adminDao.addArticle(article);
        System.out.println(article);
    }

    @Test
    public void updateArticle() {
        ArticleDetailVO advo = new ArticleDetailVO();
        advo.setUserId(1);
        advo.setTitle("测试update");
        advo.setContent("再次掩护content");
        advo.setDraft(false);
        advo.setTimestamp(333L);
        advo.setId(13);

        Map<String, Object> par = new HashMap<>();
        Boolean updateTimestamp = false;
        par.put("advo", advo);
        par.put("updateTimestamp", updateTimestamp);

        System.out.println(adminDao.updateArticle(par));

    }

}