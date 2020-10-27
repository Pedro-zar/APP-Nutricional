package com.pedrozar.functionalities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;
import com.pedrozar.sign.AccountSelecter;

public class FunctionalitySelector {

	private String[] opt = new String[3];
	private String[][] optionText = new String[3][3];
	private BufferedImage optionSprite;
	private String[] options = {"DIARY","UPDATE", "LOGOUT"};
	private int currentOption = 0, maxOption = options.length-1;
	private boolean down = false, up = false, enter = false, escape = false;
	
	public FunctionalitySelector() {
		optionSprite = Main.getSpritesheet().getSprite(64, 64,96,21);
		
		opt[0] = "Diário";
		opt[1] = "Alterar dados";
		opt[2] = "Logout";
		
		optionText[0][0] = "Aqui será onde você";
		optionText[0][1] = "poderá cuidar de sua";
		optionText[0][2] = "alimentação e calorias!";
		
		optionText[1][0] = "Inseriu algum dado errado?";
		optionText[1][1] = "Aqui você poderá";
		optionText[1][2] = "alterar seu registro";
		
		optionText[2][0] = "";
		optionText[2][1] = "";
		optionText[2][2] = "";
	}
	
	public void tick() {
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

	public void render(Graphics g) {
		//Texto de bem vindo
		String gender;
		if(User.getGender() == 1)
			gender = "a";
		else
			gender = "o";
	
		//Fonte e escrita
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 33));
		g.drawString("Olá., seja bem vind" + gender + "!", Main.getWIDTH() / 10, 
				Main.getHEIGHT() / 10);
		g.drawString("Escolha uma das opções para prosseguir:", (Main.getWIDTH() / 10), 
				(Main.getHEIGHT() / 10) * 2);
		
		//opções
		for(int i = 1; i < 4; i++) {
			g.drawImage(optionSprite, Main.getWIDTH()/6, Main.getHEIGHT()/6 * (i + 1), 
					(int)(Main.getWIDTH()/3.125), (int)(Main.getHEIGHT()/8.57), null);
			g.drawString(opt[i-1], (int)(Main.getWIDTH()/6 * 1.2), 
					Main.getHEIGHT() / 6 * (i + 1) + (int)(Main.getHEIGHT()/8.57/1.5));
		}
		g.drawString(">", (int)(Main.getWIDTH()/6 * 0.7), Main.getHEIGHT()/6 
				* (currentOption + 2) + (int)(Main.getHEIGHT()/8.57/1.5));
		
		for(int i = 0; i < 3; i++) 
			g.drawString(optionText[currentOption][i], (int)(Main.getWIDTH() * 0.6), 
					Main.getHEIGHT() / 6 * 2 + (Main.getHEIGHT() / 10 * i) 
					+ (int)(Main.getHEIGHT()/8.57/1.5));
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