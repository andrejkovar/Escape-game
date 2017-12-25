package escape.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
/**
 * 
 * @author AndrejKovar
 * 
 * @version 1.3
 * 
 * New:
 * 	- added music
 */
public class Game extends Canvas implements Runnable{
		
	
	public static final int WIDTH = 1024;
	public static  final int HEIGHT = 768;
	public static final int NUM_OF_ENEMIES = 5;
	
	public enum STATUS{
		MENU,
		HELP,
		GAME,	
		GAMEOVER;
	}
	
	public static boolean running;
	
	private static final long serialVersionUID = 1960267116245844959L;
	private STATUS gameStatus;
	private Thread thread;
	private Handler handler;
	private Menu menu;
	private BackgroundMusic backgroundMusic;

	public Game(){
		
		running = false;
		gameStatus = STATUS.MENU;
		menu = new Menu(this);
		handler = new Handler(this);
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler));
		
		backgroundMusic = new BackgroundMusic();
		backgroundMusic.start();
		
		new Window(WIDTH, HEIGHT, "ESCAPE", this);
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0.0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		if(gameStatus == STATUS.GAME){
			handler.tick();
		}else{
			menu.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.drawString("by AndrejKovar", 920, 720);
		g.drawString("Version 1.3", 16, 720);
		
		if(gameStatus == STATUS.GAME){
			handler.render(g);
		}else{
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public STATUS getGameStatus(){
		return gameStatus;
	}
	
	public void setGameStatus(STATUS newStatus){
		gameStatus = newStatus;
	}
	
	public Handler getHandler(){
		return handler;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
