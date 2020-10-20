package com.Pedrozar.functionalities;

import com.Pedrozar.entities.User;
import com.Pedrozar.main.Main;
import com.Pedrozar.sign.AccountSelecter;

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
		AccountSelecter.STATE = 1;
	}
	
}
