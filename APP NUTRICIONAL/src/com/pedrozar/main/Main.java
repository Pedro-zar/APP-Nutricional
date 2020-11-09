package com.pedrozar.main;

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

import com.pedrozar.functionalities.Diary;
import com.pedrozar.functionalities.FunctionalitySelector;
import com.pedrozar.functionalities.Update;
import com.pedrozar.graphics.Spritesheet;
import com.pedrozar.sign.AccountSelecter;

public class Main extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static final String NOME = "Wealth Health";
	private static int HEIGHT = 720, WIDTH = (int)(getHEIGHT()*1.777777777777778);
	private static String State = "MENU";
	private static Spritesheet spritesheet;
	public static AccountSelecter accountSelecter;
	private boolean isRunning = true;
	private Thread thread;	
	private BufferedImage image;
	public Menu menu;
	public Update update;
	public Diary diary;
	public FunctionalitySelector functionalitySelector;
	
	public Main() {
		addKeyListener(this);
		addMouseListener(this);
		initFrame();
		spritesheet = new Spritesheet("/spritesheet.png");	
		image = new BufferedImage(getWIDTH(),getHEIGHT(),BufferedImage.TYPE_INT_RGB);
		menu = new Menu();
		diary = new Diary();
		update = new Update();
		functionalitySelector = new FunctionalitySelector();
		accountSelecter = new AccountSelecter();
	}

	public static void main(String args[]){
		Main main = new Main();
		main.start();
	}
	
	public static void setScreenSize(int height) {
		HEIGHT = height;
		WIDTH = (int)(getHEIGHT()*1.777777777777778);
		frame.setPreferredSize(new Dimension(getWIDTH(),getHEIGHT()));
	}
	
	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static String getNome() {
		return NOME;
	}
	
	public static String getState() {
		return State;
	}

	public static void setState(String state) {
		State = state;
	}

	public static Spritesheet getSpritesheet() {
		return spritesheet;
	}

	private void initFrame() {
		frame = new JFrame(getNome());
		frame.setPreferredSize(new Dimension(getWIDTH(),getHEIGHT()));
		frame.add(this); 
		frame.setResizable(false); 
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
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
		double amountOfTicks = 500.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		requestFocus();
		double timer = System.currentTimeMillis();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
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
		g.fillRect(0, 0,getWIDTH(),getHEIGHT());
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWIDTH(), getHEIGHT(),null);
		if(getState() == "MENU")
			menu.render(g);
		else if(getState() == "LOGIN" || getState() == "SIGNIN")
			accountSelecter.render(g);
		else if(getState() == "FUNC_SELEC")
			functionalitySelector.render(g);
		else if(getState() == "DIARY")
			diary.render(g);
		else if(getState() == "UPDATE")
			update.render(g);
		bs.show();
	}

	public void tick() {
		if(getState() == "MENU") 
			menu.tick();
		else if(getState() == "LOGIN" || getState() == "SIGNIN") 
			accountSelecter.tick();
		else if(getState() == "FUNC_SELEC") 
			functionalitySelector.tick();
		else if(getState() == "DIARY")
			diary.tick();
	}

	public void mouseClicked(MouseEvent e) {
		switch(Main.getState()) {
		case "SIGNIN":
		case "LOGIN":
			accountSelecter.setMouseClicked(true);
			AccountSelecter.setMouseY(e.getY());
			AccountSelecter.setMouseX(e.getX());
			break;
		case "DIARY":
			Diary.setMouseX(e.getX());
			Diary.setMouseY(e.getY());
			diary.setMouseClicked(true);
		}
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
		switch(getState()) {
		case "MENU":
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				menu.setDown(true);
			}else if(e.getKeyCode() == KeyEvent.VK_UP) {
				menu.setUp(true);
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.setEnter(true);
			}
			break;
		case "SIGNIN" :
		case "LOGIN"  :
			if(e.getKeyCode() == KeyEvent.VK_LEFT) 
				accountSelecter.setLeft(true);
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
				accountSelecter.setRight(true);
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				accountSelecter.setEnter(true);
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
				if(AccountSelecter.getSTATE() == 1) {
					setState("MENU");
				}
			break;
		case "FUNC_SELEC":
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
				functionalitySelector.setDown(true);
			else if(e.getKeyCode() == KeyEvent.VK_UP) 
				functionalitySelector.setUp(true);
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				functionalitySelector.setEnter(true);
			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				functionalitySelector.setEscape(true);
			break;
		case "DIARY":
			if(e.getKeyCode() == KeyEvent.VK_DOWN) 
				diary.setDown(true);
			else if(e.getKeyCode() == KeyEvent.VK_UP) 
				diary.setUp(true);
			else if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				diary.setEnter(true);
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}