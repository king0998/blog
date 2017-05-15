package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}