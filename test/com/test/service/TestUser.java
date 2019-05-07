package com.test.service;

import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {

    @Autowired
    private IUserService userService;

    @Ignore
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("xqf");
        user.setPassword("123");
        userService.save(user);
    }

    @Test
    public void testUpdatePwd() {
        userService.modifyPassword("admin123", "4028e3816a8ab409016a8ab40d380000");
    }
}
