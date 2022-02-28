package music_player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.staccato.StaccatoParser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
public class PlayerController extends TimerTask {
	
	private Sequencer sequencer;
	private Timeline mediaSliderAnimation;
	@FXML private Slider videoSlider;
	@FXML private Slider volumeSlider;
	@FXML private VBox GUI;

	 //TODO: remove awful scrolling sound
	// TODO: remove play button restart bug
	public PlayerController(String musicXML) {

	        try {
		        sequencer = MidiSystem.getSequencer();
		        sequencer.setSequence(XmlSequence.generate(musicXML));
		        sequencer.open();
		        
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	}
	
	



	// https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
	@FXML 
	private void initialize() throws IOException {

		 volumeSlider.valueProperty().addListener(ov -> {
		       if (volumeSlider.isValueChanging()) {

//		           mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
		       }
		    }
		);

		 // both for dragging / clicking for scrubbing 
		 videoSlider.setOnMouseDragged((event) -> {
			 System.out.println(videoSlider.getValue());
			 System.out.println(sequencer.getTickPosition());
			 //TODO: remove awful scrolling sound
			
			 //??
			 sequencer.stop();
			 sequencer.setTickPosition((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 
			 videoSlider.valueChangingProperty().addListener((observe, prevChanging, currChanging) -> {
				 if (!currChanging) sequencer.start();
			 });
			 
		 });
		 
		 videoSlider.setOnMousePressed((event) -> {
			 System.out.println((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 sequencer.setTickPosition((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 
			 
		 });
		 
		 
		
		 

	}
	
	@FXML
	public void play() throws InvalidMidiDataException, MidiUnavailableException {
		sequencer.start();
		System.out.println("started music");
		

		// if at end, rewind to start 
		if (sequencer.getMicrosecondPosition() == sequencer.getMicrosecondLength()) {
			sequencer.setTickPosition(0);
		}
		

		 mediaSliderAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), 
                new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						System.out.println((double) sequencer.getTickPosition() / sequencer.getTickLength());
						double percentage = (double) sequencer.getTickPosition() / sequencer.getTickLength();
						videoSlider.setValue(percentage * 100);						
					}

                }));
		
		mediaSliderAnimation.setCycleCount(Timeline.INDEFINITE);
		mediaSliderAnimation.play();
		
		

		
		
		
	}
	
	@FXML
	public void pause() {
		sequencer.stop();	
		mediaSliderAnimation.pause();
	}

	@FXML
	public boolean saveSong() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("MIDI file", "*.midi")
				);
		
		// block; arbitrary component
		File file = fileChooser.showSaveDialog(videoSlider.getScene().getWindow());
		
		if (file != null) {
          try {
			MidiFileManager.save(sequencer.getSequence(), file);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
          
		}
		return false;
	}
	
	public void closeSequencer() {
		sequencer.close();
	}



	@Override
	public void run() {
		
		System.out.println(sequencer.getTickPosition());
	}
	
	private void wait(int ms) {
		try
		{
		    Thread.sleep(ms);
		}
		catch(InterruptedException ex)
		{
			// ?
		    Thread.currentThread().interrupt();
		}
	}


	 
	

}
