package drawMusic;

import java.util.LinkedList;

import models.ScorePartwise;
import note_information.GuitarInformation;

public class ParseGuitarNotes {
	private LinkedList<GuitarInformation> aLGuitar;
	private ScorePartwise sp;
	
	public ParseGuitarNotes(ScorePartwise sp){
		this.sp = sp;
	}
	
	public void createList() {
		aLGuitar = new LinkedList<>();

		for(int i = 0; i < this.getMeasureNumber(); i++) {
			for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
				GuitarInformation noteInformation = new GuitarInformation(	
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
						this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null,
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlurs(), 
						i + 1,
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(),
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getGrace(),
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlides() 
						);

				aLGuitar.add(noteInformation);
			}
		}
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
	
	public LinkedList<GuitarInformation> getGuitarInformation() {
		return this.aLGuitar;
	}
}
