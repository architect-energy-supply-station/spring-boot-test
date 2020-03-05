package com.springboot.demo.springboottest.controller;

import com.google.gson.Gson;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMybatis
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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
        user.setId("123123");
        user.setName("betty");
        user.setPhone(121221);
    }

    @Test
    void testFindAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/user/findAllUser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }


    @Test
    void testFindById() throws Exception {
        when(userService.findById("123123")).thenReturn(user);
        mockMvc.perform(get("/user/findById/{id}", "123123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

    }

    @Test
    void testFindByName() throws Exception {
        when(userService.findByName("betty")).thenReturn(user);
        mockMvc.perform(get("/user/findByName/{name}", "betty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }


    @Test
    void testFindByPhone() throws Exception {
        when(userService.findByPhone(123123)).thenReturn(user);
        mockMvc.perform(get("/user/findByPhone/{phone}", 123123))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }


    @Test
    void testInsertByUser() throws Exception {
        User user1 = new User();
        user1.setId("123123");
        user1.setName("betty");
        user1.setPhone(1231231321);
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
    void testUpdate() throws Exception {
        User user1 = new User();
        user1.setId("123123");
        user1.setName("betty");
        user1.setPhone(1231213213);
        Gson gson = new Gson();
        String json = gson.toJson(user1);
        when(userService.findById("betty")).thenReturn(user);
        mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/user/deleteById/{id}", "123123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

}
