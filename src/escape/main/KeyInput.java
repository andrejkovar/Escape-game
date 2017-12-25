package escape.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(GameObject tempObject : handler.objects){
			if(tempObject.getId() == ID.PLAYER1){
				if(key == KeyEvent.VK_W){
					tempObject.setSpeedY(Player.SPEED * (-1));
					tempObject.setSpeedX(0);
				}
				else if(key == KeyEvent.VK_S){
					tempObject.setSpeedY(Player.SPEED);
					tempObject.setSpeedX(0);
				}
				else if(key == KeyEvent.VK_A){
					tempObject.setSpeedX(Player.SPEED * (-1));
					tempObject.setSpeedY(0);
				}
				else if(key == KeyEvent.VK_D){
					tempObject.setSpeedX(Player.SPEED);
					tempObject.setSpeedY(0);
				}
			}
			if(tempObject.getId() == ID.PLAYER2){
				if(key == KeyEvent.VK_UP){
					tempObject.setSpeedY(Player.SPEED * (-1));
					tempObject.setSpeedX(0);
				}
				else if(key == KeyEvent.VK_DOWN){
					tempObject.setSpeedY(Player.SPEED);
					tempObject.setSpeedX(0);
				}
				else if(key == KeyEvent.VK_LEFT){
					tempObject.setSpeedX(Player.SPEED * (-1));
					tempObject.setSpeedY(0);
				}
				else if(key == KeyEvent.VK_RIGHT){
					tempObject.setSpeedX(Player.SPEED);
					tempObject.setSpeedY(0);
				}
			}
		}
		super.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		super.keyReleased(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		super.keyTyped(e);
	}
}
