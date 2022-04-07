package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.ScorePartwise;
import note_information.DrumInformation;
import note_information.GuitarInformation;

public class DrawTiming {
	private LinkedList<GuitarInformation> aLGuitar;
	private LinkedList<DrumInformation> aLDrums;
	private Pane p;
	private LinkedList<Timing> times;
	private ScorePartwise sp;
	private int fontsize; 
	private static int startingXSpace = 30;

	public DrawTiming(LinkedList<GuitarInformation> aLGuitar, LinkedList<DrumInformation> aLDrums, Pane p, ScorePartwise sp) {
		this.aLDrums = aLDrums;
		this.aLGuitar = aLGuitar;
		this.times = new LinkedList<Timing>();
		this.p = p;
		this.sp = sp; 
		getTiming();
	}

	private void getTiming() {
		aLDrums = new LinkedList<>();
		int beats = 0;
		int beatType = 0;
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime() != null) {
				beats = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeats();
				beatType = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeatType();
			}
		
			this.times.add(new Timing(i, beats, beatType));
		}
	}

	public void drawTiming(){
		Timing current = new Timing(1,0,0);
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(current.getBeat() != times.get(i).getBeat()) { // draw new time scale on corresponding measure.
				Text d14;
				Text d24;
				if((i)%3 == 0) {
					d14 = new Text(getMeasureXLocation(i+1) + startingXSpace, getMeasureYLocation(i+1) + 37, "" + times.get(i).getBeat());
					d24 = new Text(getMeasureXLocation(i+1) + startingXSpace, getMeasureYLocation(i+1) + 59, "" + times.get(i).getBeatType());
				} else {
					d14 = new Text(getMeasureXLocation(i+1) + startingXSpace - 15, getMeasureYLocation(i+1) + 37, "" + times.get(i).getBeat());
					d24 = new Text(getMeasureXLocation(i+1) + startingXSpace - 15, getMeasureYLocation(i+1) + 59, "" + times.get(i).getBeatType());
				}
				d14.setFont(new Font(25 - fontsize));
				d24.setFont(new Font(25 - fontsize));
				p.getChildren().add(d14); 
				p.getChildren().add(d24);
			}
			current = times.get(i); //change current
		}
	}

	public int getMeasureNumber() {
		return sp.getParts().get(0).getMeasures().size();
	}

	private int getMeasureXLocation(int measureNumber) {
		Measure m = new Measure(0, p, 0);
		int measuresX = ((measureNumber-1) % 3) * m.getMeasureWidth();
		return measuresX;
	}

	private int getMeasureYLocation(int measureNumber) {
		Measure m = new Measure(0, p, 0);
		int measuresY = ((measureNumber-1) / 3) * m.getMoveMeasureDownValue();
		return measuresY;
	}
}
