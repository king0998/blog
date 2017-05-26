package hgrx.controller;

import com.google.gson.Gson;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by HGRX on 2017/5/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/applicationContext.xml"})
public class BaseControllerTest {

    @Autowired
    BaseService baseService;

    @Autowired
    AdminService adminService;


    @Test
    public void testConvertListToMap() {
        List<ArticleDetailVO> list = baseService.listAdvoByUserId(1L);
        System.out.println(new Gson().toJson(new BaseController(baseService, adminService).getYearMap(list)));
    }

}