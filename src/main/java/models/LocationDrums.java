package models;

import GUI.Draw;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class LocationDrums {
	private int octave;
	private String note;
	private int duration;
	private int yCoord;
	@FXML
	private Pane pane;

	public LocationDrums(String note, int duration, int octave) {
		this.note = note;
		this.duration = duration;
		this.octave = octave;
		this.yCoord = getYCoord();
	}

	public int getYCoord() {
		int octaveY = 0;
		Draw d = new Draw(1, null, "Guitar");
		switch(this.octave) {
		case 3:
			octaveY+= 0;
		case 4:
			octaveY+= 0;
		case 5:
			octaveY+= 0;
		case 6:
			octaveY+= 0;
		}

		switch(this.note) {
		case "C":
			return octaveY + d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 6 - 13;
		case "D":
			return octaveY + 157;
		case "E":
			return octaveY + 140;
		case "F":
			return octaveY + 0;
		case "G":
			return octaveY + 0;
		case "A":
			return octaveY + 0;
		case "B":
			return 0; 
		}
		return this.yCoord;
	}

	public int getOctave() {return this.octave;}
	public String getNote() {return this.note;}
	public int getDutation() {return this.duration;}


}
