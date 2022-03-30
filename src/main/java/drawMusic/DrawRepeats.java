package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import note_information.DrumInformation;
import note_information.GuitarInformation;

public class DrawRepeats {
	private LinkedList<GuitarInformation> aLGuitar;
	private LinkedList<DrumInformation> aLDrums;
	private Pane p;

	public DrawRepeats(LinkedList<GuitarInformation> aLGuitar, LinkedList<DrumInformation> aLDrums, Pane p) {
		this.aLDrums = aLDrums;
		this.aLGuitar = aLGuitar;
		this.p = p;
	}

	public void drawRepeatsGuitar(){
		int currentMeasure = 1;
		for(int i = 0; i < aLGuitar.size(); i++) {
			GuitarInformation pointer = aLGuitar.get(i);
			if(pointer.getMeasure() == currentMeasure && pointer.getRepeats() != 0) {
				Text t = new Text("x" + pointer.getRepeats() + "");
				p.getChildren().add(t);
				currentMeasure++;
			}
		}
	}
	
	public void drawRepeatsDrums(){
		int currentMeasure = 1;
		for(int i = 0; i < aLDrums.size(); i++) {
			DrumInformation pointer = aLDrums.get(i);
			if(pointer.getMeasure() == currentMeasure && pointer.getRepeats() != 0) {
				Text t = new Text("x" + pointer.getRepeats() + "");
				t.setY(currentMeasure);
				p.getChildren().add(t);
				currentMeasure++;
			}
		}
	}
}
