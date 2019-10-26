package com.springboot.demo.springboottest.dao;

import com.springboot.demo.springboottest.model.User;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Classname UserDaoTest
 * @Auther sunshinezhang
 * @Date 2019/10/24 15:45
 */
@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private Flyway flyway;

	@Test
	public void findAll() {
		Collection<User> users = userDao.findAll();
		assertThat(users).hasSize(8);
	}


	@Test
	public void testFindByName() {
		User user = userDao.findByName("张三");
		assertThat(user).isNotNull();
	}

	@Test
	public void testFindById() {
		User user = userDao.findById("s08");
		assertThat(user).isNotNull();
	}

	@Test
	public void testFindByPhone() {
		User user = userDao.findByPhone(1234);
		assertThat(user).isNotNull();
	}

	@Test
	@Transactional
	public void testInsertByUser() {
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
	public void testUpdate() {
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
	public void testDeleteById() {
		User user = userDao.findById("s02");
		assertThat(user).isNotNull();

		userDao.deleteById("s02");

		user = userDao.findById("s02");
		assertThat(user).isNull();
	}

	@Test
	public void testExistsUser() {
		Boolean aBoolean = userDao.existsUser(54365);
		assertThat(aBoolean).isTrue();
	}

	@After
	public void cleanDb() {
		flyway.clean();
	}
}
