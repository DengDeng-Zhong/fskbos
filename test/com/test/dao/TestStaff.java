package com.test.dao;

import gq.dengbo.bos.dao.IStaffDao;
import gq.dengbo.bos.model.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestStaff {

    @Autowired
    private IStaffDao staffDao;
    @Test
    public void testAddStaff(){
        Staff staff = new Staff();
        staff.setId("A002");
        staff.setName("钟登博");
        staff.setTelephone("13111110000");
        staffDao.save(staff);
    }
}
