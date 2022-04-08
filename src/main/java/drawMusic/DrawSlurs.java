package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import models.ScorePartwise;
import note_information.DrumInformation;
import note_information.GuitarInformation;

public class DrawSlurs {
	private LinkedList<GuitarInformation> aLGuitar;
	private LinkedList<DrumInformation> aLDrums;

	private Pane p;

	public DrawSlurs(LinkedList<GuitarInformation> aLGuitar, LinkedList<DrumInformation> aLDrums, Pane p) {
		this.aLGuitar = aLGuitar;
		this.aLDrums = aLDrums;
		this.p = p;
	}

	public void makeSlurLine(int noteX1, int noteX2, int noteY) {
		QuadCurve quadcurve = new QuadCurve(noteX1+2, noteY, getSlurMiddleX(noteX1+2, noteX2+2), noteY+10, noteX2+2, noteY);
		quadcurve.setStroke(Color.BLACK);
		quadcurve.setFill(Color.WHITE);
		//		c.setStartX(noteX1);
		//		c.setEndX(noteX2);
		//		c.setControlX1(getSlurMiddleX(noteX1, noteX2));
		//		c.setControlY1(noteY+5);
		//		c.setStartY(noteY);
		//		c.setEndY(noteY);

		p.getChildren().add(quadcurve);
	}

	public int getSlurMiddleX(int noteX1, int noteX2) {
		return noteX1 + ((noteX2 - noteX1)/2);
	}

	public void drawSlursGuitar() {
		for(int i = 0; i < aLGuitar.size(); i++) {
			GuitarInformation lg = aLGuitar.get(i);
			if(lg.isSlur()) {
				if(lg.isGrace()) {
					
					if (lg.getSlur().get(0).getType().equals("stop") && lg.getSlur().size() == 1) {
						continue;
					}
					else if(aLGuitar.get(i+1).isChord()) {
						int moveforward = findHowManyNotesInChord(i + 1);
						i += moveforward;
						makeSlurLine(lg.getNoteX() + 7, aLGuitar.get(i).getNoteX() + 2, lg.getNoteY() +  3);
					} 
					else {
						makeSlurLine(lg.getNoteX() + 5, aLGuitar.get(i+1).getNoteX() + 2, lg.getNoteY() +  3);
					}
					
				} else {
					if (lg.getSlur().get(0).getType().equals("stop") && lg.getSlur().size() == 1) {
						continue;
					}
					else if(aLGuitar.get(i+1).isChord()) {
						int moveforward = findHowManyNotesInChord(i + 1);
						i += moveforward;
						makeSlurLine(lg.getNoteX() + 3, aLGuitar.get(i).getNoteX() + 2, lg.getNoteY() +  3);
					} 
					else {
						makeSlurLine(lg.getNoteX() + 1, aLGuitar.get(i+1).getNoteX() + 2, lg.getNoteY() +  3);
					}

				}
			}
		}
	}

	public void drawSlursDrums() {
		for(int i = 0; i < aLDrums.size(); i++) {
			DrumInformation lg = aLDrums.get(i);
			if(lg.isSlur()) {

				if (lg.getSlur().get(0).getType().equals("stop") && lg.getSlur().size() == 1) {
					continue;
				}
				else if(aLDrums.get(i+1).isChord()) {
					int moveforward = findHowManyNotesInChord(i + 1);
					i += moveforward;
					makeSlurLine(lg.getNoteX() + 5, aLDrums.get(i).getNoteX() + 2, lg.getNoteY() +  3);
				} 
				else {
					makeSlurLine(lg.getNoteX() + 5, aLDrums.get(i+1).getNoteX() + 2, lg.getNoteY() +  3);
				}

			}
		}
	}

	public int findHowManyNotesInChord( int start) {
		int index = 0;
		GuitarInformation lg = aLGuitar.get(start);
		while(lg.isChord()) {
			lg = aLGuitar.get(start+index);
			index++;
		}
		return index;
	}
}
