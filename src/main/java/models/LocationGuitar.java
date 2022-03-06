package models;

import GUI.Draw;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class LocationGuitar {
	private int string;
	private int fret;
	private int duration;
	private int location;
	private boolean chord;
	@FXML
	private Pane pane;
	
	public LocationGuitar(int string, int fret, int duration, boolean c)  {
		this.string = string;
		this.fret = fret;
		this.duration = duration;
		this.location = getYCoord();
		this.chord = c;
	}
	
	public int getYCoord() {
		Draw d = new Draw(1, null, "Guitar");
		switch(this.string) {
		  case 6:
			  return d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 6 - 13;
		  case 5:
			  return (d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 5 - 13);
		  case 4:
			  return (d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 4 - 13);
		  case 3:
			  return (d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 3 - 13);
		  case 2:
			  return (d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 2 - 13);
		  case 1:
			  return (d.getStartingYSpace() + d.getSpaceBetweenBarsHorizontal() * 1 - 13);
		}
		return -1;
	}
	
	public int getDuration() {return this.duration;}
	public boolean isChord() {return this.chord;}
	public int getString() {return this.string;}
	public int getFret() {return this.fret;}
}
