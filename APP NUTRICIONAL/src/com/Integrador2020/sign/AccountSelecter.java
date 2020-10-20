package com.Integrador2020.sign;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JOptionPane;

import com.Integrador2020.main.Main;

public class AccountSelecter {
	
	public CampoRegistro campo;
	private static BufferedImage[] PERFIL = new BufferedImage[3];	
	private final String[] options = { "1","2", "3"};
	public int currentOption = 0, maxOption = options.length-1, slot = 0;
	public boolean left = false, right = false, enter = false;
	public static int mouseX, mouseY, STATE = 1;
	public boolean mouseClicked, tick = false;
	public static String State;
	
	public AccountSelecter() {
		PERFIL[0] = Main.spritesheet.getSprite(0, 0,64,64);
		PERFIL[1] = Main.spritesheet.getSprite(0, 64,64,64);
		PERFIL[2] = Main.spritesheet.getSprite(64, 0,64,64);
	}
	public void tick() {
		if(STATE == 1) {
			if(mouseClicked) {
				mouseClicked = false;
				if(mouseX >= Main.WIDTH/5.61 && mouseX <= Main.WIDTH/2.95 && mouseY >= Main.HEIGHT/3.3 && mouseY <= Main.HEIGHT/1.76) {
					if(currentOption == 0) {
						if(State == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 0;
				}else if(mouseX >= Main.WIDTH/2.25 && mouseX <= Main.WIDTH/1.65 && mouseY >= Main.HEIGHT/3.3 && mouseY <= Main.HEIGHT/1.76) {
					if(currentOption == 1) {
						if(State == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 1;
				}else if(mouseX >= Main.WIDTH/1.41 && mouseX <= Main.WIDTH/1.15 && mouseY >= Main.HEIGHT/3.3 && mouseY <= Main.HEIGHT/1.76) {
					if(currentOption == 2 ) {
						if(State == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 2;
				}	
			}else if(left) {
				currentOption--;
				left = false;
				if(currentOption < 0) {
					currentOption = maxOption;
				}
			}else if(right){
				currentOption++;
				right = false;
				if(currentOption > maxOption) {
					currentOption = 0;
				}
			}else if(enter){
				enter = false;
				if(State == "SIGNIN")
					signin();
				else
					login();
			}	
		}else {
			right = false;
			left = false;
			enter = false;
		}
		
	}
	

	
	public void render(Graphics g) {
		if(STATE==1) {
			g.drawImage(PERFIL[0],(int) (Main.WIDTH/4) - (int)(Main.WIDTH/12.12), (int)(Main.HEIGHT/3.273), (int)(Main.WIDTH/6.6), (int)(Main.WIDTH/6.6), null);
			g.drawImage(PERFIL[1], (int) (Main.WIDTH/4*2) - (int)(Main.WIDTH/12.12), (int)(Main.HEIGHT/3.273), (int)(Main.WIDTH/6.6), (int)(Main.WIDTH/6.6), null);
			g.drawImage(PERFIL[2], (int) (Main.WIDTH/4*3) - (int)(Main.WIDTH/12.12), (int)(Main.HEIGHT/3.273), (int)(Main.WIDTH/6.6), (int)(Main.WIDTH/6.6), null);
			g.setColor(Color.green);
			g.setFont(new Font("arial",Font.BOLD,90));
			g.drawString("^",(int) (((Main.WIDTH/4)*(currentOption+1)) - Main.WIDTH/40), Main.HEIGHT / 2 + (int)(Main.HEIGHT / 4.6));	
		}else if(STATE==2) {
			g.drawImage(PERFIL[slot-1],Main.WIDTH/10, (int)(Main.HEIGHT/3.273), (int)(Main.WIDTH/6.6), (int)(Main.WIDTH/6.6), null);		
		}
	}

	private void signin() {
		File file = new File("contas"+(currentOption + 1)+".txt");
		if(!file.exists()) {
			slot = currentOption+1;
			STATE++;
			CampoRegistro campo = new CampoRegistro();
			campo.criarRegistro();
		}else 
			JOptionPane.showMessageDialog(null,"Uma conta já foi criada com este usuário, selecione outro slot de salvamento, ou aperte ESC para retornar a tela inicial!","Conta já criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}

	private void login() {
		File file = new File("contas"+(currentOption +1)+".txt");
		if(file.exists()) {
			slot = currentOption+1;
			STATE++;
			CampoLogin campo = new CampoLogin();
			campo.logar();
		}else 
			JOptionPane.showMessageDialog(null,"Conta não registrada neste usuário!","Conta não criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}
}
