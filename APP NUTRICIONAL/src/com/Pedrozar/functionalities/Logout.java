package com.Pedrozar.functionalities;

import com.Pedrozar.entities.User;
import com.Pedrozar.main.Main;
import com.Pedrozar.sign.AccountSelecter;

public class Logout {

	public static void logout()
	{
		User.setHeight(0); 
		User.setWeight(0);
		User.setWdcalories(0);
		User.setAge(0);
		User.setGender(0); 
		User.setPassword(0);
		User.setUser(0);
		for(int i = 0; i < 7; i++) 
			User.getDcalories()[i] = 0;
		Main.setState("MENU");
		AccountSelecter.setSTATE(1);
	}
}