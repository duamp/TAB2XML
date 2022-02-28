package music_player;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.integration.MusicXmlParser;
import org.jfugue.integration.MusicXmlParserListener;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.staccato.StaccatoParser;
import org.staccato.StaccatoParserListener;

import GUI.PreviewFileController;
import converter.Score;
import custom_exceptions.TXMLException;
import models.*;
import models.measure.Measure;
import models.measure.note.Note;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class XmlToMidi1 {
	
	
	private Pattern mainPattern;
	private ScorePartwise score;
	
	public XmlToMidi1(Score score) throws TXMLException {
		mainPattern = new Pattern();
		this.score = score.getModel();
	}
	



	public void parseNotes() {
		
		for (Part i : score.getParts()) {
			for (Measure j : i.getMeasures()) {
				for (Note k : j.getNotesBeforeBackup()) {
					mainPattern.add(new Pattern(k.getPitch().getStep())); 
				}
			}
		}
		
		Player player = new Player();
		player.play(mainPattern);
    	
	}

	//https://usermanual.wiki/Document/The20Complete20Guide20to20JFugue2C20Second20Edition2C20v200.723471053.pdf 37
    public static void main(String[] args) throws ValidityException, ParsingException, IOException, ParserConfigurationException, InvalidMidiDataException, MidiUnavailableException {
    	
    	
    	
		/* http://www.jfugue.org/examples.html
		 * V for voices (wtf????????)
		 * I for specifying instruments, must map to MIDI dictionary
		 * '|' (pipe) for indicating measures
		 * 
		 * 
		 */
    	
    	


    	//"V0 I24 E2ha90 B2ha90 E3ha90 G#3ha90 B3ha90 E4ha90 B3ha90 G#3ha90 E4w4a90 @4.0 B3w4a90 @4.0 G#3w4a90 @4.0 E3w4a90 @4.0 B2w4a90 @4.0 E2w4a90"
    	



    	
    	
    }
}