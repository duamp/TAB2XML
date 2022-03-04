package drawPreview;

import java.awt.FontMetrics;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.LocationGuitar;

public class GuitarNotes {
	private LinkedList<LocationGuitar> aLGuitar;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 10;
	private Pane p;

	public GuitarNotes(Pane p, LinkedList<LocationGuitar> aL) {
		this.p = p;
		this.aLGuitar = aL;
	}

	public void drawGuitarNotes(int noteYLocation) {
		int noteX = 40;
		int measureNumber = 0;
		int timeDuration = 0;
		for(int j = 0; j < aLGuitar.size(); j++) {
			LocationGuitar lg = (LocationGuitar) aLGuitar.get(j);
			String note = "" + lg.getFret() + "";			
			if(!lg.isChord()) { //NOT CHORD
				timeDuration += lg.getDuration();

				//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
				if(measureNumber != 0 && measureNumber % 3 == 0) { 
					noteYLocation += moveMeasureDownValue;
					measureNumber = 0;
					noteX = this.startingXSpace + 20;
				}

				int yLocation = lg.getYCoord();
				Rectangle r = new Rectangle(noteX, noteYLocation + yLocation-15, 10, 15);
				r.setFill(Color.WHITE);
				r.opacityProperty().set(1);
				Text t = new Text(noteX, noteYLocation + yLocation, note);
				p.getChildren().add(r); //WHITE BACKGROUND
				p.getChildren().add(t); //TEXT



				//IF NEXT CHORD, DON'T CHANGE X YET!
				if(j < aLGuitar.size() - 2 && !aLGuitar.get(j+1).isChord()) {

					/* DETERMINES HOW CLOSE NEXT NOTE SHOULD BE */
					noteX += ((double)lg.getDuration()/64 * this.measureWidth); 

					/* KEEPS TRACK OF CURRENT MEASURE  */
					if(timeDuration == 64) {
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						noteX = measureNumber * measureWidth + this.startingXSpace + 10; 
						timeDuration = 0;
					}

				}

			} else { //IS CHORD
				int yLocation = lg.getYCoord();				
				Text t = new Text(noteX, noteYLocation + yLocation, note);
				p.getChildren().add(t);

				if((aLGuitar.size() - 2 > j+1) && !aLGuitar.get(j+1).isChord()) {
					noteX += ((double)lg.getDuration()/64 * measureWidth) + 10; 
					if(timeDuration == 64) {
						timeDuration = 0;
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;

					}
				}
			}
		}
	}
}
