package music_player;

import java.util.Map;

import javax.sound.midi.Sequence;

import org.jfugue.integration.MusicXmlParser;

import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;

import org.jfugue.player.Player;

import org.staccato.StaccatoParserListener;

import converter.Converter;
import converter.Score;
import converter.measure.TabMeasure;
import custom_exceptions.TXMLException;
import models.*;
import models.measure.Measure;
import models.measure.attributes.Clef;
import models.measure.note.Note;
import utility.MusicXMLCreator;

public class XmlSequence {
	
	private String tab;
	private Score score;
	private String musicXML;
	private String pattern;
	
	public XmlSequence(String tab, Converter converter) {
		this.tab = tab;
		score = converter.getScore();
		musicXML = converter.getMusicXML();
		pattern = "";
	}
	
	// maybe better but for ez testing
	public XmlSequence(String tab, Score score, String musicXML) {
		this.tab = tab;
		this.score = score;
		this.musicXML = musicXML;
	}
	
	public Sequence generateSequence() {
		
		Clef clef = score.getMeasure(1).getModel().getAttributes().getClef();

		if (clef.getSign().equals("TAB")) return generateGuitar();
		else if (clef.getSign().equals("percussion")) return generateDrums();
			
		return null;
		
	}
	
	private Sequence generateGuitar()  {
			
		
		TabMeasure firstMeasure = score.getTabSectionList().get(0).getTabRow().getMeasureList().get(0);
		boolean noTimeSignature = (firstMeasure.getBeatCount() == 4 && firstMeasure.getBeatType() == 4);
		
		
		
		// musicXML does not specify explicit time signature if default 4/4
		if (noTimeSignature) {
//			System.out.println("  4/4\n" + tab.trim());
			Score score = new Score("  4/4\n" + tab.trim());
			musicXML = new MusicXMLCreator(score).generateMusicXML();
		}
		
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
		    	listen1.getPattern().setInstrument("Guitar");
		    	pattern = listen1.getPattern().toString();
		    	sequence =  new Player().getSequence(listen1);
			} 
			catch (Exception e) {
				
			}
	    	return sequence;
    	
	}
	
	private Sequence generateDrums() {
		System.out.println("generating drums");
		
    	Map <String,Instrument> instruments = DrumsHelper.getInstruments();
    	
    	// appending several times
		StringBuilder pattern = new StringBuilder("V9");
		
		try {
			for (Part i : score.getModel().getParts()) {
				for (Measure j : i.getMeasures()) {
					for (Note k : j.getNotesBeforeBackup()) {
						
						char duration = DrumsHelper.convertDuration(k.getType());
						String instrumentID = instruments.get(k.getInstrument().getId()).toString();
						String chord =  DrumsHelper.createChord(k.getChord(), instrumentID);
						
						pattern.append(String.format("%s[%s]%c", chord, instrumentID, duration));
					}
				}
			}
		
			}
		 catch (TXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Player player = new Player();
		System.out.println(pattern.toString());
		this.pattern = pattern.toString();
		return player.getSequence(pattern.toString());
	}
	
	public String getPattern() {
		return pattern;
	}
    
    public static void main(String[] args) {
    
    }

}
    	
