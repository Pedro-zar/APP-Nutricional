package com.pedrozar.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import com.pedrozar.entities.User;
import com.pedrozar.functionalities.CampoAlimento;
import com.pedrozar.functionalities.Diary;
import com.pedrozar.sign.AccountSelecter;

public class Save {
	
	public static void deleteAccount(int slot) {
		File file = new File("contas" + slot + ".txt");
		file.delete();
		file = new File("calorias" + slot + ".txt");
		file.delete();
		AccountSelecter.logout();
	}
	
	public static void saveRegister(String[] val1, int[] val2, int encode, int slot, String arquivo) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter(arquivo + slot + ".txt"));
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
	
	public static int getSave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i< spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "password":
				return Integer.parseInt(spl2[1]);
			}
		}
		return 0;
	}
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i< spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "user":
				User.setUser(Integer.parseInt(spl2[1]));
				break;
			case "password":
				User.setPassword(Integer.parseInt(spl2[1]));
				break;
			case "weight":
				User.setWeight(Integer.parseInt(spl2[1]));
				break;
			case "height":
				User.setHeight(Integer.parseInt(spl2[1]));
				break;
			case "gender":
				User.setGender(Integer.parseInt(spl2[1]));
				break;
			case "age":
				User.setAge(Integer.parseInt(spl2[1]));
				break;
			case "firstLogin":
				User.setFirtLogin(Integer.parseInt(spl2[1]));
				break;
			case "wdcalories":
				User.setWdcalories(Integer.parseInt(spl2[1]));
				break;
			case "wweight":
				User.setwWeight(Integer.parseInt(spl2[1]));
				break;
			case "dcalories":
				User.setDcalories(Integer.parseInt(spl2[1]), false);
				break;
			case "protDiv":
				User.setProtDivision(Integer.parseInt(spl2[1]));
				break;
			case "carbDiv":
				User.setCarbDivision(Integer.parseInt(spl2[1]));
				break;
			case "fatDiv":
				User.setFatDivision(Integer.parseInt(spl2[1]));
				break;
			}
		}
	}
	
	public static String loadRegister(int encode, int slot, String arquivo) {//dados do user
		String line = "";
		File file = new File(arquivo + slot + ".txt");
		if(file.exists()) {
			try {
				String singleLine  = null;
				BufferedReader reader = new BufferedReader(new FileReader(
						arquivo + slot + ".txt"));
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
						line+= ":";
						line+= transition[1];
						line+= "/";
					}
					reader.close();
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void loadRegister(DefaultListModel<String> model) {//lista de alimentos
		String line = "";
		File file = new File("lista de alimentos.txt");
		if(file.exists()) {
			try {
				String singleLine  = null;
				BufferedReader reader = new BufferedReader(new FileReader(
						"lista de alimentos.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] transition = singleLine.split(":");
						line+= transition[0];
						line+=":";
						line+=transition[1];
						line+=":";
						line+=transition[2];
						line+=":";
						line+=transition[3];
						line+=":";
						line+=transition[4];
						line+=":";
						line+=transition[5];
						line+="/";
					}
					reader.close();
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
				
		String[] spl = line.split("/");
		model.clear();
		int a = 0;
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			if(spl2[0].trim().toLowerCase().contains(CampoAlimento.textSearch.getText().trim().toLowerCase())) {
				Diary.foodList[a][0] = spl2[0];
				Diary.foodList[a][1] = spl2[1];
				Diary.foodList[a][2] = spl2[2];
				Diary.foodList[a][3] = spl2[3];
				Diary.foodList[a][4] = spl2[4];
				Diary.foodList[a][5] = spl2[5];
				a++;
				model.addElement(spl2[0]);
			}
		}
	}
}
