package hgrx.service;

import hgrx.dao.BaseDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

/**
 * Created by HGRX on 2017/5/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/config/applicationContext.xml"})
public class BaseServiceTest {

    @Autowired
    BaseService baseService;

    @Test
    public void getArticleById() throws Exception {
//        System.out.println(baseService.getAdvoById(1));
        InputStream inputStream = Resources.getResourceAsStream("config/mybatis/mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();
        BaseDao dao = session.getMapper(hgrx.dao.BaseDao.class);
        System.out.println(dao.getArticleById(1L));
    }

    @Test
    public void testSmt(){
//        System.out.println(baseService.getAdvoById(1));
//        baseService = new BaseService(MyBatisUtils.getSqlSession().getMapper(hgrx.dao.BaseDao.class));
//        System.out.println(baseService.getAdvoById(1));
        System.out.println(baseService.getAdvoById(1L));
//        System.out.println(new BaseService(baseDao).getAdvoById(1));
    }


}



