package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends GameObject{
	
	public static int score;
	public static int scoreStep = 500;
	public static int level;

	public Score(int x, int y) {
		super(x, y, ID.SCORE);
		score = 0;
		level = 1;
	}
	
	@Override
	public void tick(){
		score++;
		if(score % scoreStep == 0){
			level++;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString("Score: " + score, x, y);
		g.drawString("Level: " + level, x, y + 16);
	}

	@Override
	public Rectangle getRectangle() {
		return null;
	}
}
