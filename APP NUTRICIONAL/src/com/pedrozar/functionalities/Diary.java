package com.pedrozar.functionalities;

import java.awt.Graphics;

import com.pedrozar.entities.User;
import com.pedrozar.main.Save;

public class Diary {

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		
	}
	
	private void startTutorial() {
		
	}
	
	private void endTutorial() {
		User.setFirtLogin(1);
		String[] opt1 = {"user", "password", "gender", "age","weight", 
				"height", "firstLogin"};
		int[] opt2 = {User.getUser(), User.getPassword(), User.getGender(), 
				User.getAge(), User.getWeight(), User.getHeight(), 1};
		Save.saveRegister(opt1, opt2, 13, User.getUser());	
	}

}
