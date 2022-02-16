package music_player;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.integration.MusicXmlParser;
import org.jfugue.integration.MusicXmlParserListener;
import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.staccato.StaccatoParser;
import org.staccato.StaccatoParserListener;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class Main {
	
	
//https://usermanual.wiki/Document/The20Complete20Guide20to20JFugue2C20Second20Edition2C20v200.723471053.pdf
    public static void main(String[] args) throws ValidityException, ParsingException, IOException, ParserConfigurationException, InvalidMidiDataException, MidiUnavailableException {
    	

	
    	

//    	MusicXmlParser parser = new MusicXmlParser();
//    	MidiParserListener listener = new MidiParserListener();
//    	parser.addParserListener(listener);
//    	parser.parse(new File("???.musicxml"));
//    	
//
//    	
//    	MidiParser parser1 = new MidiParser();
//    	StaccatoParserListener listen1 = new StaccatoParserListener();
//    	parser1.addParserListener(listen1);
//    	parser1.parse(listener.getSequence());
//    	
//    	
//    	Player player = new Player();
//    
//    	player.play(listen1.getPattern().setTempo(480).setInstrument("Guitar"));
//
//

    	
    	
    }
}