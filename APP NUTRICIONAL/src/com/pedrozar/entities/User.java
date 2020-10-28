package com.pedrozar.entities;

public class User {

	private static int height, weight, wdcalories, age, gender; //1 = F, 0 = M
	private static int password, user, firstLogin; //1 = true, 0 = false;
	private static int[] dcalories = new int[7], proteinsDivision = new int[3]; //prot fat carb
	
	public static int getGender() {
		return gender;
	}
	
	public static void setGender(int gender) {
		User.gender = gender;
	}
	
	public static int getAge() {
		return age;
	}
	
	public static void setAge(int age) {
		User.age = age;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static void setHeight(int height) {
		User.height = height;
	}
	
	public static int getWeight() {
		return weight;
	}
	
	public static void setWeight(int weight) {
		User.weight = weight;
	}
	
	public static int getPassword() {
		return password;
	}
	
	public static void setPassword(int password) {
		User.password = password;
	}
	
	public static int getUser() {
		return user;
	}
	
	public static void setUser(int user) {
		User.user = user;
	}
	
	public static int getWdcalories() {
		return wdcalories;
	}
	
	public static void setWdcalories(int wdcalories) {
		User.wdcalories = wdcalories;
	}
	
	public static int[] getDcalories() {
		return dcalories;
	}
	
	public static void setDcalories(int[] dcalories) {
		User.dcalories = dcalories;
	}
	
	public static void setFirtLogin(int firstLogin) {
		User.firstLogin = firstLogin;
	}
	
	public static int getFirstLogin() {
		return User.firstLogin;
	}

	public static int[] getProteinsDivision() {
		return proteinsDivision;
	}

	public static void setProteinsDivision(int[] proteinsDivision) {
		User.proteinsDivision = proteinsDivision;
	}
}