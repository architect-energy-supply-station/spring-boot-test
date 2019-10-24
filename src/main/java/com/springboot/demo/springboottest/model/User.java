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

}
