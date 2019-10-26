package com.springboot.demo.springboottest.controller;

import com.google.gson.Gson;
import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
//@ContextConfiguration(classes ={UserController.class} )
//@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
//@AutoConfigureMybatis
public class UserController_Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDao userDao;

    //    @MockBean
    User user = new User();

    List<User> userList = new ArrayList<>();


    @Before
    public void initTest() {
        user.setId("s01");
        user.setName("张三");
        user.setPhone(26);
        userList.add(user);
    }

    @Test
    public void findAllUser_CTest() throws Exception {

        when(userService.findAll()).thenReturn(userList);
        this.mockMvc.perform(get("/user/findAllUser"))

                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
        ;

    }

    @Test
    public void findById_CTest() throws Exception {
        when(userService.findById("s01")).thenReturn(user);
//        System.out.println(userService.findById("s01")+""+ user);
        mockMvc.perform(get("/user/findById/{id}", "s01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

    }

    @Test
    public void findByName_CTest() throws Exception {
        when(userService.findByName("张三")).thenReturn(user);
//        System.out.println(userService.findByName("张三")+""+user);
        mockMvc.perform(get("/user/findByName/{name}", "张三"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    public void findByPhone_CTest() throws Exception {
        when(userService.findByPhone(26)).thenReturn(user);
//                 System.out.println(userService.findByPhone(26)+""+user);
        mockMvc.perform(get("/user/findByPhone/{phone}", 26))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    public void insertByUser_CTest() throws Exception {
        User userTwo = new User();
        userTwo.setPhone(11);
        userTwo.setName("十一");
        userTwo.setId("1");
        Gson gson = new Gson();
        String  userTwoToJson = gson.toJson(userTwo);
        userList.add(userTwo);
        when(userService.insertByUser(userTwo)).thenReturn(true);
        mockMvc.perform(post("/user/insertByUser").contentType(MediaType.APPLICATION_JSON)
                .content(userTwoToJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void update_CTest() throws Exception {

        user.setPhone(11);
        user.setName("十一");
        user.setId("1");
        Gson gson = new Gson();
        String json = gson.toJson(user);
//            when(userService.update(user)).thenReturn(true);
        mockMvc.perform(post("/user/update").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById_CTest() throws Exception {
        when(userService.deleteById("s01")).thenReturn(true);
        mockMvc.perform(delete("/user/deleteById/{id}", "s01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }


}
