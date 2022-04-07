package drawMusic;

import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import note_information.GuitarInformation;

public class DrawBars {
	private LinkedList<GuitarInformation> aLGuitar;
	private Pane p;

	public DrawBars(LinkedList<GuitarInformation> aLGuitar, Pane p) {
		this.aLGuitar = aLGuitar;
		this.p = p;
	}

	public void drawbars() {
		for(int j = 0; j < aLGuitar.size(); j++) {
			GuitarInformation lg = aLGuitar.get(j);
			int y = getMeasureYLocation(lg.getMeasure());
			/* KRISHNA IMPLAMENTATION */
			if(j+1 < this.aLGuitar.size()) {
				GuitarInformation g = null;
				if(this.aLGuitar.get(j+1) != null) {g = this.aLGuitar.get(j+1);}
				if(lg.getType().equals("16th") && lg.getMeasure() == g.getMeasure()) {
					Line b = new Line(lg.getNoteX(), y, g.getNoteX(), y);
					p.getChildren().add(b);
				}
			}

			Line a = new Line(lg.getNoteX(), y, lg.getNoteX(), y - 7);
			p.getChildren().add(a);
		}
	}
	
	private int getMeasureYLocation(int measureNumber) {
		Measure m = new Measure(0, p, 0);
		int measuresY = ((measureNumber-1) / 3)*200 + (m.getSpaceBetweenBarsHorizontal()*5) + 40;
		return measuresY;
	}
}
