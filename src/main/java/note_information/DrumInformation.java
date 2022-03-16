package note_information;

import drawings.Measure;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class DrumInformation {
	private int octave;
	private String note;
	private Integer duration;
	private double yCoord;
	private String XorO;
	private boolean chord;
	private int noteX;
	private int noteY;
	private int measure;
	private String type;

	@FXML
	private Pane pane;
	private double octaveDifference = 59.5;//17/2 because 2 notes fit on one line and (17/2)*7 because 7 different notes
	private double differenceBetweenNotes = 8.5; // 17*6 lines / 12 possible notes on 6 lines
	public DrumInformation(String note, Integer duration, int octave, String XorO, Boolean c, int measure, String type) {
		this.note = note;
		this.duration = duration;
		this.octave = octave;
		this.yCoord = getYCoord();
		this.XorO = XorO;
		this.chord = c;
		this.measure = measure;
		this.type = type;
	}

	public double getYCoord() {
		double octaveY = 0;
		Measure m = new Measure(4, pane, 1);
		switch(this.octave) {//need to invert addition and subtraction
		case -1:
			return -1;
		case 3:
			octaveY+= this.octaveDifference;
			break;
		case 4:
			//	reference octave so = 0
			break;
		case 5:
			octaveY-= this.octaveDifference;
			break;
		case 6:
			octaveY-= this.octaveDifference*2;
			break;
		}

		switch(this.note) {
		case "C":
			return octaveY +  m.getSpaceBetweenBarsHorizontal()*5 + 4;
		case "D":
			return octaveY + 76 + 4;
		case "E":
			return octaveY + m.getSpaceBetweenBarsHorizontal()*4 + 4;
		case "F":
			return octaveY +  59.5 + 4;
		case "G":
			return octaveY +  m.getSpaceBetweenBarsHorizontal()*3 + 4;
		case "A":
			return octaveY +  42.5 + 4;
		case "B":
			return octaveY +  m.getSpaceBetweenBarsHorizontal()*2 + 4;
		}
		return this.yCoord;
	}
	public int getOctave() {return this.octave;}
	public String getNote() {return this.note;}
	public Integer getDuration() {return this.duration;}
	public boolean isChord() {return this.chord;} // implement the actual value of if it is multiple notes.
	public void setNoteX(int x) {this.noteX = x;}
	public void setNoteY(int y) {this.noteY = y;}
	public int getNoteX() {return this.noteX;}
	public int getNoteY() {return this.noteY;}
	public int getMeasure() {return this.measure;}
	public String getXorO() {return this.XorO;}
	public String getType() {return this.type;}

}