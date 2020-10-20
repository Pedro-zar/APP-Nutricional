package com.Pedrozar.functionalities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Calendar;

import com.Pedrozar.entities.User;
import com.Pedrozar.main.Main;

public class FunctionalitySelector {

	private String[] opt = new String[3];
	private BufferedImage optionSprite;
	public String[] options = { "DIARY","UPDATE", "LOGOUT"};
	private int currentOption = 0, maxOption = options.length-1;
	public boolean down = false, up = false, enter = false, escape = false;
	
	public FunctionalitySelector() {
		optionSprite = Main.spritesheet.getSprite(64, 64,96,21);
		opt[0] = "Diário";
		opt[1] = "Alterar dados";
		opt[2] = "Logout";
	}
	
	public void tick() {
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0)
				currentOption = maxOption;
		}else if(down){
			currentOption++;
			down = false;
			if(currentOption > maxOption)
				currentOption = 0;
		}else if(enter) {
			if(options[currentOption] == "DIARY"|| options[currentOption] == "UPDATE") 
				Main.State = options[currentOption];
				if(options[currentOption] == "UPDATE")
					Update.criarJanela();
			else if(options[currentOption] == "LOGOUT") 
				Logout.logout();
			enter = false;
		}else if(escape) {
			if(currentOption == 2)
				Logout.logout();
			currentOption = 2;
			escape = false;
		}
	}
	
	public void render(Graphics g) {
		//Texto de bem vindo
		String dayPart, gender;
		if(User.gender == 1)
			gender = "a";
		else
			gender = "o";
		int hour = Calendar.HOUR_OF_DAY;
		if(hour >= 6 && hour <= 12)
			dayPart = "bom dia";
		else if(hour >= 13 && hour <= 18)
			dayPart = "boa tarde";
		else
			dayPart = "boa noite";
		//Fonte e escrita
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.WIDTH / 33));
		g.drawString("Olá, " + dayPart + ". Seja bem vind" + gender + "!", Main.WIDTH / 10, Main.HEIGHT / 10);
		g.drawString("Escolha uma das opções para prosseguir:", (Main.WIDTH / 10), (Main.HEIGHT / 10) * 2);
		
		//opções
		for(int i = 1; i < 4; i++) {
			g.drawImage(optionSprite, Main.WIDTH/6, Main.HEIGHT/6 * (i + 1), (int)(Main.WIDTH/3.125), (int)(Main.HEIGHT/8.57), null);
			g.drawString(opt[i-1], (int)(Main.WIDTH/6 * 1.2), Main.HEIGHT/6 * (i + 1) + (int)(Main.HEIGHT/8.57/1.5));
		}
		g.drawString(">", (int)(Main.WIDTH/6 * 0.7), Main.HEIGHT/6 * (currentOption + 2) + (int)(Main.HEIGHT/8.57/1.5));
		
	}
}