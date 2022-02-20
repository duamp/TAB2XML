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

import org.jfugue.midi.MidiParserListener;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.staccato.StaccatoParser;

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
public class PlayerController  {
	
	private Sequencer sequencer;

	@FXML private Slider videoSlider;
	@FXML private Slider volumeSlider;
	@FXML private VBox GUI;

	
	public PlayerController() throws MidiUnavailableException, IOException, InvalidMidiDataException{

	        sequencer = MidiSystem.getSequencer();
	        sequencer.open();
	        InputStream is = new BufferedInputStream(new FileInputStream(new File("./src/main/java/music_player/songs/swag.midi")));
	        
	        // Sets the current sequence on which the sequencer operates.
	        // The stream must point to MIDI file data.
	        sequencer.setSequence(is);
	 
	    
	}
	
	// https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
	@FXML 
	private void initialize() {
		
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
			 sequencer.setTickPosition((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
		 });
		 
		 videoSlider.setOnMousePressed((event) -> {
			 System.out.println((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 sequencer.setTickPosition((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 
			 
		 });
		 
		 // 
		
		 
//		 mediaPlayer.currentTimeProperty().addListener((obs, oldValue, newValue) -> {
//			 videoSlider.setValue((newValue.toSeconds() / mediaPlayer.getStopTime().toSeconds()) * 100);
//		 });
	}
	
	@FXML
	public void play() throws InvalidMidiDataException, MidiUnavailableException {
		sequencer.start();
		
		// if at end, rewind to start 
		if (sequencer.getMicrosecondPosition() == sequencer.getMicrosecondLength()) {
			sequencer.setTickPosition(0);
		}
		
//		Timer timer = new Timer();
//		try {
//			timer.schedule(new PlayerController(), 0, 1000);
//		} catch (MidiUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidMidiDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	@FXML
	public void pause() {
		sequencer.stop();
		
		
	}
	

	
//	@FXML
//	private void promptFile() {
//		
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().addAll(
//				new ExtensionFilter("musicxml", "*.musicxml"),
//				new ExtensionFilter("MIDI file", "*.midi")
//				);
//		
//		File file = fileChooser.showOpenDialog(null);
//		
//		if (file != null) {
//			
//	
//			 initalizePlayer();
//			 GUI.setDisable(false);
//		}
//
//	}

	@FXML
	private void Displayhelp() {
		throw new UnsupportedOperationException("Not yet Implemented");
	}
	
	public void closeSequencer() {
		sequencer.close();
	}

//		public void run() {
//			videoSlider.setValue(sequencer.getTickPosition());
//	    }
//	 
	 
	public static void main (String args[])throws MidiUnavailableException, IOException, InvalidMidiDataException {
	
		 
	}
	

}
