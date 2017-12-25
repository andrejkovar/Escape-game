package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FollowEnemy extends Enemy{
	
	private Player playerToFollow;
	private Random r;
	
	public FollowEnemy(Player playerToFollow) {
		
		this.playerToFollow = playerToFollow;
		r = new Random();
		
		if(r.nextBoolean()){
			setX(0);
		}else{
			setX(Game.WIDTH + Enemy.SIZE);
		}
		
		if(r.nextBoolean()){
			setY(0);
		}else{
			setY(Game.HEIGHT + Enemy.SIZE);
		}
		
		setStartingX(getX());
		setStartingY(getY());
	}
	
	@Override
	public void tick() {
		
		if(Math.abs(playerToFollow.getX() - this.getX()) <= Math.abs(playerToFollow.getY() - this.getY())){
			if(playerToFollow.getY() > this.getY()){
				setSpeedY(Enemy.SPEED);
			}else{
				setSpeedY(Enemy.SPEED * (-1));
			}
			setSpeedX(0);
		}else{
			if(playerToFollow.getX() > this.getX()){
				setSpeedX(Enemy.SPEED);
			}else{
				setSpeedX(Enemy.SPEED * (-1));
			}
			setSpeedY(0);
		}
		
		x += speedX;
		y += speedY;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, Enemy.SIZE / 2, Enemy.SIZE / 2);
		g.setColor(Color.black);
		g.drawString("X", x + 13, y + 21);
	}
	
	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, Enemy.SIZE / 2, Enemy.SIZE / 2);
	}
}
