package escape.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBar extends GameObject{

	private Handler handler;
	
	public HealthBar(int x, int y, Handler handler){
		super(x, y, ID.HEALTH);
		this.handler = handler;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRoundRect(x, y, 200, 25, 10, 10);
		if(!handler.isSinglePlayer()){
			g.fillRoundRect(x + 740, y, 200, 25, 10, 10);
		}
		for(Player tempPlayer : handler.getPlayers()){
			if(tempPlayer.getId() == ID.PLAYER1){
				g.setColor(Color.green);
				g.fillRoundRect(x, y, tempPlayer.getHealth(), 25, 10, 10);
				g.setColor(Color.black);
				g.drawString("Player 1 health", x + 55, y + 18);
			}
			if(tempPlayer.getId() == ID.PLAYER2){
				g.setColor(Color.green);
				g.fillRoundRect(x + 740, y, tempPlayer.getHealth(), 25, 10, 10);
				g.setColor(Color.black);
				g.drawString("Player 2 health", x + 805, y + 18);
			}
		}
	}

	@Override
	public Rectangle getRectangle() {
		return null;
	}
}
