package escape.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class MenuBackground {

	private static final int NUM_OF_ENEMIES_IN_BACKGROUD = 10;
	
	private LinkedList<Enemy> enemies = new LinkedList<>();
	
	public MenuBackground(){
		for(int i = 0; i < NUM_OF_ENEMIES_IN_BACKGROUD; i++){
			enemies.add(new Enemy());
		}
	}
	
	public void tick(){
		for(Enemy tempEnemy : enemies){
			tempEnemy.tick();
		}
	}
	
	public void render(Graphics g){
		for(Enemy tempEnemy : enemies){
			tempEnemy.render(g);
		}
	}
}
