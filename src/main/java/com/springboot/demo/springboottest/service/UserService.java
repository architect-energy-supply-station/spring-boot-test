package com.springboot.demo.springboottest.service;

import com.springboot.demo.springboottest.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserService
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:39
 */
public interface UserService {

	//新增
	void insertByUser(User user);

	//删除
	void deleteById(String id);

	//更新
	void update(User user);

	//查询所有人员
	List<User> findAll();

	//根据Id查询人员
	User findById(String id);

	//根据姓名查询人员
	User findByName(String name);

	//根据手机号查询人员
	User findByPhone(int phone);


}
