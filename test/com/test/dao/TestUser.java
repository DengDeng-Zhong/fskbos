package com.test.dao;

import gq.dengbo.bos.dao.IUserDao;
import gq.dengbo.bos.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {

    @Autowired
    private IUserDao userDao;

    @Test
    public void Test01(){
        /**
         * spring配置做好
         * 1.spring的单元测试加载spring配置文件
         * 2.配置一个UserDaoImpl的bean[@Repository]
         * 3.配置hibernateTemplate的模板bean
         * 4.事务
         */


        User user = new User();
        user.setUsername("zdf");
        user.setPassword("123");
        //调用dao【保存用户】
//        userDao.save(user);

        //获取用户
//        User user1 = userDao.findById("4028e3816a8ab409016a8ab40d380000");
//        System.out.println(user1);

        //获取所有用户
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
}
