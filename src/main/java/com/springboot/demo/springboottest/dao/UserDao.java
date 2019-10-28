package com.springboot.demo.springboottest.dao;

import com.springboot.demo.springboottest.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserDao
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:39
 */

@Mapper
@Repository("userDao")
public interface UserDao {

	//查询所有的人员
	List<User> findAll();

	//根据Id查询人员
	User findById(@Param(value="id") String id);

	//根据姓名查询人员
	User findByName(@Param(value="name") String name);

	//根据手机号查询人员
	User findByPhone(@Param(value="phone") int phone);

	//新增
	Boolean insertByUser(@Param("user") User user);

	//删除
	Boolean deleteById(@Param(value = "id") String id);

	//更新
	Boolean update(@Param("user") User user);

	//查询人员是否存在
	Boolean existsUser(@Param(value="phone") int phone);

}
