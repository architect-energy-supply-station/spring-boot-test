package com.springboot.demo.springboottest.dao;

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
 * @Classname UserDaoTest
 * @Auther sunshinezhang
 * @Date 2019/10/24 15:45
 */
@SpringBootTest
@AutoConfigureMybatis
class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private Flyway flyway;

	@BeforeClass
	 void setUp() {
		flyway = Flyway.configure().dataSource("jdbc:h2:mem:h2test;DB_CLOSE_DELAY=-1;MODE=MySQL", "root", "root").load();

	}

	@Test
	void findAll() {
		Collection<User> users = userDao.findAll();
		assertThat(users).hasSize(8);
	}

	@Test
	void testFindByName() {
		User user = userDao.findByName("张三");
		assertThat(user).isNotNull();
	}

	@Test
	void testFindById() {
		User user = userDao.findById("s08");
		assertThat(user).isNotNull();
	}

	@Test
	void testFindByPhone() {
		User user = userDao.findByPhone(1234);
		assertThat(user).isNotNull();
	}

	@Test
	@Transactional
	void testInsertByUser() {
		Collection<User> users = userDao.findAll();
		int found = users.size();

		User user = new User();
		user.setId("18");
		user.setName("sunshine");
		user.setPhone(1833364);
		userDao.insertByUser(user);

		users = userDao.findAll();
		assertThat(users.size()).isEqualTo(found + 1);
	}


	@Test
	@Transactional
	void testUpdate() {
		User user = userDao.findById("s08");
		String oldUserName = user.getName();
		String newUserName = oldUserName + "X";
		user.setName(newUserName);
		userDao.update(user);

		user = userDao.findById("s08");
		assertThat(user.getName()).isEqualTo(newUserName);
	}


	@Test
	@Transactional
	void testDeleteById() {
		User user = userDao.findById("s02");
		assertThat(user).isNotNull();

		userDao.deleteById("s02");

		user = userDao.findById("s02");
		assertThat(user).isNull();
	}

	@Test
	void testExistsUser() {
		Boolean aBoolean = userDao.existsUser(54365);
		assertThat(aBoolean).isTrue();
	}


	@AfterClass
	 void afterClass() {
		System.out.println("-------------------afterClass");
		flyway.clean();
	}
}
