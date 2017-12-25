package escape.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import escape.main.Game.STATUS;

public class Menu extends MouseAdapter{
	
	public static final int TITLE_FONT_SIZE = 64;
	public static final int MENU_FONT_SIZE = 32;
	
	private Game game;
	private MenuBackground menuBackground;
	
	private boolean pressedSinglePlayer;
	private boolean pressedMultiPlayer;
	private boolean pressedHelp;
	private boolean pressedQuit;
	private boolean pressedBack;
	
	private Rectangle singlePlayerRect;
	private Rectangle multiPlayerRect;
	private Rectangle helpRect;
	private Rectangle quitRect;
	private Rectangle backRect;
	
	public Menu(Game game){
		this.game = game;
		menuBackground = new MenuBackground();
		
		pressedSinglePlayer = false;
		pressedMultiPlayer = false;
		pressedHelp = false;
		pressedQuit = false;
		pressedBack = false;
		
		singlePlayerRect = new Rectangle(256, 116, 512, 128);
		multiPlayerRect = new Rectangle(256, 256, 512, 128);
		helpRect = new Rectangle(256, 396, 512, 128);
		quitRect = new Rectangle(256, 536, 512, 128);
		backRect = new Rectangle(256, 536, 512, 128);
	}

	public void tick(){
		menuBackground.tick();
	}
	
	public void render(Graphics g){
		
		menuBackground.render(g);
		
		g.setColor(Color.blue);
		g.setFont(new Font("TimesRoman", Font.BOLD, TITLE_FONT_SIZE));
		g.drawString("Escape", 400, 64);
		if(game.getGameStatus() == STATUS.MENU){
			showMenu(g);
		}else if(game.getGameStatus() == STATUS.HELP){
			showHelp(g);
		}else if(game.getGameStatus() == STATUS.GAMEOVER){
			showGameOver(g);
		}
		g.setFont(new Font("TimesRoman", Font.LAYOUT_LEFT_TO_RIGHT, Handler.DEFAULT_FONT_SIZE));
	}

	private boolean mouseOver(Rectangle rectangle, int mouseX, int mouseY){
		if( mouseX > rectangle.getX() && mouseX < (rectangle.getY() + rectangle.getWidth()) &&
			mouseY > rectangle.getY() && mouseY < (rectangle.getY() + rectangle.getHeight())){
			return true;
		}
		return false;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(game.getGameStatus() == STATUS.MENU){					
			//single player button
			if(mouseOver(singlePlayerRect, mouseX, mouseY)){
				game.getHandler().setSinglePlayer(true);
				startNewGame();
				game.setGameStatus(STATUS.GAME);
				pressedSinglePlayer = false;
			}
			
			//multiplayer button
			if(mouseOver(multiPlayerRect, mouseX, mouseY)){
				game.getHandler().setSinglePlayer(false);
				startNewGame();
				game.setGameStatus(STATUS.GAME);
				pressedMultiPlayer = false;
			}
			
			//help button
			if(mouseOver(helpRect, mouseX, mouseY)){
				game.setGameStatus(STATUS.HELP);
				pressedHelp = false;
			}
		
			//quit button
			if(mouseOver(quitRect, mouseX, mouseY)){
				pressedQuit = false;
				System.exit(1);
			}
			super.mousePressed(e);
		}else if(game.getGameStatus() == STATUS.HELP || game.getGameStatus() == STATUS.GAMEOVER){
			if(mouseOver(backRect, mouseX, mouseY)){
				game.setGameStatus(STATUS.MENU);
				pressedBack = false;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(game.getGameStatus() == STATUS.MENU){
			if(mouseOver(singlePlayerRect, mouseX, mouseY)){
				pressedSinglePlayer = true;
			}
			
			//multiplayer button
			if(mouseOver(multiPlayerRect, mouseX, mouseY)){
				pressedMultiPlayer = true;
			}
			
			//help button
			if(mouseOver(helpRect, mouseX, mouseY)){
				pressedHelp = true;
			}
		
			//quit button
			if(mouseOver(quitRect, mouseX, mouseY)){
				pressedQuit = true;
			}
		}else if(game.getGameStatus() == STATUS.HELP || game.getGameStatus() == STATUS.GAMEOVER){
			//back button
			if(mouseOver(backRect, mouseX, mouseY)){
				pressedBack = true;
			}
		}
		super.mouseReleased(e);
	}
	
	private void showMenu(Graphics g){
		
		//MENU
		g.setColor(Color.lightGray);
		g.setFont(new Font("TimesRoman", Font.BOLD, MENU_FONT_SIZE));
		g.drawString("MENU", 470, 108);
		
		//single player button
		g.drawRect((int)singlePlayerRect.getX(), 
				   (int)singlePlayerRect.getY(), 
				   (int)singlePlayerRect.getWidth(), 
				   (int)singlePlayerRect.getHeight());
		if(pressedSinglePlayer){
			g.setColor(Color.blue);
			g.fillRect((int)singlePlayerRect.getX(), 
					   (int)singlePlayerRect.getY(), 
					   (int)singlePlayerRect.getWidth(), 
					   (int)singlePlayerRect.getHeight());
			g.setColor(Color.lightGray);
		}
		g.drawString("------ Single player ------", 340, 190);
		
		//multiplayer button
		g.drawRect((int)multiPlayerRect.getX(), 
				   (int)multiPlayerRect.getY(), 
				   (int)multiPlayerRect.getWidth(), 
				   (int)multiPlayerRect.getHeight());
		if(pressedMultiPlayer){
			g.setColor(Color.blue);
			g.fillRect((int)multiPlayerRect.getX(), 
					   (int)multiPlayerRect.getY(), 
					   (int)multiPlayerRect.getWidth(), 
					   (int)multiPlayerRect.getHeight());
			g.setColor(Color.lightGray);
		}
		g.drawString("------ Multiplayer ------", 360, 330);
		
		//help button
		g.drawRect((int)helpRect.getX(), 
				   (int)helpRect.getY(), 
				   (int)helpRect.getWidth(), 
				   (int)helpRect.getHeight());
		if(pressedHelp){
			g.setColor(Color.blue);
			g.fillRect((int)helpRect.getX(), 
					   (int)helpRect.getY(), 
					   (int)helpRect.getWidth(), 
					   (int)helpRect.getHeight());
			g.setColor(Color.lightGray);
		}
		g.drawString("------ Help ------", 405, 470);
		
		//quit button
		g.drawRect((int)quitRect.getX(), 
				   (int)quitRect.getY(), 
				   (int)quitRect.getWidth(), 
				   (int)quitRect.getHeight());
		if(pressedQuit){
			g.setColor(Color.blue);
			g.fillRect((int)quitRect.getX(), 
					   (int)quitRect.getY(), 
					   (int)quitRect.getWidth(), 
					   (int)quitRect.getHeight());
			g.setColor(Color.lightGray);
		}
		g.drawString("------ Quit ------", 405, 610);
	}
	
	private void showHelp(Graphics g) {
		g.setColor(Color.lightGray);
		g.setFont(new Font("TimesRoman", Font.BOLD, MENU_FONT_SIZE));
		g.drawString("HELP", 470, 220);
		
		//player 1 commands
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.LAYOUT_LEFT_TO_RIGHT, Handler.DEFAULT_FONT_SIZE * 2));
		g.drawString("Player 1 instructions:", 32, 300);
		g.drawString(Instructions.player1Commands, 32, 324);
		
		g.drawLine(Game.WIDTH / 2, 250, Game.WIDTH / 2, 500);
		
		//player 2 commands
		g.setColor(Color.blue);
		g.drawString("Player 2 instructions:", Game.WIDTH/2 + 32 ,300);
		g.drawString(Instructions.player2Commands, Game.WIDTH/2 + 32, 324);
		
		//back button
		drawBackButton(g);
	}
	

	private void showGameOver(Graphics g) {
		g.setColor(Color.lightGray);
		g.setFont(new Font("TimesRoman", Font.BOLD, MENU_FONT_SIZE));
		
		//game over
		g.drawString("GAME OVER!", 420, 220);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.LAYOUT_LEFT_TO_RIGHT, Handler.DEFAULT_FONT_SIZE * 2));
		
		if(game.getHandler().isSinglePlayer()){
			//results for single player
			g.drawString("Score: " + game.getHandler().getTotalScore(), 256, 350);
		}else{
			//results for multiplayer
			g.drawString("Winner is " + game.getHandler().getWinner().getId()+ "!!!", 256, 300);
			g.drawString("Score: " + game.getHandler().getTotalScore(), 256, 350);
		}
		drawBackButton(g);
	}
	
	public void startNewGame(){
		
		game.getHandler().removeAllObjects();
		game.getHandler().setGameOver(false);
		game.getHandler().setWinner(null);
		if(game.getHandler().isSinglePlayer()){
			game.getHandler().addObject(new Player(475, 128, ID.PLAYER1, game.getHandler()));
		}else{
			game.getHandler().addObject(new Player(256, 128, ID.PLAYER1, game.getHandler()));
			game.getHandler().addObject(new Player(768, 128, ID.PLAYER2, game.getHandler()));
		}
		game.getHandler().addObject(new Score(468, 16));
		game.getHandler().addObject(new Instructions(16, 16, game.getHandler()));
		game.getHandler().addObject(new HealthBar(16, 40, game.getHandler()));
		for(int i = 0; i < Game.NUM_OF_ENEMIES; i++){
			game.getHandler().addObject(new Enemy());
		}
	}
	
	public void drawBackButton(Graphics g){
		
		g.setColor(Color.lightGray);
		g.setFont(new Font("TimesRoman", Font.BOLD, MENU_FONT_SIZE));
		
		g.drawRect((int)backRect.getX(), 
				   (int)backRect.getY(), 
				   (int)backRect.getWidth(), 
				   (int)backRect.getHeight());
		if(pressedBack){
			g.setColor(Color.blue);
			g.fillRect((int)backRect.getX(), 
					   (int)backRect.getY(), 
					   (int)backRect.getWidth(), 
					   (int)backRect.getHeight());
			g.setColor(Color.lightGray);
		}
		g.drawString("------ Back ------", 405, 610);
	}
}
