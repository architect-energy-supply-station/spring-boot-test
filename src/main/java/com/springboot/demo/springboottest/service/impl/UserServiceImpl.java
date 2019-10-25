package com.springboot.demo.springboottest.service.impl;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:40
 */
@Service("userService")
@Transactional(transactionManager = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public boolean insertByUser(User user) {
		if (userDao.existsUser(user.getPhone())) {
			return true;
		}
		userDao.insertByUser(user);
		return true;
	}

	@Override
	public boolean deleteById(String id) {
		 userDao.deleteById(id);
		return true;
	}

	@Override
	public boolean update(User user) {
		 userDao.update(user);
		return true;
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
