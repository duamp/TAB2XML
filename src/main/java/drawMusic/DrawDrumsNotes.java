package drawMusic;

import java.util.LinkedList;

import GUI.settings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import note_information.DrumInformation;


public class DrawDrumsNotes {

	private LinkedList<DrumInformation> aLDrums;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 30;
	private int currentNoteYLocation = 0;
	private int unitsInMeasure = 0;
	private Pane p;
	private int currentNotesPrinted = 0;
	private double divisionConstant = 1.2;
	settings s = new settings();
	private String fontType = s.getFontType();
	private int noteSize = s.getNoteSize(); 
	private int measuresWithRests = 0;
	private int notesStartingInMeasurePosition = 20;

	public DrawDrumsNotes(Pane pane, LinkedList<DrumInformation> aLDrums, settings s) {
		if(s != null) {
			this.s = s;
			this.noteSize = this.s.getNoteSize();
			this.fontType = this.s.getFontType();
		}
		this.aLDrums = aLDrums;
		this.p = pane;
	}

	public void drawDrumNotes() {
		int noteX = startingXSpace + 25;
		int measureNumber = 0;
		int timeDuration = 0;
		int whichMeasure = 0;
		boolean flagMeasureChange = true;
		int durationcounter = 0;
		int xStart = 0;
		int loc = 0;
		boolean drawing16 = false;
		int draw16start = 0;
		boolean cutoff = false;
		int mn = 0;
		for(int j = 0; j < aLDrums.size(); j++) {
			DrumInformation ld = (DrumInformation) this.aLDrums.get(j);
			if(ld.getNote() == null) {
				timeDuration = 0;
				/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
				measureNumber++;
				mn++;
				noteX = measureNumber*measureWidth + this.startingXSpace + notesStartingInMeasurePosition; 
				whichMeasure++;
				this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
				flagMeasureChange = true;
				measuresWithRests++;				
				
				if(measureNumber != 0 && measureNumber % 3 == 0) { 
					currentNoteYLocation += moveMeasureDownValue;
					measureNumber = 0;
					noteX = this.startingXSpace + 20;
				}
				continue;
			}
			if(flagMeasureChange) {
				this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
				flagMeasureChange = !flagMeasureChange;
			}

			double yLocation = ld.getYCoord();
			String note;

			if(ld.getXorO() == null) {
				note= "⚫";
			} else if(ld.getXorO().equals("(normal)")) {
				note = "(⚫)";
			} else {
				note= "x";
			}
			if(mn == 10) {
				System.out.println("stop");
			}
			if(!ld.isChord()) {
				timeDuration += ld.getDuration();
				//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
				if(measureNumber != 0 && measureNumber % 3 == 0) { 
					currentNoteYLocation += moveMeasureDownValue;
					measureNumber = 0;
					noteX = this.startingXSpace + 20;
				}
				removeLineBehindNote(noteX, yLocation);
				Text t = new Text(noteX, currentNoteYLocation + yLocation, note);// implement notes to actually draw here
				Line l = new Line(noteX+7, currentNoteYLocation + yLocation-5, noteX+7,currentNoteYLocation-40);
				loc = noteX;
				p.getChildren().add(t); //TEXT
				p.getChildren().add(l); //TEXT note's line
				currentNotesPrinted++;

				//RECORDS noteX && noteY in arraylist for later access
				ld.setNoteX(noteX); 	
				ld.setNoteY((int)(currentNoteYLocation + yLocation));

				if(j < aLDrums.size() - 2 && !aLDrums.get(j+1).isChord()) {
					if(unitsInMeasure != 0) {
						noteX += (((double)ld.getDuration()/(unitsInMeasure*divisionConstant)) * this.measureWidth); 
					}

					/* KEEPS TRACK OF CURRENT MEASURE  */
					if(timeDuration >= unitsInMeasure) {
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						mn++;
						noteX = measureNumber*measureWidth + this.startingXSpace + notesStartingInMeasurePosition; 
						timeDuration = 0;
						whichMeasure++;
						this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
						flagMeasureChange=!flagMeasureChange;
					}
				}
			} else {
				removeLineBehindNote(noteX, yLocation);
				Text t = new Text(noteX, currentNoteYLocation + yLocation, note);// implement notes to actually draw here
				Line l = new Line(noteX+7, currentNoteYLocation + yLocation-5, noteX+7,currentNoteYLocation-40);
				loc = noteX;
				p.getChildren().add(t);
				p.getChildren().add(l);
				currentNotesPrinted++;

				//RECORDS noteX && noteY in arraylist for later access
				ld.setNoteX(noteX); 	
				ld.setNoteY((int)(currentNoteYLocation + yLocation));

				if((aLDrums.size() - 2 > j+1) && !aLDrums.get(j+1).isChord()) {
					if(unitsInMeasure != 0) {
						noteX += ((double)ld.getDuration()/(unitsInMeasure*divisionConstant) * measureWidth); 
					}
					if(timeDuration >= unitsInMeasure) {
						timeDuration = 0;
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						mn++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						whichMeasure++;
						this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
						flagMeasureChange=!flagMeasureChange;
					}
				}
			}
			if(j+1 < this.aLDrums.size() && ld.isChord() && this.aLDrums.get(j+1).isChord()) {
				if(durationcounter+this.getDuration(ld)>=16) {
					durationcounter = 0;
					Line a = new Line(xStart, currentNoteYLocation - 40, loc+7,currentNoteYLocation-40);
					p.getChildren().add(a);
					cutoff = true;
				}else if(durationcounter == 0) {
					xStart = loc+7;
					durationcounter += this.getDuration(ld);
				}else {
					durationcounter += this.getDuration(ld);
				}
			}else if(ld.isChord()){

			}else {				
				if(durationcounter+this.getDuration(ld)>=16) {
					durationcounter = 0;
					Line a = new Line(xStart, currentNoteYLocation - 40, loc+7,currentNoteYLocation-40);
					p.getChildren().add(a);
					cutoff = true;
				}else if(durationcounter == 0) {
					xStart = loc+7;
					durationcounter += this.getDuration(ld);
				}else {
					durationcounter += this.getDuration(ld);						
				}
			}
			if(this.getDuration(ld) == 4 && drawing16 == false) {
				drawing16 = true;
				draw16start = loc+7;
			}else if(drawing16) {
				if(this.aLDrums.get(j+1).getDuration() != 4 || cutoff) {
					Line a = new Line(draw16start, currentNoteYLocation - 35, loc+7,currentNoteYLocation-35);
					p.getChildren().add(a);
					drawing16 = false;
					cutoff = false;
				}
			}			
		}
	}
	private int getDuration(DrumInformation note) {
		int duration = 0;
		if(note!= null) {
			switch(note.getType()) {
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
		}

		return duration;
	}
	private void drawDrumLines() {

	}

	private int setUnitsInMeasure(int whichMeasure) {
		/* GET AMOUNT OF NOTES IN MEASURE */
		int unitsInMeasure = 0;
		int k = 0;
		int notesPerMeasure = 0;
		while(this.currentNotesPrinted + k + measuresWithRests < aLDrums.size() && aLDrums.get(this.currentNotesPrinted + k + measuresWithRests).getMeasure() == whichMeasure + 1) {
			notesPerMeasure++;
			k++;
		}

		for(int i = currentNotesPrinted + measuresWithRests; i < notesPerMeasure+currentNotesPrinted+measuresWithRests; i++) {
			DrumInformation l = aLDrums.get(i);
			if(!l.isChord() && l.getDuration() != null ) {
				unitsInMeasure+= l.getDuration();
			} else if(!l.isChord()) {
				unitsInMeasure+=findDuration(l.getXorO());
			}
		}

		return unitsInMeasure;
	}

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

	public void removeLineBehindNote(int noteX, double yLocation) {
		Rectangle r;
		r = new Rectangle(noteX, currentNoteYLocation + yLocation - 5, 8, 4);
		r.setFill(Color.WHITE);
		r.opacityProperty().set(1);
		p.getChildren().add(r); //WHITE BACKGROUND
	}

	public LinkedList<DrumInformation> getNoteObject(){return this.aLDrums;}
	public int getMeasureWidth() {return this.measureWidth;}
	public int getUnitsInMeasure() {return this.unitsInMeasure;}
	public double getDivisionConstant() {return this.divisionConstant;}
	public int getCurrentNotesPrinted() {return this.currentNotesPrinted;}
}