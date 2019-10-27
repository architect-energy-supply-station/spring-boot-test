package com.springboot.demo.springboottest.service;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class service_test {


    @Mock
    private UserDao userDao;


    @InjectMocks
    private UserServiceImpl userService;


    User user = new User();

    List<User> userList = new ArrayList<>();

    @Before
    public void initTest() {
//        User userOne = new User();
        user.setPhone(26);
        user.setId("26");
        user.setName("张三");
//        userOne = user;
//        userOne.setId("13");
        userList.add(user);
    }

    @Test
    public void findAll_Test() {

        when(userDao.findAll()).thenReturn(userList);
        List<User> all = userService.findAll();
        assertEquals(all,userList);
    }

    @Test
    public void findById_Test() {
        when(userDao.findById("26")).thenReturn(user);
        User byId = userService.findById("26");
        assertEquals(byId,user);
    }

    @Test
    public void findByPhone_Test() {
//        System.out.println(user);
        when(userDao.findByPhone(26)).thenReturn(user);
        User byId = userService.findByPhone(26);
        assertEquals(byId,user);
    }


    @Test
    public void findByName_Test() {
        when(userDao.findByName("张三")).thenReturn(user);
        User byId = userService.findByName("张三");
        assertEquals(byId,user);
    }

    @Test
    public void update_Test() {
        when(userDao.update(user)).thenReturn(true);
        user.setName("123");
        boolean update = userService.update(user);
        assertTrue(update);
        assertEquals(user.getName(),"123");
    }

    @Test
    public void insertByUser_Test() {

        User userone = new User();
        userone.setId("1");
        userone.setName("1");
        userone.setPhone(1);
        when(userDao.insertByUser(userone)).thenReturn(true);
        boolean b = userService.insertByUser(userone);
        assertTrue(b);
//        System.out.println(b);

//        List<User> all = userServiceImpl.findAll();
//        assertEquals(1,all.size());
//        System.out.println(all);
    }
    @Test
    public void deleteById_Test() {
        when(userDao.deleteById("26")).thenReturn(true);
        boolean b = userService.deleteById("26");
        assertEquals(true, b);
    }


}
