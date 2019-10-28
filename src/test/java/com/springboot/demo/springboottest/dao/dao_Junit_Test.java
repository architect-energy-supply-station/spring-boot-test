package com.springboot.demo.springboottest.dao;

import com.springboot.demo.springboottest.model.User;

import com.springboot.demo.springboottest.service.UserService;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@DataJpaTest
public class dao_Junit_Test {

    @Autowired
    private UserDao userDao;

//@Autowired
    public static Flyway flyway ;

    @BeforeClass
    public static void setUp() {
        flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:mem:h2test;DB_CLOSE_DELAY=-1;MODE=MySQL", "root", "root");
    }

    @Test
    public void findAll_Test() {
        List<User> all = userDao.findAll();
        int size = all.size();
        Assert.assertEquals(size,8);
    }

    @Test
    public void findById_Test() {
        User userDaoById = userDao.findById("s01");
        Assert.assertEquals(userDaoById.getId(),"s01");
        Assert.assertEquals(userDaoById.getPhone(),26);
        Assert.assertEquals(userDaoById.getName(),"张三");
    }

    @Test
    public void findName_Test() {
        User userDaoByName = userDao.findByName("张三");
        Assert.assertEquals(userDaoByName.getId(),"s01");
        Assert.assertEquals(userDaoByName.getPhone(),26);
        Assert.assertEquals(userDaoByName.getName(),"张三");
    }

    @Test
    public void findByPhone_Test() {
        User userDaoByPhone = userDao.findByPhone(26);
        Assert.assertEquals(userDaoByPhone.getId(),"s01");
        Assert.assertEquals(userDaoByPhone.getPhone(),26);
        Assert.assertEquals(userDaoByPhone.getName(),"张三");
    }

    @Test
    public void existsUser_Test() {
        Boolean aBoolean = userDao.existsUser(26);
        Assert.assertTrue(aBoolean);
    }

    @Test
    @Transactional
    public void update_Test() {
        User userDaoByPhone = userDao.findByPhone(26);
        userDaoByPhone.setName("王五");
        userDao.update(userDaoByPhone);
        userDaoByPhone = userDao.findByPhone(26);
        Assert.assertEquals(userDaoByPhone.getName(),"王五");
    }

    @Test
    @Transactional
    public void insertByUser_Test() {
        List<User> userDaoAll = userDao.findAll();
        int size = userDaoAll.size();
        User user = new User();
        user.setName("美丽");
        user.setId("11");
        user.setPhone(12);
        boolean b = userDao.insertByUser(user);
        assertThat(b).isTrue();

    }

    @Test
    @Transactional
    public void deleteById_Test() {
        List<User> userDaoAll = userDao.findAll();
        int size = userDaoAll.size();
        boolean b = userDao.deleteById("s01");
        assertThat(b).isEqualTo(true);


    }

    @AfterClass
    public static void afterClass(){
        flyway.clean();
    }
}
