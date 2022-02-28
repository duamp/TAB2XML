package music_player;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.integration.MusicXmlParser;
import org.jfugue.integration.MusicXmlParserListener;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.staccato.StaccatoParser;
import org.staccato.StaccatoParserListener;

import GUI.PreviewFileController;
import converter.Converter;
import converter.Score;
import custom_exceptions.TXMLException;
import models.*;
import models.measure.Measure;
import models.measure.note.Note;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class XmlSequence {
	
	private Converter converter;
	private Sequence sequence;

	public static Sequence generate(String musicXML) {

    	MusicXmlParser parser;
    	Sequence sequence = null;
    	
		try {
			parser = new MusicXmlParser();
			MidiParserListener listener = new MidiParserListener();
	    	parser.addParserListener(listener);
	    	parser.parse(musicXML);
	    	MidiParser parser1 = new MidiParser();
	    	StaccatoParserListener listen1 = new StaccatoParserListener();
	    	parser1.addParserListener(listen1);
	    	parser1.parse(listener.getSequence());
	    	listen1.getPattern().setTempo(360).setInstrument("Guitar");
	    	sequence =  new Player().getSequence(listen1);
	    	return sequence;
		} 
		
		catch (Exception e) {
			System.out.println("musicXML invalid!");
			e.printStackTrace();
		}
		return sequence;
    	
	}
}
    	
