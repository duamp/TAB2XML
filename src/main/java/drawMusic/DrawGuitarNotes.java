package drawMusic;

import java.util.LinkedList;

import GUI.settings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import note_information.GuitarInformation;

public class DrawGuitarNotes {
	private LinkedList<GuitarInformation> aLGuitar;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 30;
	private Pane p;
	private int unitsInMeasure = 0;
	private int currentNoteYLocation = 0;
	private double divisionConstant = 1.1;
	private int currentNotesPrinted = 0;
	settings s = new settings();
	private String fontType = s.getFontType();
	private int noteSize = s.getNoteSize(); 
	
	public DrawGuitarNotes(Pane p, LinkedList<GuitarInformation> aL, settings s) {
		if(s != null) {
			this.s = s;
			this.noteSize = this.s.getNoteSize();
			this.fontType = this.s.getFontType();
		}
		this.p = p;
		this.aLGuitar = aL;
	}

	public void drawGuitarNotes() {
		int noteX = startingXSpace + 20;
		int measureNumber = 0;
		int timeDuration = 0;
		int whichMeasure = 0;
		for(int j = 0; j < aLGuitar.size(); j++) {
			GuitarInformation lg = (GuitarInformation) aLGuitar.get(j);

			if(j == 0) {
				this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
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
				printNote(lg, noteX, yLocation, note);

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
						this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
					}

				}

			} else { //IS CHORD
				/*
				 * Flag indicates fret >= 10 because Rectange to remove background space should be wider
				 */
				removeLineBehindNote(noteX, yLocation, flag);
				printNote(lg, noteX, yLocation, note);

				//RECORDS noteX && noteY in arraylist for later access
				lg.setNoteX(noteX); 	
				lg.setNoteY(currentNoteYLocation + yLocation);

				if((aLGuitar.size() - 2 > j+1) && !aLGuitar.get(j+1).isChord()) {
					noteX += ((double)lg.getDuration()/(unitsInMeasure*divisionConstant) * measureWidth); 
					if(timeDuration == unitsInMeasure) {
						timeDuration = 0;
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						whichMeasure++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
					}
				}
			}
		}
	}

	private int setUnitsInMeasure(int whichMeasure) {
		/* GET AMOUNT OF NOTES IN MEASURE */
		int unitsInMeasure = 0;
		int k = 0;
		int notesPerMeasure = 0;
		while(this.currentNotesPrinted + k < aLGuitar.size() && aLGuitar.get(this.currentNotesPrinted + k).getMeasure() == whichMeasure + 1) {
			notesPerMeasure++;
			k++;
		}

		for(int i = currentNotesPrinted; i < notesPerMeasure+currentNotesPrinted; i++) {
			GuitarInformation l = aLGuitar.get(i);
			if(!l.isChord() && l.getDuration() != 0 ) {
				unitsInMeasure+= l.getDuration();
			} else if(!l.isChord()) {
				unitsInMeasure+=findDuration(l.getType());
			}
		}

		return unitsInMeasure;
	}

	//TODO
	public int findDuration(String type) {
		switch (type){
		case "16th":
			return 4;
		case "8th":
			return 8;
		case "eighth":
			return 8;
		case "whole":
			return 96;
		case "half":
			return 32;
		case "quarter":
			return 16;
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

	public void printNote(GuitarInformation g, int noteX, int yLocation, String note) {
			Text t = new Text(noteX, currentNoteYLocation + yLocation, note);
			
			if(g.getGrace() != null) {
				t = new Text(noteX + 3, currentNoteYLocation + yLocation, note);				
				t.setFont(Font.font(fontType, FontWeight.NORMAL, FontPosture.REGULAR, this.noteSize));
			}
			
			p.getChildren().add(t); //TEXT
			currentNotesPrinted++;
			
	}

	public LinkedList<GuitarInformation> getNoteObject(){return this.aLGuitar;}
	public int getMeasureWidth() {return this.measureWidth;}
	public int getUnitsInMeasure() {return this.unitsInMeasure;}
	public double getDivisionConstant() {return this.divisionConstant;}
	public int getCurrentNotesPrinted() {return this.currentNotesPrinted;}
	
	
		
}
