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
	public String[] options = { "1","2", "3"};
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
				if(mouseX >= 108 && mouseX <= 236 && mouseY >= 219 && mouseY <= 347) {
					if(currentOption == 0) {
						if(State == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 0;
				}else if(mouseX >= 374 && mouseX <= 502 && mouseY >= 219 && mouseY <= 347) {
					if(currentOption == 1) {
						if(State == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 1;
				}else if(mouseX >= 640 && mouseX <= 767 && mouseY >= 219 && mouseY <= 347) {
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
			g.drawImage(PERFIL[0], 108, 220, 64*2, 64*2, null);
			g.drawImage(PERFIL[1], 374, 220, 64*2, 64*2, null);
			g.drawImage(PERFIL[2], 640, 220, 64*2, 64*2, null);
			g.setColor(Color.green);
			g.setFont(new Font("arial",Font.BOLD,90));
			g.drawString("^", Main.WIDTH / 2 - 299 + (266*(currentOption)), Main.HEIGHT / 2 +130);	
		}else if(STATE==2) {
			g.drawImage(PERFIL[slot-1],70, 220, 64*2, 64*2, null);		
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
			JOptionPane.showMessageDialog(null,"Uma conta j� foi criada com este usu�rio, selecione outro slot de salvamento, ou aperte ESC para retornar a tela inicial!","Conta j� criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}

	private void login() {
		File file = new File("contas"+(currentOption + 1)+".txt");
		if(file.exists()) {
			slot = currentOption+1;
			STATE++;
			CampoLogin campo = new CampoLogin();
			campo.logar();
		}else 
			JOptionPane.showMessageDialog(null,"Conta n�o registrada neste usu�rio!","Conta n�o criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}
}