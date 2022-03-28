package drawings;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import note_information.DrumInformation;


public class DrawDrumsNotes {

	private LinkedList<DrumInformation> aLDrums;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 15;
	private int currentNoteYLocation = 0;
	private int unitsInMeasure = 0;
	private Pane p;
	private 	int currentNotesPrinted = 0;
	private double divisionConstant = 1.1;


	public DrawDrumsNotes(Pane pane, LinkedList<DrumInformation> aLDrums) {
		this.aLDrums = aLDrums;
		this.p = pane;
	}


	public void drawDrumNotes() {
		int noteX = 35;
		int measureNumber = 0;
		int timeDuration = 0;
		int whichMeasure = 0;
		boolean flagMeasureChange = true;
		int durationcounter = 0;
		int xStart = 0;
		int loc = 0;
		for(int j = 0; j < aLDrums.size(); j++) {
			DrumInformation ld = (DrumInformation) this.aLDrums.get(j);

			if(flagMeasureChange) {
				this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
				flagMeasureChange = !flagMeasureChange;
			}

			double yLocation = ld.getYCoord();
			String note;
			if(ld.getXorO() != null) {
				note= "x";
			}else {
				note="⚫";
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
					noteX += (((double)ld.getDuration()/(unitsInMeasure*divisionConstant)) * this.measureWidth); 

					/* KEEPS TRACK OF CURRENT MEASURE  */
					if(timeDuration == unitsInMeasure) {
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
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
					noteX += ((double)ld.getDuration()/(unitsInMeasure*divisionConstant) * measureWidth); 
					if(timeDuration == unitsInMeasure) {
						timeDuration = 0;
						/*  PLACES NOTE AT BEGINNING OF NEW MEASURE  */
						measureNumber++;
						noteX = measureNumber*measureWidth + this.startingXSpace + 5; 
						whichMeasure++;
						this.unitsInMeasure = setUnitsInMeasure(whichMeasure);
						flagMeasureChange=!flagMeasureChange;
					}
				}
			}
			if(j+1 < this.aLDrums.size() && ld.isChord() && this.aLDrums.get(j+1).isChord()) {
				if(durationcounter+ld.getDuration()>=12) {
					durationcounter = 0;
					Line a = new Line(xStart, currentNoteYLocation - 40, loc+7,currentNoteYLocation-40);
					p.getChildren().add(a);
				}else if(durationcounter == 0) {
					xStart = loc+7;
				}
				durationcounter += ld.getDuration();
				
			}else if(ld.isChord()){
				
			}else {				
				if(durationcounter+ld.getDuration()>=12) {
					durationcounter = 0;
					Line a = new Line(xStart, currentNoteYLocation - 40, loc+7,currentNoteYLocation-40);
					p.getChildren().add(a);
				}else if(durationcounter == 0) {
					xStart = loc+7;
				}
				durationcounter += ld.getDuration();
			}
			
//			ld.getDuration()
//			ld.getType()

		}
	}

	private int setUnitsInMeasure(int whichMeasure) {
		/* GET AMOUNT OF NOTES IN MEASURE */
		int unitsInMeasure = 0;
		int k = 0;
		int notesPerMeasure = 0;
		while(this.currentNotesPrinted + k < aLDrums.size() && aLDrums.get(this.currentNotesPrinted + k).getMeasure() == whichMeasure + 1) {
			notesPerMeasure++;
			k++;
		}

		for(int i = currentNotesPrinted; i < notesPerMeasure+currentNotesPrinted; i++) {
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
			return 16;
		case "8th":
			return 8;
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