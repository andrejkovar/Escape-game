package escape.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	public static final int SPEED = 5;
	public static final int SIZE = 32;
	
	private final int MAX_HEALTH = 200;
	private final int MAX_RECOVERY = 50;
	
	private boolean playerDead;
	private int playerHealth;
	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		playerHealth = MAX_HEALTH;
		playerDead = false;
	}
	
	@Override
	public void tick() {
		
		for(Enemy tempObject : handler.getEnemies()){
			if(this.getRectangle().intersects(tempObject.getRectangle())){
				playerHealth--;
				if(playerHealth <= 0){
					playerDead = true;
					handler.setGameOver(true);
					break;
				}
			}
		}
		
		for(RecoveryPack tempRecovery : handler.getRecoveries()){
			if(this.getRectangle().intersects(tempRecovery.getRectangle())){
				if(playerHealth < MAX_HEALTH){
					playerHealth += MAX_RECOVERY;
					tempRecovery.setRecoveryUsed(true);
					if(playerHealth > MAX_HEALTH){
						playerHealth = MAX_HEALTH;
					}
				}
				break;
			}
		}
		
		if(x <= 0){
			setSpeedX(SPEED);
		}
		else if((x + SIZE) >= Game.WIDTH){
			setSpeedX(SPEED * (-1));
		}
		
		if(y <= 0){
			setSpeedY(SPEED);
		}else if((y + (SIZE + 32)) >= Game.HEIGHT){
			setSpeedY(SPEED * (-1));
		}
				
		x += speedX;
		y += speedY;
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 32));
		if(getId() == ID.PLAYER1){
			g.setColor(Color.white);
			g.fillRoundRect(x, y, SIZE, SIZE, 10, 10);
			g.setColor(Color.BLACK);
			g.drawString("1", x + (SIZE/2) - 8, y + (SIZE/2) + 10);
		}
		else{
			g.setColor(Color.red);
			g.fillRoundRect(x, y, SIZE, SIZE, 10, 10);
			g.setColor(Color.white);
			g.drawString("2", x + (SIZE/2) - 8, y + (SIZE/2) + 10);
		}
		g.setFont(new Font("TimesRoman", Font.LAYOUT_LEFT_TO_RIGHT, 12));
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, SIZE, SIZE);
	}
	
	public boolean isPlayerDead(){
		return playerDead;
	}
	
	public int getHealth(){
		return playerHealth;
	}
	
	public void resetHealth(){
		playerHealth = 200;
	}
}
