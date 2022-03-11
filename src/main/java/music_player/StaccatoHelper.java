package music_player;

import java.util.HashMap;


import models.measure.note.Chord;
import models.measure.note.Note;
import models.measure.note.notations.Tied;

// helper class to convert Note objects into Stacatto string components 
public class StaccatoHelper {
	
	// need this to be string so it can return empty
	public static String createChord(Chord chord) {
		// append + backwards
		if (chord != null) {
			return "+";
		}
		return " ";
	}
	
	public static char convertDuration(String noteType) {
		switch (noteType) {
			case "whole": return 'w';
			case "half": return 'h';
			case "quarter": return 'q';
			case "eighth": return 'i';
			case "16th": return 's';
			case "32nd": return 't';
			case "64th": return 'x';
			case "128th": return 'o';
		}
		//"If duration is not specified, the default duration of a quarter-note will be used."  
		return 'q';
	}
	
	// wat???????????????
	public static String getChromaticAlteration(Integer alter) {
		if (alter == null) return "";
		
		else if (alter == -1) return "b";
		else if (alter == 1) return "#";
		
		return "";
		
	}
	

    public static String getInstrument(String instrumentID) {
	   	HashMap <String,Instrument>  map = new HashMap<>();
	   	// some sounds might be off
	   	map.put("P1-I52", Instrument.RIDE_CYMBAL_2);
	   	map.put("P1-I46", Instrument.LO_FLOOR_TOM);
	   	map.put("P1-I47", Instrument.OPEN_HI_HAT);
	   	map.put("P1-I54", Instrument.RIDE_BELL);
	   	map.put("P1-I42", Instrument.LO_FLOOR_TOM);
	   	map.put("P1-I53", Instrument.CHINESE_CYMBAL);
	   	map.put("P1-I43", Instrument.CLOSED_HI_HAT);
	   	map.put("P1-I50", Instrument.CRASH_CYMBAL_1);
	   	map.put("P1-I48", Instrument.HI_MID_TOM);
	   	map.put("P1-I39", Instrument.ELECTRIC_SNARE);
	   	map.put("P1-I44", Instrument.HIGH_FLOOR_TOM);
	   	map.put("P1-I36", Instrument.BASS_DRUM);
	   	map.put("P1-I45", Instrument.PEDAL_HI_HAT);
   	
	   	return map.get(instrumentID).toString();    	
   }

	/*T for tie? a note may haVe multiple ties,
	 * each tie either has start/continue/end type instance variable
	 *  
	 *  "For a note that is at the end of a tie, prepend the dash to the beginning of the
		duration. If a note is in the middle of a series of notes that are all tied
		together, two dashes are used: one before the duration, and one after"
		
		start-
		-contiune-
		-end
	 */
	public static String getDurationWithTies(char duration, Note note) {
		
		if (note.getNotations() != null && note.getNotations().getTieds() != null) {
			String string = Character.toString(duration);
			for (Tied i : note.getNotations().getTieds()) {
				
				if (i.getType().equals("start")) string += "-";
				if (i.getType().equals("stop")) string = "-" + string;
				
			}
			return string;
		}
		return Character.toString(duration);
		
	}
	
	/*The Repeat Sign informs you that you should go back to the section
	 *  where there is another repeat sign and repeat it once again. 
	 * If there is no other sign, go back to the beginning and repeat everything up to that sign.
	 * 
	 */
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
