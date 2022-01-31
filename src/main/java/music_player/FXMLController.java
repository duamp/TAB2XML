package music_player;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FXMLController {
	
	public void test() {
	
	    System.out.println("Working Directory = " + System.getProperty("user.dir"));

		
	    Media song = new Media(new File("Bach.mp3").toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(song);
		mediaPlayer.play();
		
	}
}
