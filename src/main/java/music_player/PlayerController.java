package music_player;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
//An instance of this class is created when the FXML file is loaded r this to work, the controller class must have a no-argument constructor.
public class PlayerController {
	
	private MediaPlayer mediaPlayer;
	@FXML private Slider videoSlider;
	@FXML private Slider volumeSlider;
	@FXML private VBox GUI;
	
	public PlayerController() {
	
	}
	
	// https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
	//TODO: File path parameters.
	@FXML 
	private void initialize() {
//		 GUI.setDisable(true);
		 volumeSlider.setValue(50.0);
		
		 volumeSlider.valueProperty().addListener(ov -> {
			       if (volumeSlider.isValueChanging()) {
			    	   // setVolume() [0.0, 1.0]
			           mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
			       }
			    }
			);
		 
		 // for prototype only 
		 Media song = new Media(new File("Bach.mp3").toURI().toString());
		 mediaPlayer = new MediaPlayer(song);
		 initalizePlayer();
	}
	
	@FXML
	public void play() {

		// if at end, rewind to start 
		if (mediaPlayer.getCurrentTime().equals(mediaPlayer.getStopTime())) {
			mediaPlayer.seek(mediaPlayer.getStartTime());
		}
		
		mediaPlayer.play();
		
	}
	
	@FXML
	public void pause() {
		mediaPlayer.pause();
	}
	

	
	@FXML
	private void promptFile() {
		
		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().add(new ExtensionFilter("musicxml", "*.musicxml"));
		File file = fileChooser.showOpenDialog(null);
		
		if (file != null) {
			
			 Media song = new Media(file.toURI().toString());
			 mediaPlayer = new MediaPlayer(song);
			 initalizePlayer();
			 GUI.setDisable(false);
		}

	}
	
	private void initalizePlayer() {
		
		 //videoslider scrubbing
		 videoSlider.setOnMouseDragged((event) -> {
			 System.out.println(videoSlider.getValue());
			 mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(videoSlider.getValue() / 100.0));
			 
			 
		 });
		 
		 videoSlider.setOnMousePressed((event) -> {
			 System.out.println(videoSlider.getValue());
			 mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(videoSlider.getValue() / 100.0));
			 
			 
		 });
		 
		 // scrubbing animation 
		 // add change listener to current time 
		 mediaPlayer.currentTimeProperty().addListener((obs, oldValue, newValue) -> {
			 videoSlider.setValue((newValue.toSeconds() / mediaPlayer.getStopTime().toSeconds()) * 100);
		 });
		
	}
	
	@FXML
	private void Displayhelp() {
		throw new UnsupportedOperationException("Not yet Implemented");
	}

	
	
	
}
