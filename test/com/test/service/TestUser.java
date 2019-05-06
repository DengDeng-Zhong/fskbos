package com.test.service;

import gq.dengbo.bos.model.User;
import gq.dengbo.bos.service.IUserService;
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

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("xqf");
        user.setPassword("123");
        userService.save(user);
    }

}
