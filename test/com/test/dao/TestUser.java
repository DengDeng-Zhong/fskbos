package com.test.dao;

import gq.dengbo.bos.dao.IUserDao;
import gq.dengbo.bos.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUser {

    @Autowired
    private IUserDao userDao;

    public void Test01() {
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

    public void test02() {
        test(1);
    }

    public void test(int i) {
        try {
            if (i == 1) {
                throw new Exception();
            }
            System.out.println("try:" + i);
            return;
        } catch (Exception e) {
            test(i + 1);
        } finally {
            System.out.println("finally:" + i);
        }
    }

    public void bublrSort() {
        int[] arr = {1, 4, 5, 6, 7, 3, 9, 0, 2, 8};
//        printArr(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
//        printArr(arr);
    }

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void test03() {
        int a = 0;
        int c = 0;
        do {
            --c;
            a = a - 1;

        } while (a > 0);
        System.out.println(c);
    }

    /**
     * abc***gfe***xyz --- ******abcgfexyz
     */
    @Test
    public void test04() {
        test004("aaaabbbbcccdddzcz");

    }

    public void test004(String str) {
        char[] tempList = str.toCharArray();   //将得到的String型字符串转变为Char型字符
        for (int i = 0; i < tempList.length - 1; i++) {     //循环数组
            if (tempList[i] == tempList[i + 1]) {               //判断两个数是否相等，如果相等则去掉
                tempList[i] = 0;                                       //如果相邻两数重复了  则为0 由于char是基本
                tempList[i + 1] = 0;                                   //类型，所以初始值为0，不过并不影响效果
            }
        }
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i] != 0) {                                  //如果两数都不为0  则输出
                System.out.print(tempList[i]);
            }
        }
    }
}

/**
 * 懒汉模式
 */
class Single {
    private static final Single single = new Single();

    public Single() {
    }

    public static Single getInstance() {
        return single;
    }

    String s = new String("123");
}

/**
 * 饿汉模式
 */
class Singleton {
    private static Singleton single;

    public Singleton() {

    }

    public static Singleton getInstance() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null)
                    single = new Singleton();
            }
        }
        return single;
    }
}



