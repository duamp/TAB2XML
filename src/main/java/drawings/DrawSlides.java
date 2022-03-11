package drawings;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import note_information.GuitarInformation;

public class DrawSlides {
	private LinkedList<GuitarInformation> aL;
	private Pane p;

	public DrawSlides(LinkedList<GuitarInformation> aL, Pane p) {
		this.aL = aL;
		this.p = p;
	}

	
	public void drawSlides() {
		for(int i = 0; i < aL.size(); i++) {
			GuitarInformation lg = aL.get(i);
			if(lg.isSlide()) {

				if (lg.getSlide().get(0).getType().equals("stop") && lg.getSlur().size() == 1) {
					continue;
				}
				else if(aL.get(i+1).isChord()) {
					int moveforward = findHowManyNotesInChord(i + 1);
					i += moveforward;
					makeSlideLine(lg.getNoteX() + 3, aL.get(i).getNoteX() + 3, lg.getNoteY() -  10);
				} 
				else {
					makeSlideLine(lg.getNoteX() + 3, aL.get(i+1).getNoteX() + 3, lg.getNoteY() -  10);
				}

				//				if(aL.get(i+1).getSlur().size() == 2 && aL.get(i+1).getSlur().get(0).getType() == "start" && aL.get(i+1).getSlur().get(1).getType() == "end") {
				//					i--;
				//				} 

			}
		}
	}
	
	public void makeSlideLine(int noteX1, int noteX2, int noteY) {
		Line l = new Line(noteX1, noteY, noteX2, noteY);
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
}
