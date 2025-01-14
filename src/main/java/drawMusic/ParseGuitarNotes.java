package drawMusic;

import java.util.LinkedList;

import converter.Converter;
import models.ScorePartwise;
import note_information.GuitarInformation;

public class ParseGuitarNotes extends MainParser {
	private LinkedList<GuitarInformation> aLGuitar;
	private Converter convertor;
	
	public ParseGuitarNotes(ScorePartwise sp, Converter convertor){
		super(sp);
		this.convertor = convertor;
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
						sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlides(),
						convertor.getScore().getMeasureList().get(i).getRepeatCount()
						);

				aLGuitar.add(noteInformation);
			}
		}
	}
	
	public LinkedList<GuitarInformation> getGuitarInformation() {
		return this.aLGuitar;
	}
}
