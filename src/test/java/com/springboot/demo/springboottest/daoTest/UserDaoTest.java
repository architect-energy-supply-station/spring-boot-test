package com.springboot.demo.springboottest.daoTest;

import com.springboot.demo.springboottest.dao.UserDao;
import com.springboot.demo.springboottest.model.User;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 任蒙蒙
 * @version 1.0.0
 * @since 2019/10/25 10:30
 */

//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@SpringBootTest
@AutoConfigureMybatis
 class UserDaoTest {
    @Autowired
    private UserDao userDao;

    private static  Flyway flyway;

    @BeforeClass
     static void setUp() {
        flyway = Flyway.configure().dataSource("jdbc:h2:mem:h2test;DB_CLOSE_DELAY=-1;MODE=MySQL", "root", "root").load();
    }


    @Test
     void findAllTest() {
        Collection<User> users = this.userDao.findAll();
        assertThat(users).hasSize(7);
    }

    @Test
     void findByIdTest() {
        User user = this.userDao.findById("s01");
        assertThat(user.getName()).startsWith("张三");
        assertThat(user.getPhone()).isEqualTo(26);
    }

    @Test
     void findByNameTest() {
        User user = this.userDao.findByName("张三");
        assertThat(user.getId()).isEqualTo("s01");
        assertThat(user.getPhone()).isEqualTo(26);
    }

    @Test
     void findByPhoneTest() {
        User user = this.userDao.findByPhone(26);
        assertThat(user.getId()).isEqualTo("s01");
        assertThat(user.getName()).startsWith("张三");
    }

    @Test
    @Transactional
     void insertByUserTest() {
        Collection<User> users = userDao.findAll();
        int found = users.size();

        User user = new User();
        user.setId("123");
        user.setName("mm");
        user.setPhone(182324);
        userDao.insertByUser(user);

        users = userDao.findAll();
        assertThat(users.size()).isEqualTo(found + 1);
    }

    @Test
     void deleteByIdTest() {
        User user = userDao.findById("s03");
        assertThat(user).isNotNull();

        userDao.deleteById("s03");

        user = userDao.findById("s03");
        assertThat(user).isNull();
    }

    @Test
     void updateTest() {
        User user = this.userDao.findById("s02");
        String userName = user.getName();
        String newUserName = userName + "mm";
        user.setName(newUserName);
        this.userDao.update(user);

        this.userDao.findById("s02");
        assertThat(user.getName()).isEqualTo(newUserName);
    }

    @Test
     void existsUserTest() {
        Boolean existed = userDao.existsUser(54365);
        assertThat(existed).isTrue();
    }

    @AfterClass
    static void afterClass() {
        System.out.println("--afterClass");
        flyway.clean();
    }
}
