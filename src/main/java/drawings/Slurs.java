package drawings;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import models.LocationGuitar;
import models.ScorePartwise;

public class Slurs {
	private LinkedList<LocationGuitar> aL;
	private Pane p;
	private ScorePartwise sp;

	public Slurs(LinkedList<LocationGuitar> aL, Pane p, ScorePartwise sp) {
		this.aL = aL;
		this.p = p;
		this.sp = sp;
	}

	public void makeSlurLine(int noteX1, int noteX2, int noteY) {
		QuadCurve quadcurve = new QuadCurve(noteX1, noteY, getSlurMiddleX(noteX1, noteX2), noteY-10, noteX2, noteY);
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

	public void drawNotesWithSlurs() {
		for(int i = 0; i < aL.size(); i++) {
			LocationGuitar lg = aL.get(i);
			if(lg.isSlur()) {
				if(aL.get(i+1).isChord()) {
					int moveforward = findHowManyNotesInChord(i + 1);
					i += moveforward;
					makeSlurLine(lg.getNoteX() + 3, aL.get(i).getNoteX() + 3, lg.getNoteY() -  10);
				} else {
					makeSlurLine(lg.getNoteX() + 3, aL.get(i+1).getNoteX() + 3, lg.getNoteY() -  10);
				}
				
				if(lg.getSlur().size() == 2 && lg.getSlur().get(0).getType() == "start" && lg.getSlur().get(1).getType() == "end") {
					i--;
				} 
				i++;

			}
		}
	}
	public int findHowManyNotesInChord( int start) {
		int index = 0;
		LocationGuitar lg = aL.get(start);
		while(lg.isChord()) {
			lg = aL.get(start+index);
			index++;
		}
		return index;
	}
}
