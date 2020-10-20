package com.Pedrozar.functionalities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Pedrozar.entities.User;
import com.Pedrozar.main.Main;

public class Update {
	
	private BufferedImage[] PERFIL = new BufferedImage[3];
	
	public static void criarJanela() {
		CampoAlteracao campo = new CampoAlteracao();
		campo.criarAlterator();
	}
	
	public Update() {
		PERFIL[0] = Main.spritesheet.getSprite(0, 0,64,64);
		PERFIL[1] = Main.spritesheet.getSprite(0, 64,64,64);
		PERFIL[2] = Main.spritesheet.getSprite(64, 0,64,64);
	}

	public void render(Graphics g) {
		g.drawImage(PERFIL[User.user - 1], Main.WIDTH/10, (int)(Main.HEIGHT/3.273), (int)(Main.WIDTH/6.6), (int)(Main.WIDTH/6.6), null);	
	}

}
