package com.Pedrozar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import com.Pedrozar.sign.AccountSelecter;

public class Menu {
	
	public String[] options = { "LOGIN","SIGN IN", "EXIT"};
	public int currentOption = 0, maxOption = options.length-1;
	public boolean down = false, up = false, enter = false;
	public static boolean accountExists = false;
	
	public void tick() {
		for(int i = 1; i <= 3 ; i++) {
			File file = new File("contas"+i+".txt");
			if(!(file.exists()) && accountExists == false) {
				accountExists = false;
			}else {
				accountExists = true;
			}	
		}
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}else if(down){
			currentOption++;
			down = false;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}else if(enter) {
			if(options[currentOption] == "SIGN IN") {
				Main.State = "SIGNIN";
				AccountSelecter.State = Main.State;
			}else if(options[currentOption] == "LOGIN") {
				if (accountExists == true) {
					Main.State = "LOGIN";
					AccountSelecter.State = Main.State;
				}else {
					System.out.println("n�o h� cadastros no sistema");
				}
			}else if(options[currentOption] == "EXIT") {
				System.exit(1);
			}
			enter = false;
		}
	}

	
	public void render(Graphics g) {
		//Titulo
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.WIDTH / 33));
		g.drawString(Main.Nome, (Main.WIDTH) / 2 - (Main.WIDTH / 8), (Main.HEIGHT) / 2 - (Main.HEIGHT/3));
		
		//Opcoes de menu
		g.setFont(new Font("arial",Font.BOLD, Main.WIDTH / 50));
		g.drawString("Entrar", (Main.WIDTH) / 2 - (Main.WIDTH / 15), (int) (Main.HEIGHT / 3.75));
		g.drawString("Registrar-se", (Main.WIDTH) / 2 - (Main.WIDTH / 15), Main.HEIGHT / 3);
		g.drawString("Sair", (Main.WIDTH) / 2 - (Main.WIDTH / 15), (int) (Main.HEIGHT / 2.5));
		
		//Sele��o da op��o
		if(options[currentOption] == "LOGIN") {
			g.drawString(">", (Main.WIDTH) / 2 - (Main.WIDTH / 10), (int) (Main.HEIGHT/3.75));
		}else if(options[currentOption] == "SIGN IN") {
			g.drawString(">", (Main.WIDTH) / 2 - (Main.WIDTH / 10), Main.HEIGHT/3);
		}else if(options[currentOption] == "EXIT") {
			g.drawString(">", (Main.WIDTH) / 2 - (Main.WIDTH / 10),(int) (Main.HEIGHT/2.5));
		}
	}
}
