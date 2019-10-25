package com.springboot.demo.springboottest.controller;

 import com.google.gson.Gson;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Classname UserControllerTest
 * @Auther sunshinezhang
 * @Date 2019/10/24 15:41
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMybatis
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

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
		user.setId("234889");
		user.setName("sunshine");
		user.setPhone(123344);
	}

	@Test
	public void testFindAllUsers() throws Exception {
		when(userService.findAll()).thenReturn(userList);
		mockMvc.perform(get("/user/findAllUser"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}


	@Test
	public void testFindById() throws Exception {
		when(userService.findById("234889")).thenReturn(user);
		mockMvc.perform(get("/user/findById/{id}", "234889"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();

	}

	@Test
	public void testFindByName() throws Exception {
		when(userService.findByName("sunshine")).thenReturn(user);
		mockMvc.perform(get("/user/findByName/{name}", "sunshine"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}


	@Test
	public void testFindByPhone() throws Exception {
		when(userService.findByPhone(123344)).thenReturn(user);
		mockMvc.perform(get("/user/findByPhone/{phone}", 123344))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}


	@Test
	public void testInsertByUser() throws Exception {
		User user1 = new User();
		user1.setId("234889");
		user1.setName("sunshine2");
		user1.setPhone(123344222);
		Gson gson = new Gson();
		String json = gson.toJson(user1);
		mockMvc.perform(post("/user/insertByUser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}

	@Test
	public void testUpdate() throws Exception {
		User user1 = new User();
		user1.setId("234889");
		user1.setName("sunshine2");
		user1.setPhone(123344222);
		Gson gson = new Gson();
		String json = gson.toJson(user1);
		when(userService.findById("234889")).thenReturn(user);
		mockMvc.perform(post("/user/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/user/deleteById/{id}", "234889"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse();
	}

}
