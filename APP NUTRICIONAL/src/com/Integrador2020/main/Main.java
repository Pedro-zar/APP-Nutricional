package com.Integrador2020.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import com.Integrador2020.sign.Login;
import com.Integrador2020.sign.Signin;

public class Main extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	public static final String Nome = "Título";
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 900, HEIGHT = 600;
	public Menu menu;
	public Login login;
	public Signin signin;
	private BufferedImage image;
	public static String State = "MENU";
	public static com.Integrador2020.graphics.Spritesheet spritesheet;
	
	public static void main(String args[]){
		Main main = new Main();
		main.start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();	
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		//int frames = 0;
		requestFocus();
		double timer = System.currentTimeMillis();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				//frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000){
				//System.out.println("FPS: "+ frames);
				//frames = 0;
				timer+=1000;
			}
			
		}
		stop();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT,null);
		if(State == "MENU") {
			menu.render(g);
		}else if(State == "LOGIN") {
			login.render(g);
		}else if(State == "SIGNIN") {
			signin.render(g);
		}
		bs.show();
	}

	public void tick() {
		if(State == "MENU") {
			menu.tick();
		}else if(State == "LOGIN") {
			login.tick();
		}else if(State == "SIGNIN") {
			signin.tick();
		}
	}

	public Main() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		initFrame();
		spritesheet = new com.Integrador2020.graphics.Spritesheet("/spritesheet.png");
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		menu = new Menu();
		signin = new Signin();
		login = new Login();
	}

	private void initFrame() {
		frame = new JFrame(Nome);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void mouseClicked(MouseEvent e) {
		signin.mouseClicked = true;
		signin.mouseY =  e.getY();
		signin.mouseX =  e.getX();
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {
	
	}

	public void keyPressed(KeyEvent e) {
		switch(State) {
		case "MENU":
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				menu.down = true;
			}else if(e.getKeyCode() == KeyEvent.VK_UP) {
				menu.up = true;
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;
			}
			break;
		case "SIGNIN":
			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
				signin.left = true;
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
				signin.right = true;
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				signin.enter = true;
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
				if(signin.STATE > 1) {
					signin.STATE--;
				}else
					State = "MENU";
			
		}
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
