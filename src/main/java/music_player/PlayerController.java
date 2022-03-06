package music_player;

import java.io.File;
import java.io.IOException;


import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.midi.MidiFileManager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
public class PlayerController {
	
	private Sequencer sequencer;
	private Timeline mediaSliderAnimation;
	@FXML private Slider videoSlider;
	@FXML private Slider volumeSlider;
	@FXML private VBox GUI;

	 //TODO: remove awful scrolling sound
	// TODO: remove play button restart bug
	public PlayerController(XmlSequence sequence) throws InvalidMidiDataException, MidiUnavailableException, ValidityException, ParserConfigurationException, ParsingException, IOException {

        sequencer = MidiSystem.getSequencer();
        sequencer.setSequence(sequence.generateSequence());
        sequencer.open();
	       
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
			 //TODO: remove awful scrolling sound
			
			 //??
			 sequencer.stop();
			 sequencer.setTickPosition((long) ((videoSlider.getValue() / 100.0) * sequencer.getTickLength()));
			 
			 videoSlider.valueChangingProperty().addListener((observe, prevChanging, currChanging) -> {
				 if (!currChanging) sequencer.start();
			 });
			 
		 });
		 
		 videoSlider.setOnMousePressed((event) -> {
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
						double percentage = (double) sequencer.getTickPosition() / sequencer.getTickLength();
						videoSlider.setValue(percentage * 100);						
					}

                }));
		
		mediaSliderAnimation.setCycleCount(Timeline.INDEFINITE);
		mediaSliderAnimation.play();
		
		

		
		
		
	}
	
	@FXML
	public void pause() {
		if (sequencer.isRunning()) {
			sequencer.stop();	
			mediaSliderAnimation.pause();
		}
		
	}

	@FXML
	public boolean saveSong() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("MIDI file", "*.midi")
				);
		
		// window; arbitrary component
		File file = fileChooser.showSaveDialog(videoSlider.getScene().getWindow());
		System.out.println(file);
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
	public Sequencer getSequencer() {
		return this.sequencer;
	}



	
	
	private void wait(int ms) {
		try
		{
		    Thread.sleep(ms);
		}
		catch(InterruptedException ex)
		{
			// ???
		    Thread.currentThread().interrupt();
		}
	}


	 
	

}
