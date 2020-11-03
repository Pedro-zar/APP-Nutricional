package com.pedrozar.functionalities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;
import com.pedrozar.main.Save;

public class Diary {

	private static int mouseX, mouseY;
	private int wdCalories = 0, wWeight = 0, exerciseRate = 0, dietOption = 0;
	public static String[][] foodList;
	private int carbCon, fatCon, protCon;
	private boolean mouseClicked, down = false, up = false, enter = false;
	private String[] options = {"ADICIONAR","VOLTAR"};
	private int currentOption = 0, maxOption = options.length-1;
	private BufferedImage spritesheet;
	private int[] nutrientChosed = new int[3];
	private int nutrientsDivision = 0;
	int f = 0;
	
	public Diary() {
		spritesheet = Main.getSpritesheet().getSprite(64, 64,96,21);
		foodList = new String[100][6];
	}
	
	public void tick() {
		if(f == 0) {
			adicionarList();
			setValues();
			f++;
		}
		if(isMouseClicked()) {
			if(getMouseX() >= Main.getWIDTH() * 0.7 && getMouseY() 
					>= Main.getHEIGHT() * 0.8 && isMouseClicked()){
				
			}
			
		}else if(isUp()) {
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}else if(isDown()) {
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}else if(isEnter()) {
			if(currentOption == 0) {
			}else if (currentOption == 1) {
				f = 0;
				Main.setState("FUNC_SELEC");
			}
		}
		setUp(false);
		setDown(false);
		setEnter(false);
		setMouseClicked(false);
	}
	
	public void render(Graphics g) {

		g.drawImage(spritesheet, (int)(Main.getWIDTH() * 0.61), 
				(int)(Main.getHEIGHT() * 0.6), (int)(Main.getWIDTH()/3), 
				(int)(Main.getHEIGHT()/10), null);
		g.drawImage(spritesheet, (int)(Main.getWIDTH() * 0.61), 
				(int)(Main.getHEIGHT() * 0.75), (int)(Main.getWIDTH()/3), 
				(int)(Main.getHEIGHT()/10), null);
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 20));
		g.drawString(">", (int)(Main.getWIDTH() * 0.58),
				(int)(Main.getHEIGHT() * (0.683 + currentOption * 0.15)));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 43));
		//menu
		g.drawString("Adicionar Alimentos",
				(int)(Main.getWIDTH() * 0.666), 
				(int)(Main.getHEIGHT() * 0.666));
		g.drawString("Voltar ao menu",
				(int)(Main.getWIDTH() * 0.69), 
				(int)(Main.getHEIGHT() * 0.815));
		adicionarHUD(g);
		
	}
	
	private void setValues() {
		if(User.getFirstLogin() == 0) {
			if(this.getNutrientsDivision() == 0) {//moderate carb
				nutrientChosed[0] = 30;
				nutrientChosed[1] = 35;
				nutrientChosed[2] = 35;
			}else if(this.getNutrientsDivision() == 1){//low carb
				nutrientChosed[0] = 40;
				nutrientChosed[1] = 40;
				nutrientChosed[2] = 20;
			}else {//high carb
				nutrientChosed[0] = 30;
				nutrientChosed[1] = 20;
				nutrientChosed[2] = 50;
			}
			User.setProtDivision(nutrientChosed[0]);
			User.setFatDivision(nutrientChosed[1]);
			User.setCarbDivision(nutrientChosed[2]);
			if(User.getGender() == 0)
				wdCalories = (int)((66.47 + (13.75 * User.getWeight()) 
						+ (5.003 * User.getHeight()) - (6.755 * User.getAge())) 
						* (1.2 +(0.2 * exerciseRate))) + (500 * dietOption);
			else
				wdCalories = (int)((655.1 + (9.563 * User.getWeight()) 
						+ (1.8 * User.getHeight()) - (4.676 * User.getAge()))
						* (1.2 + (0.2 *exerciseRate))) + (500 * dietOption);
			User.setWdcalories(wdCalories);
			User.setFirtLogin(1);
			User.setwWeight(wWeight);
			saveData();
		}else {			
			readData();
		}
	}
	
	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		Diary.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		Diary.mouseY = mouseY;
	}
	
	private void saveData() {
		String[] opt1 = {"user", "password", "gender", "age","weight", 
				"height", "firstLogin"};
		int[] opt2 = {User.getUser(), User.getPassword(), User.getGender(), 
				User.getAge(), User.getWeight(), User.getHeight(), 
				1};
		Save.saveRegister(opt1, opt2, 13, User.getUser(), "contas");
		String[] opt3 = {"wdcalories", "protDiv","fatDiv" ,"carbDiv",
				"dcalories","wweight"};
		int[] opt4 = {User.getWdcalories(), User.getProtDivision(),
				User.getFatDivision(), User.getCarbDivision(),
				User.getDcalories(), User.getwWeight()};
		Save.saveRegister(opt3, opt4, 13, User.getUser(), "calorias");
	}
	
	private void readData() {
		Save.applySave(
				Save.loadRegister(13, User.getUser(), "calorias"));
		System.out.println(User.getProtDivision());
	}

	private void adicionarList() {

	}
	
	private void adicionarHUD(Graphics g) {
		adicionarNutrientes(g);
		adicionarDados(g);
	}
	
	private void adicionarDados(Graphics g) {
		//Calories
		g.drawString("Meta de Calorias: " + User.getWdcalories(),
				(int)(Main.getWIDTH() * 0.05), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Calorias Consumidas: " + User.getDcalories(),
				(int)(Main.getWIDTH() * 0.05), 
				(int)(Main.getHEIGHT() * 0.15));
		//Weight
		g.drawString("Meta de peso: " + User.getwWeight(),
				(int)(Main.getWIDTH() * 0.37), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Peso atual: " + User.getWeight(),
				(int)(Main.getWIDTH() * 0.37), 
				(int)(Main.getHEIGHT() * 0.15));
	}	
	
	private void adicionarNutrientes(Graphics g) {
		int calories = User.getWdcalories() / 100;
		//Fats
		g.drawString("Proteinas necessárias: " + calories / 4 
				* User.getProtDivision() + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Proteinas consumidas: " + protCon + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.15));
		//Protein
		g.drawString("Gorduras necessárias: " + ((calories / 9)
				* User.getFatDivision()) + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.3));
		g.drawString("Gorduras consumidas: " + fatCon + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.35));
		//Carbs
		g.drawString("Carboidratos necessários: " + calories / 4
				* User.getCarbDivision() + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.5));
		g.drawString("Carboidratos consumidos: " + carbCon + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.55));
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public int getNutrientsDivision() {
		return nutrientsDivision;
	}

	public void setNutrientsDivision(int nutrientsDivision) {
		this.nutrientsDivision = nutrientsDivision;
	}
	
}