package models;

import GUI.Draw;
import drawings.Measure;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class DrumInformation {
	private int octave;
	private String note;
	private int duration;
	private double yCoord;
	private String type;
	private boolean chord;

	@FXML
	private Pane pane;
	private double octaveDifference = 59.5;//17/2 because 2 notes fit on one line and (17/2)*7 because 7 different notes
	private double differenceBetweenNotes=8.5; // 17*6 lines / 12 possible notes on 6 lines
	public DrumInformation(String note, int duration, int octave, String type, Boolean c) {
		this.note = note;
		this.duration = duration;
		this.octave = octave;
		this.yCoord = getYCoord();
		this.type = type;
		this.chord = c;
	}

	public double getYCoord() {
		double octaveY = 0;
		Draw d = new Draw(1, null, "Drums");
		Measure m = new Measure(4, pane, 1);
		switch(this.octave) {//need to invert addition and subtraction
		case 3:
			octaveY+= this.octaveDifference;
		case 4:
			//			reference octave so = 0
		case 5:
			octaveY-= this.octaveDifference;
		case 6:
			octaveY-= this.octaveDifference*2;
		}

		switch(this.note) {
		case "C":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 5*m.getSpaceBetweenBarsHorizontal();
		case "D":
			return octaveY + m.getCurrentTopOfMeasureHeight() + 4.5*m.getSpaceBetweenBarsHorizontal();
		case "E":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 4*m.getSpaceBetweenBarsHorizontal();
		case "F":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 3.5*m.getSpaceBetweenBarsHorizontal();
		case "G":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 3*m.getSpaceBetweenBarsHorizontal();
		case "A":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 2.5*m.getSpaceBetweenBarsHorizontal();
		case "B":
			return octaveY +  m.getCurrentTopOfMeasureHeight() + 2*m.getSpaceBetweenBarsHorizontal();
		}
		return this.yCoord;
	}
	public int getDuration() {return this.duration;}
	public int getOctave() {return this.octave;}
	public String getNote() {return this.note;}
	public int getDutation() {return this.duration;}
	public boolean isChord() {return this.chord;} // implement the actual value of if it is multiple notes.

}