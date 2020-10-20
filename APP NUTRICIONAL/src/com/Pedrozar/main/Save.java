package com.Pedrozar.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.Pedrozar.entities.User;
import com.Pedrozar.functionalities.Logout;

public class Save {
	
	public static void deleteAccount(int slot) {
		File file = new File("contas" + slot + ".txt");
		file.delete();
		Logout.logout();
	}
	
	public static void saveRegister(String[] val1, int[] val2, int encode, int slot) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("contas" + slot + ".txt"));
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
				User.user = Integer.parseInt(spl2[1]);
				break;
			case "password":
				User.password = Integer.parseInt(spl2[1]);
				break;
			case "weight":
				User.weight = Integer.parseInt(spl2[1]);
				break;
			case "height":
				User.height = Integer.parseInt(spl2[1]);
				break;
			case "gender":
				User.gender = Integer.parseInt(spl2[1]);
				break;
			case "age":
				User.age = Integer.parseInt(spl2[1]);
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
	
	public static String loadRegister(int encode, int slot) {
		String line = "";
		File file = new File("contas" + slot + ".txt");
		if(file.exists()) {
			try {
				String singleLine  = null;
				BufferedReader reader = new BufferedReader(new FileReader("contas" + slot + ".txt"));
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
}
