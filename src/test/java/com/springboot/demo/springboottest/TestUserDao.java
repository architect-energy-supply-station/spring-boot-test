package com.springboot.demo.springboottest;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import com.springboot.demo.springboottest.service.UserService;
import com.springboot.demo.springboottest.service.impl.UserServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

//@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
@SpringBootTest(classes = {UserDao.class})
@AutoConfigureMybatis
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
@RunWith(MockitoJUnitRunner.class)
public class TestUserDao extends AbstractTestNGSpringContextTests {
    //
//    @Mock
//    private UserDao userDao;
//    @Mock // 定义 dao 层对象
//    private UserMapper userMapper;
//    @InjectMocks
//    private UserService userService = new UserServiceImpl();



//        @Autowired
//    @Qualifier("userDao")
//    @Mock
    @Resource
    private UserDao userDao;


    private User user=new User();

    private List<User> list=new ArrayList<> ();

    @BeforeMethod
    public void initUser() {
        this.user = new User();
        this.user.setId("123");
        user.setPhone(123456);
        user.setName("Chris");
        this.list.add(user);
    }


    @Test
    public void testFindById() {
//        assertNotNull(userDao);
//        user = userDao.findById("123");
//        assertNotNull(user.getId());
//        User user1 = userDao.findById("s01");
//        assertNotNull(user1);
//        assertEquals(userDao.findById("123"),user);

//        assertNotNull(userDao) ;
//        user = userDao.registerUser(user);
//        assertNotNull(user.getId()) ;
////        assertEquals(user,userDao.findById("123"));
//        when(userDao.findById("123")).thenReturn(user);
//        assertEquals(user,userDao.findById("123"));




    }

//    @Test
//    public void testInsertUser() {
//        assertEquals(true,userDao.insertByUser(user));
//    }

    @Test
    public void testFindByName() {
//        assertEquals(userDao.findByName("Chris"),user);
    }

    @Test
    public void testFindByPhone() {
//        assertEquals(userDao.findByPhone(123456), user);
    }

    @Test
    public void testFindAllUser() {
//        List<User> list = new ArrayList<>();
//        list.add(user);
//        assertEquals(list.equals(user),true);
    }

    @Test
    public void testUpdate() {
//        user.setId("6688");
//        System.out.println("userId" + user.getId());
//        assertEquals(userDao.update(user), true);
    }

    @Test
    public void testDeleteId() {
//        User user1 = new User();
//        user1.setId("456");
//        user1.setName("SunShine");
//        user1.setPhone(7890);
//        userDao.insertByUser(user1);
//        assertEquals(userDao.deleteById("456"), true);
    }
}
//@SpringBootApplication(scanBasePackageClasses = UserDao.class)
