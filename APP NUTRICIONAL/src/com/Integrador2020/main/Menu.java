package com.Integrador2020.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.Integrador2020.entities.User;

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

	public static void saveRegister(String[] val1, int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("contas.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i< val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int n = 0; n < value.length;n++) {
				value[n]+=encode;
				current+=value[n];
			}
			try {
				write.write(current);
				if(i<val1.length-1) {
					write.newLine();
				}
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
	}
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i< spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "user":
				User.user = spl2[1];
				break;
			case "password":
				User.password = spl2[1];
				break;
			case "weight":
				User.weight = Integer.parseInt(spl2[1]);
				break;
			case "height":
				User.height = Integer.parseInt(spl2[1]);
				break;
			case "genre":
				User.genre = spl2[1];
				break;
			case "name":
				User.name = spl2[1];
				break;
			case "wdcalories":
				User.wdcalories = Integer.parseInt(spl2[1]);
				break;
			case "d1calories":
				User.dcalories[0] = Integer.parseInt(spl2[1]);
				break;
			case "d2calories":
				User.dcalories[1] = Integer.parseInt(spl2[1]);
				break;
			case "d3calories":
				User.dcalories[2] = Integer.parseInt(spl2[1]);
				break;
			case "d4calories":
				User.dcalories[3] = Integer.parseInt(spl2[1]);
				break;
			case "d5calories":
				User.dcalories[4] = Integer.parseInt(spl2[1]);
				break;
			case "d6calories":
				User.dcalories[5] = Integer.parseInt(spl2[1]);
				break;
			case "d7calories":
				User.dcalories[6] = Integer.parseInt(spl2[1]);
				break;
			}
		}
	}
	
	public static String loadRegister(int encode) {
		String line = "";
		File file = new File("contas.txt");
		if(file.exists()) {
			try {
				String singleLine  = null;
				BufferedReader reader = new BufferedReader(new FileReader("contas.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] transition = singleLine.split(":");
						char[] val = transition[1].toCharArray();
						transition[1] = "";
						for(int i = 0; i< val.length;i++) {
							val[i]-= encode;
							transition[1]+=val[i];
						}
						line+= transition[0];
						line+=":";
						line+=transition[1];
						line+="/";
					}
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		
		return line;
	}
	
	public void render(Graphics g) {
		//Titulo
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,36));
		
		g.drawString(Main.Nome, (Main.WIDTH*Main.SCALE) / 2 - 50, (Main.HEIGHT*Main.SCALE) / 2 - 200);
		
		//Opcoes de menu
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,24));
		g.drawString("Entrar", (Main.WIDTH*Main.SCALE) / 2 - 30, 160);
		g.drawString("Registrar-se", (Main.WIDTH*Main.SCALE) / 2 - 60, 200);
		g.drawString("Sair", (Main.WIDTH*Main.SCALE) / 2 - 15, 240);
		
		//Seleção da opção
		if(options[currentOption] == "LOGIN") {
			g.drawString(">", (Main.WIDTH*Main.SCALE) / 2 - 90, 160);
		}else if(options[currentOption] == "SIGN IN") {
			g.drawString(">", (Main.WIDTH*Main.SCALE) / 2 - 90, 200);
		}else if(options[currentOption] == "EXIT") {
			g.drawString(">", (Main.WIDTH*Main.SCALE) / 2 - 90, 240);
		}
	}
}
