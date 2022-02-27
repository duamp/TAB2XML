package models;

import GUI.Draw;

public class LocationDrums extends Location{
	private String id;
	private int duration;
	private int yCoord;

	
	public LocationDrums(String id, int duration) {
		super();
		this.id = id;
		this.duration = duration;
		this.yCoord = getYCoord();
	}
	
	public int getYCoord() {
		return 0;
	}

}
