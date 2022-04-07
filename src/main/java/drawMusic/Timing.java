package drawMusic;

import models.ScorePartwise;

public class Timing {
	private int measure;
	private int beat;
	private int beatType;
	
	public Timing(int measure, int beat, int beatType) {
		this.measure = measure;
		this.beat = beat;
		this.beatType = beatType;
	}
	
	public int getMeasure() {return this.getMeasure();}
	public int getBeat() {return this.beat;}
	public int getBeatType() {return this.beatType;}

}
