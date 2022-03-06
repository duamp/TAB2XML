package drawings;

import java.awt.FontMetrics;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.GuitarInformation;
import models.ScorePartwise;
import models.measure.note.Note;

public class DrawGuitarNotes {
	private LinkedList<GuitarInformation> aLGuitar;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 15;
	private int currentcurrentNoteYLocation = 0;
	private Pane p;
	private int unitsInMeasure = 0;
	private int currentNoteYLocation = 0;
	private ScorePartwise sp;
	private double divisionConstant = 1.1;

	public DrawGuitarNotes(Pane p, LinkedList<GuitarInformation> aL, ScorePartwise sp) {
		this.p = p;
		this.aLGuitar = aL;
		this.sp = sp;
	}

	public void drawGuitarNotes() {
		int noteX = 40;
		int measureNumber = 0;
		int timeDuration = 0;
		int whichMeasure = 0;
		for(int j = 0; j < aLGuitar.size(); j++) {
			GuitarInformation lg = (GuitarInformation) aLGuitar.get(j);

			if(j == 0) {
				this.unitsInMeasure = setUnitsInMeasure(lg, whichMeasure);
			}

			String note = "" + lg.getFret() + "";
			int yLocation = lg.getYCoord();	

			/*
			 * CHECKS
			 * 1. IF FRET >= 10, size of background erased
			 * 2. IF SLUR, create slur at location and append at end.
			 */
			boolean flag = (lg.getFret() >= 10);
			boolean slur = lg.isSlur();

			if(!lg.isChord()) { //NOT CHORD
				timeDuration += lg.getDuration();

				//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
				if(measureNumber != 0 && measureNumber % 3 == 0) { 
					currentNoteYLocation += moveMeasureDownValue;
					measureNumber = 0;
					noteX = this.startingXSpace + 20;
				}
				/*
				 * 1. ADD WHITE BACKGROUND TO REMOVE LINE BEHIND NOTEX LOCATION
				 * 2. ADD NEW NOTE (i.e., A, B in form on TABS ... 1, 2) IN LOCATION WITH NO BACKGROUND
				 */

				removeLineBehindNote(noteX, yLocation, flag);

				Text t = new Text(noteX, currentNoteYLocation + yLocation, note);
				p.getChildren().add(t); //TEXT

				//RECORDS noteX && noteY in arraylist for later access
				lg.setNoteX(noteX); 	
				lg.setNoteY(currentNoteYLocation + yLocation);

				//IF NEXT CHORD, DON'T CHANGE X YET!
				if(j < aLGuitar.size() - 2 && !aLGuitar.get(j+1).isChord()) {

					/* DETERMINES HOW CLOSE NEXT NOTE SHOULD BE */
					noteX += ((double)lg.getDuration()/(unitsInMeasure*divisionConstant) * this.measureWidth); 

					/* KEEPS TRACK OF CURRENT MEASURE  */
					if(timeDuration == unitsInMeasure) {
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						timeDuration = 0;
						whichMeasure++;
						this.unitsInMeasure = setUnitsInMeasure(lg, whichMeasure);
					}

				}

			} else { //IS CHORD
				/*
				 * Flag indicates fret >= 10 because Rectange to remove background space should be wider
				 */
				removeLineBehindNote(noteX, yLocation, flag);
				Text t = new Text(noteX, currentNoteYLocation + yLocation, note);
				p.getChildren().add(t);
				//RECORDS noteX && noteY in arraylist for later access
				lg.setNoteX(noteX); 	
				lg.setNoteY(currentNoteYLocation + yLocation);

				if((aLGuitar.size() - 2 > j+1) && !aLGuitar.get(j+1).isChord()) {
					noteX += ((double)lg.getDuration()/(unitsInMeasure*1.1) * measureWidth); 
					if(timeDuration == unitsInMeasure) {
						timeDuration = 0;
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						whichMeasure++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						this.unitsInMeasure = setUnitsInMeasure(lg, whichMeasure);
					}
				}
			}
		}
	}

	private int setUnitsInMeasure(GuitarInformation gi, int whichMeasure) {
//		/* GET AMOUNT OF NOTES IN MEASURE */
//		int unitsInMeasure = 0;
//		int j = 0;
//		
//		while(gi.getMeasure() == whichMeasure) {
//			j++;
//		}
//		aLGuitar.get(j)
//		for(int i = 0; i < j; i++) {
//			Note n = sp.getParts().get(0).getMeasures().get(whichMeasure).getNotesBeforeBackup().get(i);
//			if(n.getChord() == null && n.getDuration() != null) {
//				unitsInMeasure+= n.getDuration();
//			} else if(n.getChord() == null) {
//				unitsInMeasure+=findDuration(n.getType());
//			}
//		}
		return unitsInMeasure;
	}

	public int findDuration(String type) {
		switch (type){
		case "16th":
			return 16;
		case "8th":
			return 8;
		}
		return 8;
	}

	public void removeLineBehindNote(int noteX, int yLocation, boolean flag) {
		Rectangle r;
		if(flag) {
			r = new Rectangle(noteX, currentNoteYLocation + yLocation-15, 15, 15);
		} else {
			r = new Rectangle(noteX, currentNoteYLocation + yLocation-15, 10, 15);

		}
		r.setFill(Color.WHITE);
		r.opacityProperty().set(1);
		p.getChildren().add(r); //WHITE BACKGROUND
	}


	public LinkedList<GuitarInformation> getNoteObject(){return this.aLGuitar;}
	public int getMeasureWidth() {return this.measureWidth;}
	public int getUnitsInMeasure() {return this.unitsInMeasure;}
	public double getDivisionConstant() {return this.divisionConstant;}
}
