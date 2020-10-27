package com.pedrozar.functionalities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Calendar;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;
import com.pedrozar.main.Save;
import com.pedrozar.sign.AccountSelecter;

public class FunctionalitySelector {

	private String[] opt = new String[3];
	private BufferedImage optionSprite;
	private String[] options = { "DIARY","UPDATE", "LOGOUT"};
	private int currentOption = 0, maxOption = options.length-1;
	private boolean down = false, up = false, enter = false, escape = false;
	
	public FunctionalitySelector() {
		optionSprite = Main.getSpritesheet().getSprite(64, 64,96,21);
		opt[0] = "Diário";
		opt[1] = "Alterar dados";
		opt[2] = "Logout";
	}
	
	public void tick() {
		if(User.getFirstLogin() == 0) {
		
			tutorialEnd();
		}else {
			if(isUp()) {
				currentOption--;
				setUp(false);
				if(currentOption < 0)
					currentOption = maxOption;
			}else if(isDown()){
				currentOption++;
				setDown(false);
				if(currentOption > maxOption)
					currentOption = 0;
			}else if(isEnter()) {
				if(options[currentOption] == "DIARY"
						|| options[currentOption] == "UPDATE") 
					Main.setState(options[currentOption]);
				if(options[currentOption] == "UPDATE")
					Update.criarJanela();
				else if(options[currentOption] == "LOGOUT") 
					AccountSelecter.logout();
				setEnter(false);
			}else if(isEscape()) {
				if(currentOption == 2)
					AccountSelecter.logout();
				currentOption = 2;
				setEscape(false);
			}
		}
	}
	
	private void tutorialEnd() {
		User.setFirtLogin(1);
		String[] opt1 = {"user", "password", "gender", "age","weight", 
				"height", "firstLogin"};
		int[] opt2 = {User.getUser(), User.getPassword(), User.getGender(), 
				User.getAge(), User.getWeight(), User.getHeight(), 1};
		Save.saveRegister(opt1, opt2, 13, User.getUser());	
	}


	public void render(Graphics g) {
		//Texto de bem vindo
		String dayPart, gender;
		if(User.getGender() == 1)
			gender = "a";
		else
			gender = "o";
		int hour = Calendar.HOUR_OF_DAY;
		for(int i = 0; i < 4; i++) {
			if(hour == 23)
				hour = 0;
			else 
				hour++;
		}
		if(hour >= 6 && hour <= 12)
			dayPart = "bom dia";
		else if(hour >= 13 && hour <= 18)
			dayPart = "boa tarde";
		else
			dayPart = "boa noite";
		//Fonte e escrita
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 33));
		g.drawString("Olá, " + dayPart + ". Seja bem vind" + gender + "!", 
				Main.getWIDTH() / 10, Main.getHEIGHT() / 10);
		g.drawString("Escolha uma das opções para prosseguir:", (Main.getWIDTH() / 10), 
				(Main.getHEIGHT() / 10) * 2);
		
		//opções
		for(int i = 1; i < 4; i++) {
			g.drawImage(optionSprite, Main.getWIDTH()/6, Main.getHEIGHT()/6 * (i + 1), 
					(int)(Main.getWIDTH()/3.125), (int)(Main.getHEIGHT()/8.57), null);
			g.drawString(opt[i-1], (int)(Main.getWIDTH()/6 * 1.2), 
					Main.getHEIGHT()/6 * (i + 1) + (int)(Main.getHEIGHT()/8.57/1.5));
		}
		g.drawString(">", (int)(Main.getWIDTH()/6 * 0.7), Main.getHEIGHT()/6 
				* (currentOption + 2) + (int)(Main.getHEIGHT()/8.57/1.5));
		
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isEscape() {
		return escape;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}
}