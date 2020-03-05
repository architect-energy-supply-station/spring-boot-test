package com.springboot.demo.springboottest.service;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
 class UserServiceTest {


	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserDao userDao;


	List<User> userList = new ArrayList<>();

	User user = new User();


	@Before
	 void initTestUserListData() {
		User user = new User();
		user.setName("betty");
		user.setId("123");
		user.setPhone(1234);
		userList.add(user);
	}

	@Before
	 void initTestUserData() {
		user.setId("123");
		user.setName("betty");
		user.setPhone(123456789);
	}

	@Test
	 void testFindAll() {
		when(userDao.findAll()).thenReturn(userList);
		List<User> users = userService.findAll();
		assertEquals(userList, users);
	}


	@Test
	 void testFindById() {
		when(userDao.findById("1234")).thenReturn(user);
		User result = userService.findById("1234");
		assertEquals(user, result);


		when(userDao.findById(Mockito.anyString())).thenReturn(user);
		result = userService.findById("1234");
		assertEquals(user, result);
	}

	@Test
	 void testFindByName() {
		when(userDao.findByName("betty")).thenReturn(user);
		User result = userService.findByName("betty");
		assertEquals(user, result);

		when(userDao.findByName(Mockito.anyString())).thenReturn(user);
		result = userService.findByName("betty");
		assertEquals(user, result);
	}


	@Test
	 void testFindByPhone() {
		when(userDao.findByPhone(123456789)).thenReturn(user);
		User result = userService.findByPhone(123456789);
		assertEquals(user, result);

		when(userDao.findByPhone(Mockito.anyInt())).thenReturn(user);
		result = userService.findByPhone(123456789);
		assertEquals(user, result);
	}


	@Test
	 void testInsertByUser() {
		User user = new User();
		when(userDao.insertByUser(any(User.class))).thenReturn(true);
		assertTrue(userService.insertByUser(user));
		verify(userDao, times(1)).insertByUser(any(User.class));
	}

	@Test
	 void testInsertByUserFalse() {
		User user = new User();
		user.setPhone(12345678);
		when(userDao.existsUser(12345678)).thenReturn(true);
		Assert.assertFalse(userService.insertByUser(user));
		verify(userDao, times(1)).existsUser(anyInt());
	}


	@Test
	 void testDeleteById() {
		when(userDao.deleteById("1234")).thenReturn(true);
		boolean b = userService.deleteById("1234");
		assertEquals(true, b);
	}


	@Test
	 void testUpdate() {
		User user = new User();
		when(userDao.update(any(User.class))).thenReturn(true);
		assertTrue(userService.update(user));
		verify(userDao, times(1)).update(any(User.class));
	}
}
