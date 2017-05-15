package hgrx.service;

import hgrx.bean.Follow;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by HGRX on 2017/5/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/config/applicationContext.xml"})
public class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Test
    public void getUserByUsername() throws Exception {
        Assert.assertTrue(adminService.getUserByUsername("hahah") == null);
    }

    @Test
    public void addFollower() {
        adminService.addFollow(new Follow(1L, 2L));
    }

}