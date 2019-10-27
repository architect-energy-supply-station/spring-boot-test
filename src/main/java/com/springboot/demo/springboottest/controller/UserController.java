package com.springboot.demo.springboottest.controller;

import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname UserController
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:39
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;


	/**
	 * 查询所有人员
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:06 上午
	 */
	@GetMapping(value = {"/findAllUser"})
	public List<User> findAllUser(){
		List<User> userList = userService.findAll();
		System.out.println(userList);
		return userList;
	}

	/**
	 *根据Id查询用户
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:06 上午
	 */
	@GetMapping(value = {"/findById/{id}"})
	public User findById(@PathVariable String id){
		System.out.println(userService.findById(id));
		return userService.findById(id);
	}

	/**
	 *根据姓名查询用户
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:06 上午
	 */
	@GetMapping(value = {"/findByName/{name}"})
	public User findByName(@PathVariable String name){
		System.out.println(name);
		System.out.println(userService.findByName(name));
		return userService.findByName(name);
	}


	/**
	 *根据手机号查询用户
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:06 上午
	 */
	@GetMapping(value = {"/findByPhone/{phone}"})
	public User findByPhone(@PathVariable int phone){
		System.out.println(userService.findByPhone(phone));
		return userService.findByPhone(phone);
	}

	/**
	 * 新增一个用户
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:11 上午
	 */
	@PostMapping(value = {"/insertByUser"})
	public boolean insertByUser(@RequestBody User user){
		 return userService.insertByUser(user);
	}

	/**
	 * 更新用户的信息
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:16 上午
	 */

	@PostMapping(value = {"/update"})
	public boolean update(@RequestBody User user){
		return userService.update(user);
	}

	/**
	 * 根据UserId删除一个用户
	 * @param
	 * @return
	 * @auther sunshinezhang
	 * @date 2019/10/24 10:18 上午
	 */
	@DeleteMapping(value = {"/deleteById/{id}"})
	public boolean deleteById(@PathVariable String id){
		return userService.deleteById(id);
	}
}
