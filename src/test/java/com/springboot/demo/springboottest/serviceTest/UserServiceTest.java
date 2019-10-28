package com.springboot.demo.springboottest.serviceTest;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author 任蒙蒙
 * @version 1.0.0
 * @since 2019/10/26 10:06
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserDao userDao;

    List<User> userList = new ArrayList<>();

    User user = new User();

    //    todo
    @Before
    public void initTestUserListData() {
        User user = new User();
        user.setId("666");
        user.setName("mm");
        user.setPhone(123445);
        userList.add(user);
    }

    @Before
    public void initTestUserData() {
        user.setId("888");
        user.setName("ll");
        user.setPhone(6868);
    }

    @Test
    public void findAllTest() {
        when(userDao.findAll()).thenReturn(userList);
        List<User> users = userServiceImpl.findAll();
        assertEquals(userList, users);
    }

    @Test
    public void findByIdTest() {
        when(userDao.findById("888")).thenReturn(user);
        User result = userServiceImpl.findById("888");
        assertEquals(user, result);

        when(userDao.findById(Mockito.anyString())).thenReturn(user);
        result = userServiceImpl.findById("1234");
        assertEquals(user, result);
    }

    @Test
    public void findByNameTest() {
        when(userDao.findByName("mm")).thenReturn(user);
        User result = userServiceImpl.findByName("mm");
        assertEquals(user, result);

        when(userDao.findByName(Mockito.anyString())).thenReturn(user);
        result = userServiceImpl.findByName("mm");
        assertEquals(user, result);
    }

    @Test
    public void findByPhoneTest() {
        when(userDao.findByPhone(888)).thenReturn(user);
        User result = userServiceImpl.findByPhone(888);
        assertEquals(user, result);

        when(userDao.findByPhone(Mockito.anyInt())).thenReturn(user);
        result = userServiceImpl.findByPhone(888);
        assertEquals(user, result);
    }

    //    todo ??
    @Test
    public void insertByUserTest() {
        User user = new User();
        when(userDao.insertByUser(any(User.class))).thenReturn(true);
        assertTrue(userServiceImpl.insertByUser(user));
//        verify(userDao, times(1)).insertByUser(any(User.class));
    }

    @Test
    public void deleteByIdTest() {
        when(userDao.deleteById("888")).thenReturn(true);
        boolean result = userServiceImpl.deleteById("888");
        assertTrue(result);
    }

    //    todo
    @Test
    public void updateTest() {
        User user = new User();
        when(userDao.update(any(User.class))).thenReturn(true);
        assertTrue(userServiceImpl.update(user));
//        verify(userDao, times(1)).update(any(User.class));
    }

}
