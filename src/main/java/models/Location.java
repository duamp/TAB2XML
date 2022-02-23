package models;

import models.measure.note.Chord;

public class Location {
	private int string;
	private int fret;
	private int duration;
	private int location;
	private boolean chord;
	
	public Location(int string, int fret, int duration, boolean c) {
		this.string = string;
		this.fret = fret;
		this.duration = duration;
		this.location = getYLocation();
		this.chord = c;
	}
	
	public int getYLocation() {
		switch(this.string) {
		  case 6:
			  return getYLocationHelper(170);
		  case 5:
			  return getYLocationHelper(153);
		  case 4:
			  return getYLocationHelper(119);
		  case 3:
			  return  getYLocationHelper(102);
		  case 2:
			  return getYLocationHelper(85);
		  case 1:
			  return getYLocationHelper(51);
		}
		return -1;
	}
	
	public int getYLocationHelper(int startingY) {
		int y = startingY;
		for(int i = 0; i < this.fret; i++) {
			y += 17/2;
		}
		return y;
	}
	
	public int getDuration() {return this.duration;}
	public boolean isChord() {return this.chord;}
	
}
