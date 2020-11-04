package com.pedrozar.entities;

public class User {
	//save 1
	private static int height, weight, age, gender; //1 = F, 0 = M
	private static int password, user, firstLogin; //1 = true, 0 = false;
	//save 2
	private static int wdcalories, dcalories, wWeight;//w = wanted 
	private static int protDivision, carbDivision, fatDivision; //prot fat carb
	private static String[] consumedList = new String[23];

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
	
	public static int getDcalories() {
		return dcalories;
	}
	
	public static void setDcalories(int dcalories) {
		User.dcalories = dcalories;
	}
	
	public static void setFirtLogin(int firstLogin) {
		User.firstLogin = firstLogin;
	}
	
	public static int getFirstLogin() {
		return User.firstLogin;
	}

	public static int getwWeight() {
		return wWeight;
	}

	public static void setwWeight(int wWeight) {
		User.wWeight = wWeight;
	}

	public static int getProtDivision() {
		return protDivision;
	}

	public static void setProtDivision(int protDivision) {
		User.protDivision = protDivision;
	}

	public static int getCarbDivision() {
		return carbDivision;
	}

	public static void setCarbDivision(int carbDivision) {
		User.carbDivision = carbDivision;
	}

	public static int getFatDivision() {
		return fatDivision;
	}

	public static void setFatDivision(int fatDivision) {
		User.fatDivision = fatDivision;
	}

	public static String[] getConsumedList() {
		return consumedList;
	}

	public static void setConsumedList(String[] consumedList) {
		User.consumedList = consumedList;
	}

}