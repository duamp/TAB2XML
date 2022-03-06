package music_player;

import java.util.HashMap;

import models.measure.note.Chord;

public class DrumsHelper {

	
	// need this to be string so it can return empty
	public static String createChord(Chord chord, String note) {
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

     public static HashMap<String,Instrument> getInstruments() {
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
		return map;
    	
    }
	

}
