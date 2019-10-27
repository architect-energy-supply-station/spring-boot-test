package com.springboot.demo.springboottest;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class TestService extends AbstractTransactionalTestNGSpringContextTests {

    @InjectMocks
    private UserServiceImpl userService;

//    @Mock
//    private UserService userService;

    @Mock
    private UserDao userDao;

    private User user;

    private List<User> list= new ArrayList<>();;

    @BeforeMethod
    public void initUser() {
        user = new User();
        user.setId("123");
        user.setPhone(123456);
        user.setName("Chris");
    }

    @BeforeMethod
    public void initListUser() {
        list.add(user);
    }

    @Test
    public void testFindById() {
        when(userDao.findById("123")).thenReturn(user);
        User byId = userService.findById("123");
        assertThat(byId.equals(user));
    }

    @Test
    public void testFindByName() {
        when(userDao.findByName("Chris")).thenReturn(user);
        assertThat(userService.findByName("Chris").equals(user));
    }

    @Test
    public void testFindByPhone() {
        when(userDao.findByPhone(123456)).thenReturn(user);
        assertThat(userService.findByPhone(123456).equals(user));
    }

    @Test
    public void testInsertUser() {
        when(userDao.insertByUser(user)).thenReturn(true);
        Assert.assertEquals(true,userService.insertByUser(user));
       }

    @Test
    public void testUpdate() {
        user.setName("6688");
        when(userDao.update(user)).thenReturn(true);
        Assert.assertEquals(true,userService.update(user));
    }

    @Test
    public void testFindAll() {
        when(userDao.findAll()).thenReturn(list);
        assertThat(userService.findAll().equals(list));
    }

    @Test
    public void testDeleteById() {
        when(userDao.deleteById("123")).thenReturn(true);
        Assert.assertEquals(true,userService.deleteById("123"));
    }
}