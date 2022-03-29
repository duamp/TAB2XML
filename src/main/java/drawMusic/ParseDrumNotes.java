package drawMusic;

import java.util.LinkedList;

import models.ScorePartwise;
import note_information.DrumInformation;

public class ParseDrumNotes {
	private LinkedList<DrumInformation> aLDrums;
	private ScorePartwise sp;
	
	public ParseDrumNotes(ScorePartwise sp) {
		this.sp = sp;
	}

	/*
	 * CREATE LINKED LIST OF NOTES FOLLOWED BY SPECIFIC Y-LOCATION
	 * 1. Get <PITCH> STRING (STARTING POINT) + FRET (TIMES REPEATS i.e., add 17/2)
	 */
	
	public void createList() {
		aLDrums = new LinkedList<>();
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup() != null) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					DrumInformation noteInformation;
					noteInformation = new DrumInformation(
							this.getDisplayedStepNote(i, j),
							this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
							this.getDisplayedStepOctave(i, j),
							getXorO(i, j),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null, 
							i+1,
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()
							);
					aLDrums.add(noteInformation);
				}
			}
		}

	}
	
	public String getXorO(int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead() == null) {
			return null;
		}
		return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead().getXorOtype();
	}
	
	public String getDisplayedStepNote(int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched() != null) {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayStep();
		}
		return "R";
	}

	public int getDisplayedStepOctave(int i, int j) {
		if(	sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched() != null) {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayOctave();
		}
		return -1;
	}
	
	public int findDuration(String type, int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration() == null) {
			switch (type){
			case "16th":
				return 16;
			case "8":
				return 8;
			}
			return 0;
		} else {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration();
		}

	}

	public int getMeasureNumber() {
		return sp.getParts().get(0).getMeasures().size();
	}
	
	public LinkedList<DrumInformation> getDrumInformation() {
		return this.aLDrums;
	}
}
