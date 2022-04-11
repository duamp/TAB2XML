package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import note_information.GuitarInformation;

public class DrawSlides {
	private LinkedList<GuitarInformation> aL;
	private Pane p;
	private int nos;

	public DrawSlides(LinkedList<GuitarInformation> aL, Pane p) {
		this.aL = aL;
		this.p = p;
	}

	public void drawSlides() {
		for(int i = 0; i < aL.size(); i++) {
			GuitarInformation lg = aL.get(i);
			if(lg.isSlide()) {
				nos++;

				if (lg.getSlide().get(0).getType().equals("stop") && lg.getSlide().size() == 1) {
					continue;
				}
				else if(aL.get(i+1).isChord()) {
					int moveforward = findHowManyNotesInChord(i + 1);
					i += moveforward;
					makeSlideLine(lg.getNoteX() + 3, aL.get(i).getNoteX() + 3, lg.getNoteY() -  10, i);
				} 
				else {
					makeSlideLine(lg.getNoteX() + 3, aL.get(i+1).getNoteX() + 3, lg.getNoteY() -  10, i);
				}


			}
		}
	}
	
	public void makeSlideLine(int noteX1, int noteX2, int noteY, int index) {
		int noteYfirst = noteY;
		int noteYsecond = noteY;
		if(aL.get(index + 1) != null && aL.get(index).getFret() > aL.get(index + 1).getFret()) {
			noteYfirst = noteY + 9;
			noteYsecond = noteY + 3;
		} else {
			noteYfirst = noteY + 3;
			noteYsecond = noteY + 9;
		}
		Line l = new Line(noteX1 + 10, noteYfirst, noteX2 - 10, noteYsecond);
		p.getChildren().add(l);
	}
	
	public int findHowManyNotesInChord( int start) {
		int index = 0;
		GuitarInformation lg = aL.get(start);
		while(lg.isChord()) {
			lg = aL.get(start+index);
			index++;
		}
		return index;
	}
	public int getNos() {return this.nos;}
}
