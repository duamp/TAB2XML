package drawMusic;

import java.util.LinkedList;

import converter.Converter;
import models.ScorePartwise;
import note_information.DrumInformation;

public class ParseDrumNotes extends MainParser {
	private LinkedList<DrumInformation> aLDrums;
	private Converter convertor;

	public ParseDrumNotes(ScorePartwise sp, Converter convertor) {
		super(sp);
		this.convertor = convertor;
	}

	/*
	 * CREATE LINKED LIST OF NOTES FOLLOWED BY SPECIFIC Y-LOCATION
	 * 1. Get <PITCH> STRING (STARTING POINT) + FRET (TIMES REPEATS i.e., add 17/2)
	 */

	public void createList() {
		aLDrums = new LinkedList<>();
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup() != null && sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size() != 0) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					DrumInformation noteInformation;
					if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations() != null) {
						noteInformation = new DrumInformation(
								this.getDisplayedStepNote(i, j),
								this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
								this.getDisplayedStepOctave(i, j),
								getXorO(i, j),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null, 
								i+1,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(),
								convertor.getScore().getMeasureList().get(i).getRepeatCount(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlurs(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getGrace()

								);
					} else {
						noteInformation = new DrumInformation(
								this.getDisplayedStepNote(i, j),
								this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
								this.getDisplayedStepOctave(i, j),
								getXorO(i, j),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null, 
								i+1,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(),
								convertor.getScore().getMeasureList().get(i).getRepeatCount(),
								null,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getGrace()
								);
					}
					aLDrums.add(noteInformation);
				}
			} else { //ADD REST MEASURE
				DrumInformation noteInformation;
				noteInformation = new DrumInformation(); //DEFAULT NULL VALUES
				aLDrums.add(noteInformation);
			}
		}

	}

	public String getXorO(int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead() == null) {
			return null;
		} else if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead().getParentheses() != null) {
			return "(" + sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead().getXorOtype(true) + ")";
		}
		return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead().getXorOtype(true);
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

	public LinkedList<DrumInformation> getDrumInformation() {
		return this.aLDrums;
	}
}
