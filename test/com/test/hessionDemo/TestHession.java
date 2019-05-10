package com.test.hessionDemo;

import com.caucho.hessian.client.HessianProxyFactory;
import gq.dengbo.crm.service.Customer;
import gq.dengbo.crm.service.ICustomerService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

public class TestHession {
    @Test
    public void test01() throws Exception {
        /*
            访问另一个项目的数据,使用远程调用Hession
            1.创建一个代理对象
            2.
         */
        HessianProxyFactory factory = new HessianProxyFactory();

        ICustomerService customerService = (ICustomerService)factory.create(ICustomerService.class,"http://localhost:8080/hessian/customer");

        List<Customer> customerList = customerService.findAll();

        System.out.println(customerList);
    }
}
