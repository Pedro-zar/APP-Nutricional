package com.Integrador2020.entities;

public class User {

	public static int height, weight, wdcalories, age;
	public static String gender, user, name, password;
	public static int[] dcalories;

	public User(int height, int weight, String gender, String user, String name, String password, int age) {
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.user = user;
		this.age = age;
		this.password = password;
	}

}
