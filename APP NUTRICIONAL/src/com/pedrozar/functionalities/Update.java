package com.pedrozar.functionalities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedrozar.entities.User;
import com.pedrozar.main.Main;

public class Update {
	
	private BufferedImage[] PERFIL = new BufferedImage[3];
	
	public Update() {
		PERFIL[0] = Main.getSpritesheet().getSprite(0, 0,64,64);
		PERFIL[1] = Main.getSpritesheet().getSprite(0, 64,64,64);
		PERFIL[2] = Main.getSpritesheet().getSprite(64, 0,64,64);
	}
	
	public static void criarJanela() {
		CampoAlteracao campo = new CampoAlteracao();
		campo.criarAlterator();
	}

	public void render(Graphics g) {
		g.drawImage(PERFIL[User.getUser() - 1], Main.getWIDTH()/10, 
				(int)(Main.getHEIGHT()/3.273), (int)(Main.getWIDTH()/6.6), 
				(int)(Main.getWIDTH()/6.6), null);	
	}

}
