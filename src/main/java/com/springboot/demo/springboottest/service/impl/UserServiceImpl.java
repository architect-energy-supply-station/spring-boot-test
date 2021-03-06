package com.springboot.demo.springboottest.service.impl;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:40
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public void insertByUser(User user) {
		if (userDao.existsUser(user.getPhone())) {
			return;
		}
		userDao.insertByUser(user);
	}

	@Override
	public void deleteById(String id) {
		 userDao.deleteById(id);
	}

	@Override
	public void update(User user) {
		 userDao.update(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public User findByPhone(int phone) {
		return userDao.findByPhone(phone);
	}

}
