package com.pedrozar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import com.pedrozar.sign.AccountSelecter;

public class Menu {
	
	private static boolean accountExists = false;
	private String[] options = { "LOGIN","SIGN IN", "EXIT"};
	private int currentOption = 0, maxOption = options.length-1;
	private boolean down = false, up = false, enter = false;
	
	public void tick() {
		for(int i = 1; i <= 3 ; i++) {
			File file = new File("contas"+i+".txt");
			if(!(file.exists()) && accountExists == false) {
				accountExists = false;
			}else {
				accountExists = true;
			}	
		}
		if(isUp()) {
			currentOption--;
			setUp(false);
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}else if(isDown()){
			currentOption++;
			setDown(false);
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}else if(isEnter()) {
			if(options[currentOption] == "SIGN IN") {
				Main.setState("SIGNIN");
				AccountSelecter.setState(Main.getState());
			}else if(options[currentOption] == "LOGIN") {
				if (accountExists == true) {
					Main.setState("LOGIN");
					AccountSelecter.setState(Main.getState());
				}else {
					System.out.println("não há cadastros no sistema");
				}
			}else if(options[currentOption] == "EXIT") {
				System.exit(1);
			}
			setEnter(false);
		}
	}

	
	public void render(Graphics g) {
		//Titulo
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 33));
		g.drawString(Main.getNome(), (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 8),
				(Main.getHEIGHT()) / 2 - (Main.getHEIGHT()/3));
		
		//Opcoes de menu
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 50));
		g.drawString("Entrar", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 15), 
				(int) (Main.getHEIGHT() / 3.75));
		g.drawString("Registrar-se", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 15), 
				Main.getHEIGHT() / 3);
		g.drawString("Sair", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 15), 
				(int) (Main.getHEIGHT() / 2.5));
		
		//Seleção da opção
		if(options[currentOption] == "LOGIN") {
			g.drawString(">", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 10), 
					(int) (Main.getHEIGHT()/3.75));
		}else if(options[currentOption] == "SIGN IN") {
			g.drawString(">", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 10), 
					Main.getHEIGHT()/3);
		}else if(options[currentOption] == "EXIT") {
			g.drawString(">", (Main.getWIDTH()) / 2 - (Main.getWIDTH() / 10),
					(int) (Main.getHEIGHT()/2.5));
		}
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
}
