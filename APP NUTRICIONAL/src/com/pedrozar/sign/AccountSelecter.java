package com.pedrozar.sign;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JOptionPane;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;

public class AccountSelecter {
	
	private static BufferedImage[] PERFIL = new BufferedImage[3];	
	private final String[] OPTIONS = { "1","2", "3"};
	private int currentOption = 0, maxOption = OPTIONS.length-1, slot = 0;
	private boolean left = false, right = false, enter = false;
	private static int mouseX, mouseY, STATE = 1;
	private boolean mouseClicked;
	private static String State;
	
	public AccountSelecter() {
		PERFIL[0] = Main.getSpritesheet().getSprite(0, 0,64,64);
		PERFIL[1] = Main.getSpritesheet().getSprite(0, 64,64,64);
		PERFIL[2] = Main.getSpritesheet().getSprite(64, 0,64,64);
	}
	
	public void tick() {
		if(getSTATE() == 1) {
			if(isMouseClicked()) {
				setMouseClicked(false);
				if(getMouseX() >= Main.getWIDTH()/5.61 && getMouseX() <= Main.getWIDTH()
						/2.95 && getMouseY() >= Main.getHEIGHT()/3.3 && getMouseY() 
						<= Main.getHEIGHT()/1.76) {
					if(currentOption == 0) {
						if(getState() == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 0;
				}else if(getMouseX() >= Main.getWIDTH()/2.25 && getMouseX() 
						<= Main.getWIDTH()/1.65 && getMouseY() >= Main.getHEIGHT()/3.3 
						&& getMouseY() <= Main.getHEIGHT()/1.76) {
					if(currentOption == 1) {
						if(getState() == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 1;
				}else if(getMouseX() >= Main.getWIDTH()/1.41 && getMouseX() 
						<= Main.getWIDTH()/1.15 && getMouseY() >= Main.getHEIGHT()/3.3 
						&& getMouseY() <= Main.getHEIGHT()/1.76) {
					if(currentOption == 2 ) {
						if(getState() == "SIGNIN")
							signin();
						else
							login();
					}
					currentOption = 2;
				}	
			}else if(isLeft()) {
				currentOption--;
				setLeft(false);
				if(currentOption < 0) {
					currentOption = maxOption;
				}
			}else if(isRight()){
				currentOption++;
				setRight(false);
				if(currentOption > maxOption) {
					currentOption = 0;
				}
			}else if(isEnter()){
				setEnter(false);
				if(getState() == "SIGNIN")
					signin();
				else
					login();
			}	
		}else {
			setRight(false);
			setLeft(false);
			setEnter(false);
		}
		
	}
	

	
	public void render(Graphics g) {
		if(getSTATE()==1) {
			g.drawImage(PERFIL[0],(int) (Main.getWIDTH()/4) - 
					(int)(Main.getWIDTH()/12.12), (int)(Main.getHEIGHT()/3.273), 
					(int)(Main.getWIDTH()/6.6), (int)(Main.getWIDTH()/6.6), null);
			g.drawImage(PERFIL[1], (int) (Main.getWIDTH()/4*2) - 
					(int)(Main.getWIDTH()/12.12), (int)(Main.getHEIGHT()/3.273), 
					(int)(Main.getWIDTH()/6.6), (int)(Main.getWIDTH()/6.6), null);
			g.drawImage(PERFIL[2], (int) (Main.getWIDTH()/4*3) - 
					(int)(Main.getWIDTH()/12.12), (int)(Main.getHEIGHT()/3.273), 
					(int)(Main.getWIDTH()/6.6), (int)(Main.getWIDTH()/6.6), null);
			g.setColor(Color.green);
			g.setFont(new Font("arial",Font.BOLD, Main.getHEIGHT()/8));
			g.drawString("^",(int) (((Main.getWIDTH()/4)*(currentOption+1)) - 
					Main.getWIDTH()/40), Main.getHEIGHT() / 2 + 
					(int)(Main.getHEIGHT() / 4.6));	
		}else if(getSTATE()==2) {
			g.drawImage(PERFIL[getSlot()-1],Main.getWIDTH()/10, 
					(int)(Main.getHEIGHT()/3.273), (int)(Main.getWIDTH()/6.6), 
					(int)(Main.getWIDTH()/6.6), null);		
		}
	}

	private void signin() {
		File file = new File("contas"+(currentOption + 1)+".txt");
		if(!file.exists()) {
			setSlot(currentOption+1);
			setSTATE(getSTATE() + 1);
			CampoRegistro campo = new CampoRegistro();
			campo.criarRegistro();
		}else 
			JOptionPane.showMessageDialog(null,
					"Uma conta já foi criada com este usuário, selecione outro slot "
					+ "de salvamento, ou aperte ESC para retornar a tela inicial!",
					"Conta já criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}

	private void login() {
		File file = new File("contas"+(currentOption +1)+".txt");
		if(file.exists()) {
			setSlot(currentOption+1);
			setSTATE(getSTATE() + 1);
			CampoLogin campo = new CampoLogin();
			campo.logar();
		}else 
			JOptionPane.showMessageDialog(null,"Conta não registrada neste usuário!",
					"Conta não criada!", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	public static void logout()
	{
		User.setHeight(0); 
		User.setWeight(0);
		User.setWdcalories(0);
		User.setAge(0);
		User.setGender(0); 
		User.setPassword(0);
		User.setUser(0); 
		User.setDcalories(0);
		User.setFirtLogin(0);
		User.setwWeight(0);
		Main.setState("MENU");
		AccountSelecter.setSTATE(1);
	}
	
	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public static int getSTATE() {
		return STATE;
	}

	public static void setSTATE(int sTATE) {
		STATE = sTATE;
	}

	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		AccountSelecter.mouseY = mouseY;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		AccountSelecter.mouseX = mouseX;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public static String getState() {
		return State;
	}

	public static void setState(String state) {
		State = state;
	}
}
