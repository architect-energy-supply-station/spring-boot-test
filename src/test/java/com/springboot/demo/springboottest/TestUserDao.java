package com.springboot.demo.springboottest;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


@SpringBootTest
@AutoConfigureMybatis
public class TestUserDao extends AbstractTestNGSpringContextTests {


    @Autowired
    private UserDao userDao;

    @Autowired
    private Flyway flyway;


    private User user = new User();

    private List<User> list = new ArrayList<>();

    @BeforeMethod
    public void initUser() {
        user.setId("s01");
        user.setName("张三");
        user.setPhone(26);
        this.list.add(user);


    }

    @BeforeClass
    void setUp() {
        flyway = Flyway.configure().dataSource("jdbc:h2:mem:h2test;DB_CLOSE_DELAY=-1;MODE=MySQL", "root", "root").load();

    }


    @Test
    public void testFindById() {

        System.out.println(userDao);
        assertEquals(user, userDao.findById("s01"));


    }

    @Test
    @Transactional
    public void testInsertUser() {
        User user1 = new User();
        user1.setName("s10");
        user1.setPhone(6688);
        user1.setId("s10");
        assertEquals(true, userDao.insertByUser(user1));
    }

    @Test
    public void testFindByName() {

        assertEquals(user, userDao.findByName("张三"));
    }

    @Test
    public void testFindByPhone() {
        assertEquals(user, userDao.findByPhone(26));
    }

    @Test
    public void testFindAllUser() {
        assertEquals(7, userDao.findAll().size());

    }

    @Test
    @Transactional
    public void testUpdate() {
        User s01 = userDao.findById("s01");
        s01.setName("Chris");
        assertEquals(true, userDao.update(s01));
    }

    @Test
    @Transactional
    public void testDeleteId() {
        assertEquals(true, userDao.deleteById("s08"));
    }

    @AfterClass
    void afterClass() {
        System.out.println("-------------------afterClass");
        flyway.clean();
    }
}

