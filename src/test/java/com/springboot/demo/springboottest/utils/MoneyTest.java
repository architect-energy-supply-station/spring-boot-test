package com.springboot.demo.springboottest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Classname MoneyTest
 * @Auther sunshinezhang
 * @Date 2019/10/26 10:44
 */
public class MoneyTest {
	public static void main(String[] args) {
		Random random = new Random();//默认构造方法

		int i = 0;
		int sum = 0;
		List<Integer> list = new ArrayList<>();
		for (int j = 0; j < 19; j++) {
			if (sum < 90000) {
				i = random.nextInt(5000);
				list.add(i) ;

				sum = sum + i;
			}
		}
		list.add(90000 - sum);
		for (Integer integer : list) {
			System.out.println("每一个数据为："+integer);
		}
	}



}
