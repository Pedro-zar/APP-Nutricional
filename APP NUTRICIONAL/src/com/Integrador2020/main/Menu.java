package com.Integrador2020.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

public class Menu {
	
	public String[] options = { "LOGIN","SIGN IN", "EXIT"};
	public int currentOption = 0, maxOption = options.length-1;
	public boolean down = false, up = false, enter = false;
	public static boolean accountExists = false;
	
	public void tick() {
		File file = new File("contas.txt");
		if(file.exists()) {
			accountExists = true;
		}else {
			accountExists = false;
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
			}else if(options[currentOption] == "LOGIN") {
				if (accountExists == true) {
					//Main.State = "LOGIN";
					/*Login
					 * String saver = loadGame(13);
					applySave(saver);*/
				}else {
					System.out.println("não há cadastros no sistema");
				}
			}else if(options[currentOption] == "EXIT") {
				System.exit(1);
			}
			enter = false;
		}
	}

	
	public void render(Graphics g) {
		//Titulo
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,36));
		
		g.drawString(Main.Nome, (Main.WIDTH) / 2 - 50, (Main.HEIGHT) / 2 - 200);
		
		//Opcoes de menu
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,24));
		g.drawString("Entrar", (Main.WIDTH) / 2 - 30, 160);
		g.drawString("Registrar-se", (Main.WIDTH) / 2 - 60, 200);
		g.drawString("Sair", (Main.WIDTH) / 2 - 15, 240);
		
		//Seleção da opção
		if(options[currentOption] == "LOGIN") {
			g.drawString(">", (Main.WIDTH) / 2 - 90, 160);
		}else if(options[currentOption] == "SIGN IN") {
			g.drawString(">", (Main.WIDTH) / 2 - 90, 200);
		}else if(options[currentOption] == "EXIT") {
			g.drawString(">", (Main.WIDTH) / 2 - 90, 240);
		}
	}
}
