package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RecoveryPack extends GameObject{
	
	private final int SIZE = 64;
	
	private boolean recoveryUsed;
	private Random r;

	public RecoveryPack() {
		super(0, 0, ID.RECOVERY);
		
		setRecoveryUsed(false);
		r = new Random();
		
		setY(r.nextInt(Game.HEIGHT - SIZE * 2) + SIZE);
		setX(r.nextInt(Game.WIDTH - SIZE * 2) + SIZE);
		setSpeedX(0);
		setSpeedY(0);
		setStartingX(getX());
		setSpeedY(getY());
		
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRoundRect(x, y, SIZE, SIZE, 10, 10);
		g.setColor(Color.RED);
		g.fillRect(x + 24, y, 16, SIZE);
		g.fillRect(x, y + 24, SIZE, 16);
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, SIZE, SIZE);
	}

	public boolean isRecoveryUsed() {
		return recoveryUsed;
	}

	public void setRecoveryUsed(boolean recoveryUsed) {
		this.recoveryUsed = recoveryUsed;
	}

}
