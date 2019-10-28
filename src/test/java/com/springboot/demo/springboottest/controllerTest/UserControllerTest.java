package com.springboot.demo.springboottest.controllerTest;

import com.google.gson.Gson;
import com.springboot.demo.springboottest.controller.UserController;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 任蒙蒙
 * @version 1.0.0
 * @since 2019/10/26 14:51
 */
@WebMvcTest(UserController.class)
@AutoConfigureMybatis
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @MockBean
//    private UserDao userDao;

    List<User> userList = new ArrayList<>();

    User user = new User();

    @Before
     void initTestUserListData() {
        User user = new User();
        user.setName("mm");
        user.setId("234");
        user.setPhone(1234);
        userList.add(user);
    }

    @Before
     void initTestUserData() {
        user.setId("234889");
        user.setName("ll");
        user.setPhone(123344);
    }

    @Test
     void findAllUsersTest() throws Exception {
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/user/findAllUser")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void findByIdTest() throws Exception {
        when(userService.findById("234889")).thenReturn(user);
        mockMvc.perform(get("/user/findById/{id}", "234889")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void findByNameTest() throws Exception {
        when(userService.findByName("mm")).thenReturn(user);
        mockMvc.perform(get("/user/findByName/{name}", "mm")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void findByPhoneTest() throws Exception {
        when(userService.findByPhone(123344)).thenReturn(user);
        mockMvc.perform(get("/user/findByPhone/{phone}", 123344)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void insertByUserTest() throws Exception {
        User user = new User();
        user.setId("678");
        user.setName("mmll");
        user.setPhone(6688);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        mockMvc.perform(post("/user/insertByUser").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void updateTest() throws Exception {
        User user = new User();
        user.setId("678");
        user.setName("mmll");
        user.setPhone(6688);
        Gson gson = new Gson();
        String json = gson.toJson(user);
//        todo
        when(userService.findById("678")).thenReturn(user);
        mockMvc.perform(post("/user/update").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
     void deleteById() throws Exception {
        when(userService.deleteById("234889")).thenReturn(true);
        mockMvc.perform(delete("/user/deleteById/{id}", "234889")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse();
    }
}

