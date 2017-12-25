package escape.main;

import java.awt.Graphics;
import java.util.LinkedList;

import escape.main.Game.STATUS;

public class Handler {
	
	public static final int DEFAULT_FONT_SIZE = 12;
	
	public LinkedList<GameObject> objects = new LinkedList<>();
	
	private LinkedList<Enemy> enemies = new LinkedList<>();
	private LinkedList<Player> players = new LinkedList<>();
	private LinkedList<RecoveryPack> recoveries = new LinkedList<>();
	
	private boolean isSinglePlayer;
	private boolean gameOver;
	private int totalScore;
	private Player winner;
	private Game game;
	
	public Handler(Game game) {
		this.game = game;
		totalScore = 0;
		winner = null;
		gameOver = false;
	}
	
	public void tick(){
		for(GameObject tempObject : objects){
			tempObject.tick();
		}
		
		objects.removeIf(t->{
			if(t instanceof RecoveryPack){
				if(((RecoveryPack) t).isRecoveryUsed()){
					recoveries.remove(t);
					return true;
				}
			}
			return false;
		});
		
		if(gameOver){
			game.setGameStatus(STATUS.GAMEOVER);
			for(Player winner : players){
				if(!winner.isPlayerDead()){
					this.winner = winner;
					break;
				}
			}
			totalScore = Score.score;
			return;
		}
		
		//periodic recovery
		if(Score.score % (Score.scoreStep * 3) == 0){
			addObject(new RecoveryPack());
		}
		
		if(Score.score % Score.scoreStep == 0 ){
				addObject(new Enemy());
				addObject(new Enemy());
		}
		
		//levels +3
		if(Score.score % (Score.scoreStep * 4) == 0){
			for(Player tempPlayer : players){
				addObject(new FollowEnemy(tempPlayer));
			}
		}
	}
	
	public void render(Graphics g){
		for(GameObject tempObject : objects){
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject newObject){
		objects.add(newObject);
		if(newObject instanceof Enemy){
			enemies.add((Enemy) newObject);
		}
		if(newObject instanceof Player){
			players.add((Player) newObject);
		}
		if(newObject instanceof RecoveryPack){
			recoveries.add((RecoveryPack) (newObject));
		}
	}
	
	public boolean removeObject(GameObject remObject){
		return objects.remove(remObject);
	}
	
	public void removeAllObjects(){
		objects.clear();
		enemies.clear();
		players.clear();
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(LinkedList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public LinkedList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(LinkedList<Player> players) {
		this.players = players;
	}

	public LinkedList<RecoveryPack> getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(LinkedList<RecoveryPack> recoveries) {
		this.recoveries = recoveries;
	}

	public LinkedList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(LinkedList<GameObject> objects) {
		this.objects = objects;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	public void setTotalScore(int newScore){
		totalScore = newScore;
	}
	
	public Player getWinner(){
		return winner;
	}
	
	public void setWinner(Player winner){
		this.winner = winner;
	}

	public boolean isSinglePlayer() {
		return isSinglePlayer;
	}

	public void setSinglePlayer(boolean isSinglePlayer) {
		this.isSinglePlayer = isSinglePlayer;
	}
}


