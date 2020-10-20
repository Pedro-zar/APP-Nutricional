package com.Integrador2020.functionalities;

import com.Integrador2020.entities.User;
import com.Integrador2020.main.Main;

public class Logout {

	public static void logout()
	{
		User.height = 0; 
		User.weight = 0;
		User.wdcalories = 0;
		User.age = 0;
		User.gender = 0; 
		User.password = 0;
		User.user = 0;
		for(int i = 0; i < 7; i++) 
			User.dcalories[i] = 0;
		Main.State = "MENU";
		Main.accountSelecter.STATE = 1;
	}
	
}
