package escape.main;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class BackgroundMusic extends Thread{
	
	@Override
	public void run() {

        MP3Player player = new MP3Player(new File("Kojoti - Razuzdan I Lud.mp3"));
        player.setRepeat(true);
        player.play();
	}
}