package com.springboot.demo.springboottest.dao;


import com.springboot.demo.springboottest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.flywaydb.core.Flyway;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


@SpringBootTest
@SpringBootApplication
public class dao_Test extends AbstractTransactionalTestNGSpringContextTests {

//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private  Flyway flyway;
//
//    @BeforeTest
//    public void initTest() {
//        User user = new User();
//        user.setPhone(123);
//        user.setName("诺基亚");
//        user.setId("01");
//    }
//
//    @Test
//    public void testInsertByUser() {
//
//        userDao.findById("01");
//        assertEquals(countRowsInTable("user"), 1);
//        countRowsInTableWhere("user", "");
//
//
//
//    }
//
//    @AfterTest
//    public void cleanDb() {
//        flyway.clean();
//    }

}
