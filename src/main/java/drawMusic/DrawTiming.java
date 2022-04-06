package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import models.ScorePartwise;
import note_information.DrumInformation;
import note_information.GuitarInformation;

public class DrawTiming {
	private LinkedList<GuitarInformation> aLGuitar;
	private LinkedList<DrumInformation> aLDrums;
	private Pane p;
	private ScorePartwise sp;

	public DrawTiming(LinkedList<GuitarInformation> aLGuitar, LinkedList<DrumInformation> aLDrums, Pane p, ScorePartwise sp) {
		this.aLDrums = aLDrums;
		this.aLGuitar = aLGuitar;
		this.p = p;
		this.sp = sp; 
		if(sp.getPartList().getScoreParts().get(0).getPartName() == "Drumset") {
			getTimingDrums();
		} else {
			getTimingGuitar();
		}
	}

	private void getTimingGuitar() {
		aLGuitar = new LinkedList<>();
		int beats = 0;
		int beatType = 0;
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime() != null) {
				beats = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeats();
				beatType = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeatType();
			}
			
			System.out.println("Beats: " + beats);
			System.out.println("Beat Type: " + beatType);
		}
	}


	private void getTimingDrums() {
		aLDrums = new LinkedList<>();
		int beats = 0;
		int beatType = 0;
		for(int i = 0; i < this.getMeasureNumber(); i++) {
			if(sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime() != null) {
				beats = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeats();
				beatType = sp.getParts().get(0).getMeasures().get(i).getAttributes().getTime().getBeatType();
			}
			
			System.out.println("Beats: " + beats);
			System.out.println("Beat Type: " + beatType);
		}
	}



	public void drawTimingGuitar(){
		Boolean flag = true;
		for(int i = 0; i < aLGuitar.size(); i++) {
			GuitarInformation pointer = aLGuitar.get(i);
			GuitarInformation pointerNext = aLGuitar.get(i);
			if(i + 1 < aLGuitar.size()) {
				pointerNext = aLGuitar.get(i+1);
			}
			if(pointer.getRepeats() != 0) {
				if(flag) { // CREATE BEGINNING 'REPEAT' CIRCLES
					Line l = new Line(getMeasureXLocation(pointer.getMeasure()) + 35, getMeasureYLocation(pointer.getMeasure()) + 28, getMeasureXLocation(pointer.getMeasure()) + 35, getMeasureYLocation(pointer.getMeasure()) + 43); //x1 y1 x2 y2
					Circle ctop = new Circle(getMeasureXLocation(pointer.getMeasure()) + 41, getMeasureYLocation(pointer.getMeasure()) + 28, 3);
					Circle cbottom = new Circle(getMeasureXLocation(pointer.getMeasure()) + 41, getMeasureYLocation(pointer.getMeasure()) + 43, 3);
					p.getChildren().add(ctop);
					p.getChildren().add(cbottom);
					p.getChildren().add(l);
					flag = false;
				}

				if(pointerNext.getRepeats() == 0) {
					Text t = new Text(getMeasureXLocation(pointerNext.getMeasure()), getMeasureYLocation(pointer.getMeasure()) - 10, "x" + pointer.getRepeats() + "");
					p.getChildren().add(t);
					// CREATE ENDING 'REPEAT' CIRCLES
					Line l = new Line(getMeasureXLocation(pointerNext.getMeasure()) + 5, getMeasureYLocation(pointer.getMeasure()) + 28, getMeasureXLocation(pointerNext.getMeasure()) + 5, getMeasureYLocation(pointer.getMeasure()) + 43); //x1 y1 x2 y2
					Circle ctop = new Circle(getMeasureXLocation(pointerNext.getMeasure() ), getMeasureYLocation(pointerNext.getMeasure()) + 28, 3);
					Circle cbottom = new Circle(getMeasureXLocation(pointerNext.getMeasure()), getMeasureYLocation(pointerNext.getMeasure()) + 43, 3);
					p.getChildren().add(ctop);
					p.getChildren().add(cbottom);
					p.getChildren().add(l);
					flag = true;
				}
			}
		}
	}

	public void drawTimingDrums(){
		Boolean flag = true;
		for(int i = 0; i < aLDrums.size(); i++) {
			DrumInformation pointer = aLDrums.get(i);
			DrumInformation pointerNext = aLDrums.get(i);
			if(i + 1 < aLDrums.size()) {
				pointerNext = aLDrums.get(i+1);
			}
			if(pointer.getRepeats() != 0) {
				if(flag) { // CREATE BEGINNING 'REPEAT' CIRCLES
					Line l = new Line(getMeasureXLocation(pointer.getMeasure()) + 35, getMeasureYLocation(pointer.getMeasure()) + 28, getMeasureXLocation(pointer.getMeasure()) + 35, getMeasureYLocation(pointer.getMeasure()) + 43); //x1 y1 x2 y2
					Circle ctop = new Circle(getMeasureXLocation(pointer.getMeasure()) + 41, getMeasureYLocation(pointer.getMeasure()) + 28, 3);
					Circle cbottom = new Circle(getMeasureXLocation(pointer.getMeasure()) + 41, getMeasureYLocation(pointer.getMeasure()) + 43, 3);
					p.getChildren().add(ctop);
					p.getChildren().add(cbottom);
					p.getChildren().add(l);
					flag = false;
				}

				if(pointerNext.getRepeats() == 0) {
					Text t = new Text(getMeasureXLocation(pointerNext.getMeasure()), getMeasureYLocation(pointer.getMeasure()) - 10, "x" + pointer.getRepeats() + "");
					p.getChildren().add(t);
					// CREATE ENDING 'REPEAT' CIRCLES
					Line l = new Line(getMeasureXLocation(pointerNext.getMeasure()) + 5, getMeasureYLocation(pointer.getMeasure()) + 28, getMeasureXLocation(pointerNext.getMeasure()) + 5, getMeasureYLocation(pointer.getMeasure()) + 43); //x1 y1 x2 y2
					Circle ctop = new Circle(getMeasureXLocation(pointerNext.getMeasure() ), getMeasureYLocation(pointerNext.getMeasure()) + 28, 3);
					Circle cbottom = new Circle(getMeasureXLocation(pointerNext.getMeasure()), getMeasureYLocation(pointerNext.getMeasure()) + 43, 3);
					p.getChildren().add(ctop);
					p.getChildren().add(cbottom);
					p.getChildren().add(l);
					flag = true;
				}
			}
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
