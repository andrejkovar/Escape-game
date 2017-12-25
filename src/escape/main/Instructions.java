package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Instructions extends GameObject{
	
	public static final String player1Commands = "W - up || S - down || A - left || D -right";
	public static final String player2Commands = "UP || DOWN || LEFT || RIGHT";
	
	private Handler handler;

	public Instructions(int x, int y, Handler handler){
		super(x, y, ID.INSTRUCTIONS);
		this.handler = handler;
	}

	@Override
	public void tick(){
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.white);
		g.drawString("Player 1 instructions: ", x, y);
		g.drawString(player1Commands, x, 32);
		if(!handler.isSinglePlayer()){
			g.setColor(Color.blue);
			g.drawString("Player 2 instructions: ", x + 740, y);
			g.drawString(player2Commands, x + 740, 32);
		}
	}

	@Override
	public Rectangle getRectangle() {
		return null;
	}
}
