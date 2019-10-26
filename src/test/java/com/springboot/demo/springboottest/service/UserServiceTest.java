package com.springboot.demo.springboottest.service;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @Classname UserServiceTest
 * @Auther sunshinezhang
 * @Date 2019/10/24 22:42
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {


	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserDao userDao;


	List<User> userList = new ArrayList<>();

	User user = new User();


	@Before
	public void initTestUserListData() {
		User user = new User();
		user.setName("bill");
		user.setId("234");
		user.setPhone(1234);
		userList.add(user);
	}

	@Before
	public void initTestUserData() {
		user.setId("1234");
		user.setName("sunshine");
		user.setPhone(123456789);
	}

	@Test
	public void testFindAll() {
		when(userDao.findAll()).thenReturn(userList);
		List<User> users = userService.findAll();
		assertEquals(userList, users);
	}


	@Test
	public void testFindById() {
		when(userDao.findById("1234")).thenReturn(user);
		User result = userService.findById("1234");
		assertEquals(user, result);


		when(userDao.findById(Mockito.anyString())).thenReturn(user);
		result = userService.findById("1234");
		assertEquals(user, result);
	}

	@Test
	public void testFindByName() {
		when(userDao.findByName("sunshine")).thenReturn(user);
		User result = userService.findByName("sunshine");
		assertEquals(user, result);


		when(userDao.findByName(Mockito.anyString())).thenReturn(user);
		result = userService.findByName("sunshine");
		assertEquals(user, result);
	}


	@Test
	public void testFindByPhone() {
		when(userDao.findByPhone(123456789)).thenReturn(user);
		User result = userService.findByPhone(123456789);
		assertEquals(user, result);


		when(userDao.findByPhone(Mockito.anyInt())).thenReturn(user);
		result = userService.findByPhone(123456789);
		assertEquals(user, result);
	}


	@Test
	public void testInsertByUser() {
		User user = new User();
		when(userDao.insertByUser(any(User.class))).thenReturn(true);
		//when(userDao.existsUser(12345678)).thenReturn(false);
		assertTrue(userService.insertByUser(user));
		verify(userDao, times(1)).insertByUser(any(User.class));
	}

	@Test
	public void testInsertByUserFalse() {
		User user = new User();
		user.setPhone(12345678);
		when(userDao.existsUser(12345678)).thenReturn(true);
		Assert.assertFalse(userService.insertByUser(user));
		verify(userDao, times(1)).existsUser(anyInt());
	}


	@Test
	public void testDeleteById() {
		when(userDao.deleteById("1234")).thenReturn(true);
		boolean b = userService.deleteById("1234");
		assertEquals(true, b);
	}


	@Test
	public void testUpdate() {
		User user = new User();
		when(userDao.update(any(User.class))).thenReturn(true);
		assertTrue(userService.update(user));
		verify(userDao, times(1)).update(any(User.class));
	}
}
