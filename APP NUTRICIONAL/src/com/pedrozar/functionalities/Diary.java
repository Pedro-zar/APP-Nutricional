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
	private int wdCalories = 0, tutorialState, exerciseRate, dietOption = 0;
	private String[][] foodList;
	private boolean mouseClicked, down = false, up = false, enter = false;
	private String[] options = {"ADICIONAR","VOLTAR"};
	private int currentOption = 0, maxOption = options.length-1;
	private BufferedImage spritesheet;
	private int nutrientsDivision = 0, necProt, necFats, necCarbs;
	
	public Diary() {
		spritesheet = Main.getSpritesheet().getSprite(64, 64,96,21);
		setFoodList(new String[100][5]);
	}
	
	public void tick() {
		int calories = User.getWdcalories() / 100;
		necProt =  calories * User.getProteinsDivision()[0];
		necFats = calories * User.getProteinsDivision()[1];
		necCarbs = calories * User.getProteinsDivision()[2];
		if(User.getFirstLogin() == 0) {
			if(getMouseX() >= Main.getWIDTH() * 0.7 && mouseClicked) {
				tutorialState++;
			}else if(tutorialState > 10)
				User.setFirtLogin(1);
			setMouseClicked(false);
		}else if(isUp()) {
			currentOption--;
			setUp(false);
			if(currentOption < 0)
				currentOption = maxOption;
		}else if(isDown()) {
			currentOption++;
			setDown(false);
			if(currentOption > maxOption)
				currentOption = 0;
		}else if(isEnter()) {
			if(currentOption == 0) {
				
			}else if (currentOption == 1) {
				Main.setState("FUNC_SELEC");
			}
			setEnter(false);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(spritesheet,  (int)(Main.getWIDTH() * 0.61), 
				(int)(Main.getHEIGHT() * 0.6), (int)(Main.getWIDTH()/3), 
				(int)(Main.getHEIGHT()/10), null);
		g.drawImage(spritesheet,  (int)(Main.getWIDTH() * 0.61), 
				(int)(Main.getHEIGHT() * 0.75), (int)(Main.getWIDTH()/3), 
				(int)(Main.getHEIGHT()/10), null);
		g.setColor(new Color(141, 255, 161));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 13));
		g.drawString(">", (int)(Main.getWIDTH() * 0.56),
				(int)(Main.getHEIGHT() * (0.7 + currentOption * 0.15)));
		g.setFont(new Font("arial",Font.BOLD, Main.getWIDTH() / 43));
		//menu
		g.drawString("Adicionar Alimentos",
				(int)(Main.getWIDTH() * 0.666), 
				(int)(Main.getHEIGHT() * 0.666));
		g.drawString("Voltar ao menu",
				(int)(Main.getWIDTH() * 0.69), 
				(int)(Main.getHEIGHT() * 0.815));
		//Calories
		g.drawString("Meta de Calorias: " + User.getWdcalories(),
				(int)(Main.getWIDTH() * 0.05), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Calorias Consumidas: " + 1200,
				(int)(Main.getWIDTH() * 0.05), 
				(int)(Main.getHEIGHT() * 0.15));
		//Weight
		g.drawString("Meta de peso: " + 70,
				(int)(Main.getWIDTH() * 0.37), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Peso atual: " + User.getWeight(),
				(int)(Main.getWIDTH() * 0.37), 
				(int)(Main.getHEIGHT() * 0.15));
		//Fats
		g.drawString("Proteinas necessárias: " + necProt + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.1));
		g.drawString("Proteinas consumidas: " + 2 + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.15));
		//Protein
		g.drawString("Gorduras necessárias: " + necFats + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.3));
		g.drawString("Gorduras consumidas: " + 2 + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.35));
		//Carbs
		g.drawString("Carboidratos necessários: " + necCarbs + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.5));
		g.drawString("Carboidratos consumidos: " + 2 + "g",
				(int)(Main.getWIDTH() * 0.6), 
				(int)(Main.getHEIGHT() * 0.55));
	}

	private void startTutorial() {
		
	}
	
	private void endTutorial() {
		int[] nutrientChosed = new int[3];
		if(this.getNutrientsDivision() == 0) {//moderate carb
			nutrientChosed[0] = 30;
			nutrientChosed[1] = 35;
			nutrientChosed[2] = 35;
			User.setProteinsDivision(nutrientChosed);
		}else if(this.getNutrientsDivision() == 1){//low carb
			nutrientChosed[0] = 40;
			nutrientChosed[1] = 40;
			nutrientChosed[2] = 20;
			User.setProteinsDivision(nutrientChosed);
		}else {//high carb
			nutrientChosed[0] = 30;
			nutrientChosed[1] = 20;
			nutrientChosed[2] = 50;
			User.setProteinsDivision(nutrientChosed);
		}
		if(User.getGender() == 0)
			wdCalories = (int)((66.47 + (13.75 * User.getWeight()) 
					+ (5.003 * User.getHeight()) - (6.755 * User.getAge())) 
					* exerciseRate) + (500 * dietOption);
		else
			wdCalories = (int)((655.1 + (9.563 * User.getWeight()) 
					+ (1.85 * User.getHeight()) - (4.676 * User.getAge()) 
					* exerciseRate )) + (500 * dietOption);
		
		User.setFirtLogin(1);
		String[] opt1 = {"user", "password", "gender", "age","weight", 
				"height", "firstLogin", "wdcalories"};
		int[] opt2 = {User.getUser(), User.getPassword(), User.getGender(), 
				User.getAge(), User.getWeight(), User.getHeight(), 
				1, wdCalories};
		Save.saveRegister(opt1, opt2, 13, User.getUser());	
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

	public String[][] getFoodList() {
		return foodList;
	}

	public void setFoodList(String[][] foodList) {
		this.foodList = foodList;
	}
	
}