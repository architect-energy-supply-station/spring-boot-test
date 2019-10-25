package com.springboot.demo.springboottest.controller;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes ={UserController.class} )
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class UserController_Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @MockBean
//    private User user;

    @Test
    public void testController() throws Exception {
        User user = new User();
        user.setId("s01");
        user.setName("张三");
        user.setPhone(26);

        when(userService.findById("s01")).thenReturn(user);

        this.mockMvc.perform(get("/user/findById").param("id", "s01"))
        .andExpect(status().isOk())
        .andExpect(content().string(""))

        ;

    }
}
