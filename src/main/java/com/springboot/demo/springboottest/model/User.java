package com.springboot.demo.springboottest.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname User
 * @Auther sunshinezhang
 * @Date 2019/10/24 09:39
 */
@Data
public class User implements Serializable {

	private String id;

	private String name;

	private int phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}
