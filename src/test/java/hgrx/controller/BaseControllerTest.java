package hgrx.controller;

import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


}