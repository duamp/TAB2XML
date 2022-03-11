package music_player;

import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

import org.jfugue.integration.MusicXmlParser;

import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import org.staccato.StaccatoParserListener;

import converter.Converter;
import converter.Score;
import converter.measure.TabMeasure;
import custom_exceptions.TXMLException;
import models.*;
import models.measure.Measure;
import models.measure.attributes.Clef;
import models.measure.barline.BarLine;
import models.measure.note.Note;
import models.measure.note.notations.Tied;
import utility.MusicXMLCreator;

public class XmlSequence {
	
	private String tab;
	private Score score;
	private String musicXML;
	private Pattern pattern;
	
	public XmlSequence(String tab, Converter converter) {
		this.tab = tab;
		score = converter.getScore();
		musicXML = converter.getMusicXML();
		pattern = new Pattern();
	}
	
	// maybe better but for ez testing
	public XmlSequence(String tab, Score score, String musicXML) {
		this.tab = tab;
		this.score = score;
		this.musicXML = musicXML;
	}
	
	public Sequence generateSequence() {
		
		Clef clef = score.getMeasure(1).getModel().getAttributes().getClef();

		if (clef.getSign().equals("TAB")) return generateGuitar1();
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
		    	pattern = listen1.getPattern();
		    	System.out.println(pattern.toString());
		    	sequence =  new Player().getSequence(listen1);
			} 
			catch (Exception e) {
				
			}
	    	return sequence;
    	
	}
	//TODO: GRACE, BEND
	private Sequence generateGuitar1() {
		StringBuilder mainPattern = new StringBuilder("V0 I[Guitar]");
		StringBuilder repeatPattern = new StringBuilder();
		boolean repeat = false;
		try {
			
			for (Part i : score.getModel().getParts()) {
				for (Measure j : i.getMeasures()) {
					
					if (getBarLine(j.getBarlines(), "left") != null) repeat = true;
					System.out.println(repeat);
					// check if measure is not empty
					if (j.getNotesBeforeBackup() != null) {
						j.getBarlines();
						for (Note k : j.getNotesBeforeBackup()) {
							String note;
							
							if (k.getRest() != null) {
								note = "R";
							} else {
								note = k.getPitch().getStep() + StaccatoHelper.getChromaticAlteration(k.getPitch().getAlter()) +k.getPitch().getOctave();
							}
							
							char duration =  StaccatoHelper.convertDuration(k.getType());
							String tie = StaccatoHelper.getDurationWithTies(duration, k);
							String chord =  StaccatoHelper.createChord(k.getChord());
							
							
							mainPattern.append(String.format("%s%s%s", chord, note, tie));
							if (repeat) repeatPattern.append(String.format("%s%s%s", chord, note, tie));

					}
				}
					
					BarLine rightBar = getBarLine(j.getBarlines(), "right");
					
					if (rightBar != null) {
					   int times = Integer.valueOf(rightBar.getRepeat().getTimes());
					   mainPattern.append(repeatPattern.toString().repeat(times-1));
					   repeat = false;
					}
					
				}
			}
		
			}
		 catch (TXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Player player = new Player();
		System.out.println(mainPattern.toString());
		this.pattern = new Pattern(mainPattern.toString());
		return player.getSequence(mainPattern.toString());
		
	}


	private Sequence generateDrums() {
		System.out.println("generating drums");
		
    	
    	// appending several times
		StringBuilder pattern = new StringBuilder("V9");
	
		try {
			for (Part i : score.getModel().getParts()) {
				for (Measure j : i.getMeasures()) {
				
					
					// if measure is not empty
					if (j.getNotesBeforeBackup() != null) {
						
						for (Note k : j.getNotesBeforeBackup()) {
							String instrument;
							String format;
							
							if (k.getRest() != null) {
								instrument = "R";
								format = "%s%s%s";
							}
							// valid instrument
							else {
								instrument = StaccatoHelper.getInstrument(k.getInstrument().getId());
								format = "%s[%s]%s";
							}
							
							char duration = StaccatoHelper.convertDuration(k.getType());
							String tie = StaccatoHelper.getDurationWithTies(duration, k);
							String chord =  StaccatoHelper.createChord(k.getChord());
							
							pattern.append(String.format(format, chord, instrument, tie));
					}
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
		this.pattern = new Pattern(pattern.toString());
		return player.getSequence(pattern.toString());
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	

    private BarLine getBarLine( List<BarLine> barlines,  String location) {
    	if (barlines != null) {
			for (BarLine xd : barlines) {
				   if (xd.getLocation().equals(location)) {
					   return xd;
				   }
			}
		}
    	return null;
		
    }
    public static void main(String[] args) {
    	String tab ="";
	
		Score score = new Score(tab);
		MusicXMLCreator creator = new MusicXMLCreator(score);
		XmlSequence sequence = new XmlSequence(tab, score, creator.generateMusicXML());
		Player player = new Player();

		sequence.generateSequence();
		player.play(sequence.getPattern());
    }
    // "A grace note is a note played ever so slighty before the following note"
}
    	
