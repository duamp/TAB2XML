package note_information;

import java.util.List;

import drawMusic.Measure;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import models.measure.note.Grace;
import models.measure.note.notations.Slide;
import models.measure.note.notations.Slur;

public class GuitarInformation {
	private int string;
	private int fret;
	private int duration;
	private int location;
	private boolean chord;
	private List<Slur> slur;
	private int noteX;
	private int noteY;
	private int measure;
	private String type;
	private Grace grace; 
	private List<Slide> slides;
	@FXML
	private Pane pane;

	public GuitarInformation(int string, int fret, int duration, boolean c, List<Slur> slur, int measure, String type, Grace g, List<Slide> s)  {
		this.string = string;
		this.fret = fret;
		this.duration = duration;
		this.location = getYCoord();
		this.chord = c;
		this.slur = slur;
		this.measure = measure;
		this.type = type;
		this.grace = g;
		this.slides = s;
	}

	public int getYCoord() {
		Measure m = new Measure(1, pane, 5);
		switch(this.string) {
		case 6:
			return m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 6 - 13;
		case 5:
			return (m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 5 - 13);
		case 4:
			return (m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 4 - 13);
		case 3:
			return (m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 3 - 13);
		case 2:
			return (m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 2 - 13);
		case 1:
			return (m.getCurrentTopOfMeasureHeight() + m.getSpaceBetweenBarsHorizontal() * 1 - 13);
		}
		return -1;
	}

	public int getDuration() {return this.duration;}
	public boolean isChord() {return this.chord;}
	public int getString() {return this.string;}
	public int getFret() {return this.fret;}
	public boolean isSlur() {return this.slur != null;}
	public void setNoteX(int x) {this.noteX = x;}
	public void setNoteY(int y) {this.noteY = y;}
	public int getNoteX() {return this.noteX;}
	public int getNoteY() {return this.noteY;}
	public List<Slur> getSlur() {return this.slur;}
	public int getMeasure() {return this.measure;}
	public String getType() {return this.type;}
	public Grace getGrace() {return this.grace;}
	public List<Slide> getSlide() {return this.slides;}
	public boolean isSlide() {return this.slides != null;}



}
