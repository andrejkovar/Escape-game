package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {

	public static final int SIZE = 64;
	public static final int SPEED = 2;
	
	private int tempSize;
	private double counter;
	private Random r;
	
	public Enemy() {
		super(0, 0, ID.ENEMY);
		tempSize = 0;
		counter = 0.0;
		
		r = new Random();
		setY(r.nextInt(Game.HEIGHT - Game.HEIGHT/4) + Game.HEIGHT/4);
		setX(r.nextInt(Game.WIDTH - SIZE) + 1);
		setStartingX(getX());
		setStartingY(getY());
		
		if(r.nextBoolean()){
			setSpeedX(SPEED);
		}else{
			setSpeedX(SPEED * (-1));
		}
		
		if(r.nextBoolean()){
			setSpeedY(SPEED);
		}else{
			setSpeedY(SPEED * (-1));
		}
	}
	
	@Override
	public void tick() {
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
		
		
		updateSize();
		
		x += speedX;
		y += speedY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, tempSize, tempSize);
		g.setColor(Color.black);
		g.fillOval(x + 5, y + 5, tempSize - 10, tempSize - 10);
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, tempSize, tempSize);
	}
	
	private void updateSize() {
		if(counter >=  8 * Math.PI){
			counter = -0.03;
		}
		counter += 0.03;
		tempSize = (int) ((0.75 * SIZE) + (SIZE/4) * Math.sin(counter * Math.PI));
	}
}
