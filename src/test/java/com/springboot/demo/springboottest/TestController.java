package com.springboot.demo.springboottest;

import com.google.gson.Gson;
import com.springboot.demo.springboottest.controller.UserController;
import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {UserController.class})
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class TestController extends AbstractTestNGSpringContextTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDao userDao;
    private User user;

    private List<User> list;

    @BeforeMethod
    public void initUser() {
        user = new User();
        user.setId("123");
        user.setPhone(123456);
        user.setName("Chris");
        User user1 = new User();
        user1.setId("345");
        user1.setPhone(123456);
        user1.setName("bill");

        list = new ArrayList<>();
        list.add(user);
        list.add(user1);
    }


    @Test
    public void testFindById() throws Exception {
        when(userService.findById("123")).thenReturn(user);
        this.mvc.perform(get("/user/findById/{id}", "123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByName() throws Exception {
        when(userService.findByName("Chris")).thenReturn(user);
        this.mvc.perform(get("/user/findByName/{name}", "Chris"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByPhone() throws Exception {
        when(userService.findByPhone(123456)).thenReturn(user);
        this.mvc.perform(get("/user/findByPhone/{phone}", 123456))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAll() throws Exception {
        when(userService.findAll()).thenReturn(list);
        this.mvc.perform(get("/user/findAllUser"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Gson gson = new Gson();

        this.mvc.perform(post("/user/update").contentType((MediaType.APPLICATION_JSON)).content(gson.toJson(user)))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        when(userService.deleteById("123")).thenReturn(true);
        this.mvc.perform(delete("/user/deleteById/{id}", "123"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void testInsertUser() throws Exception {

        this.mvc.perform(post("/user/insertByUser").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"id\": \"demoData\",\n" +
                "  \"name\": \"demoData\",\n" +
                "  \"phone\": 1\n" +
                "}")).andExpect(status().isOk()).andDo(print()).andReturn();
    }
}