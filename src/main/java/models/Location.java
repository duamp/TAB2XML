package models;

import java.util.LinkedList;

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
		int[] exceptions;
		switch(this.string) {
		  case 6:
			  exceptions = new int[]{2, 4, 6, 9, 11};
			  return getYLocationHelper(187, exceptions);
		  case 5:
			  exceptions = new int[]{1, 4, 6, 9, 11};
			  return getYLocationHelper(160, exceptions);
		  case 4:
			  exceptions = new int[]{1, 4, 6, 8, 11};
			  return getYLocationHelper(142, exceptions);
		  case 3:
			  exceptions = new int[]{1, 3, 6, 8, 11};
			  return  getYLocationHelper(119, exceptions);
		  case 2:
			  exceptions = new int[]{2, 4, 7, 9, 11};
			  return getYLocationHelper(102, exceptions);
		  case 1:
			  exceptions = new int[]{2, 4, 6, 9, 11};
			  return getYLocationHelper(72, exceptions);
		}
		return -1;
	}
	
	public int getYLocationHelper(int startingY, int[] exceptions) {
		int y = startingY;
		int count = 0;
		for(int i = 0; i < this.fret; i++) {
			if(exceptions[count] == i) {count++;} 
			else {y -= 9;}
		}
		return y;
	}
	
	public int getDuration() {return this.duration;}
	public boolean isChord() {return this.chord;}
	
}
