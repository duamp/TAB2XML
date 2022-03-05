package drawPreview;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.LocationDrums;
import models.LocationGuitar;

public class DrawDrumsNotes {

	private LinkedList<LocationDrums> aLDrums;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 10;
	private int currentNoteYLocation = 0;
	private int unitsInMeasure = 0;
	private Pane p;
	
	
		public DrawDrumsNotes(Pane pane, LinkedList<LocationDrums> aLDrums, int unitsInMeasure) {
			this.aLDrums = aLDrums;
			this.p = pane;
			this.unitsInMeasure = unitsInMeasure;
		}


		public void drawDrumNotes() {
			int noteX = 40;
			int measureNumber = 0;
			int timeDuration = 0;
			for(int j = 0; j < aLDrums.size(); j++) {
				LocationDrums ld = (LocationDrums) this.aLDrums.get(j);
				if(!ld.isMulti()) {
					timeDuration += ld.getDuration();
					//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
					if(measureNumber != 0 && measureNumber % 3 == 0) { 
						currentNoteYLocation += moveMeasureDownValue;
						measureNumber = 0;
						noteX = this.startingXSpace + 20;
					}
					double yLocation = ld.getYCoord();
					Rectangle r = new Rectangle(noteX, currentNoteYLocation + yLocation-15, 10, 15);
					r.setFill(Color.WHITE);
					r.opacityProperty().set(1);
					Text t = new Text(noteX, currentNoteYLocation + yLocation, ld.getNote());// implement notes to actually draw here
					p.getChildren().add(r); //WHITE BACKGROUND
					p.getChildren().add(t); //TEXT
	
					if(j < aLDrums.size() - 2 && !aLDrums.get(j+1).isMulti()) {
						noteX += ((double)ld.getDuration()/unitsInMeasure * this.measureWidth); 
						/* KEEPS TRACK OF CURRENT MEASURE  */
						if(timeDuration == unitsInMeasure) {
							/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
							measureNumber++;
							noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
							timeDuration = 0;
						}
					}
				}else {
					double yLocation = ld.getYCoord();
					Rectangle r = new Rectangle(noteX, currentNoteYLocation + yLocation-15, 10, 15);
					r.setFill(Color.WHITE);
					r.opacityProperty().set(1);
					Text t = new Text(noteX, currentNoteYLocation + yLocation, "o");// implement notes to actually draw here
					p.getChildren().add(r);
					p.getChildren().add(t);
					if((aLDrums.size() - 2 > j+1) && !aLDrums.get(j+1).isMulti()) {
						noteX += ((double)ld.getDuration()/unitsInMeasure * measureWidth) + 10; 
						if(timeDuration == unitsInMeasure) {
							timeDuration = 0;
							/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
							measureNumber++;
							noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						}
					}
				}

			}
		}
	}

