package com.Pedrozar.main;

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

import com.Pedrozar.functionalities.Diary;
import com.Pedrozar.functionalities.FunctionalitySelector;
import com.Pedrozar.functionalities.Update;
import com.Pedrozar.graphics.Spritesheet;
import com.Pedrozar.sign.AccountSelecter;

public class Main extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static final String Nome = "Wealth and Health";
	private Thread thread;
	private boolean isRunning = true;
	private static final int HEIGHT = 720, WIDTH = (int)(getHEIGHT()*1.777777777777778);
	private BufferedImage image;
	private static String State = "MENU";
	private static Spritesheet spritesheet;
	
	public Menu menu;
	public Update update;
	public Diary diary;
	public FunctionalitySelector functionalitySelector;
	public static AccountSelecter accountSelecter;
	
	
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

	public Main() {
		setPreferredSize(new Dimension(getWIDTH(),getHEIGHT()));
		addKeyListener(this);
		addMouseListener(this);
		initFrame();
		
		setSpritesheet(new Spritesheet("res/spritesheet.png"));	
		image = new BufferedImage(getWIDTH(),getHEIGHT(),BufferedImage.TYPE_INT_RGB);
		menu = new Menu();
		diary = new Diary();
		update = new Update();
		functionalitySelector = new FunctionalitySelector();
		accountSelecter = new AccountSelecter();
	}

	private void initFrame() {
		frame = new JFrame(getNome());
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void mouseClicked(MouseEvent e) {
		accountSelecter.setMouseClicked(true);
		AccountSelecter.setMouseY(e.getY());
		AccountSelecter.setMouseX(e.getX());
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
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public static String getNome() {
		return Nome;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
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

	public static void setSpritesheet(Spritesheet spritesheet) {
		Main.spritesheet = spritesheet;
	}
}
