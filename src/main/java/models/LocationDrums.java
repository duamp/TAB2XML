package models;

import GUI.Draw;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class LocationDrums {
	private int octave;
	private String note;
	private int duration;
	private double yCoord;
	private String type;
	
	@FXML
	private Pane pane;
	private double octaveDifference = 59.5;//17/2 because 2 notes fit on one line and (17/2)*7 because 7 different notes
	private double differenceBetweenNotes=8.5; // 17*6 lines / 12 possible notes on 6 lines
	public LocationDrums(Pane pane, String note, int duration, int octave, String type) {
		this.note = note;
		this.duration = duration;
		this.octave = octave;
		this.yCoord = getYCoord();
		this.pane = pane;
		this.type = type;
	}

	public double getYCoord() {
		double octaveY = 0;
		Draw d = new Draw(1, null, "Drums");
		switch(this.octave) {//need to invert addition and subraction
		case 3:
			octaveY-= this.octaveDifference;
		case 4:
//			reference octave so = 0
		case 5:
			octaveY+= this.octaveDifference;
		case 6:
			octaveY+= this.octaveDifference*2;
		}

		switch(this.note) {
		case "C":
			return octaveY +  this.differenceBetweenNotes * 7 + 17;
		case "D":
			return octaveY +  this.differenceBetweenNotes * 6 + 17;
		case "E":
			return octaveY +  this.differenceBetweenNotes * 5 + 17;
		case "F":
			return octaveY +  this.differenceBetweenNotes * 4 + 17;
		case "G":
			return octaveY +  this.differenceBetweenNotes * 3 + 17;
		case "A":
			return octaveY +  this.differenceBetweenNotes * 2 + 17;
		case "B":
			return octaveY +  this.differenceBetweenNotes + 17; 
		}
		return this.yCoord;
	}
	public int getDuration() {return this.duration;}
	public int getOctave() {return this.octave;}
	public String getNote() {return this.note;}
	public int getDutation() {return this.duration;}
	public boolean isMulti() {return false;} // implement the actual value of if it is multiple notes.

}
