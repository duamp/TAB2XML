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
		int counter16 = 0;
		int counterQuarterEight = 0;
		for(int j = 0; j < aLGuitar.size(); j++) {
			GuitarInformation lg = aLGuitar.get(j);
			int y = getMeasureYLocation(lg.getMeasure());
			/* KRISHNA IMPLAMENTATION */
			if(j+1 < this.aLGuitar.size()) {
				GuitarInformation g = null;
				if(this.aLGuitar.get(j+1) != null) {g = this.aLGuitar.get(j+1);}
				if(lg.getMeasure() == g.getMeasure()) {
				if(lg.getType().equals("16th") && counter16 < 3 ) {
					Line b = new Line(lg.getNoteX() + 1, y, g.getNoteX() + 1, y);
					p.getChildren().add(b);
					counter16++;

				} else if((lg.getType().equals("quarter") || lg.getType().equals("eighth")) && counterQuarterEight < 1){
					Line b = new Line(lg.getNoteX() + 1, y, g.getNoteX() + 1, y);
					p.getChildren().add(b);
					counterQuarterEight++;
				} else {
					counterQuarterEight = 0;
					counter16 = 0;
				}
				}
			}

			Line a = new Line(lg.getNoteX() + 1, y, lg.getNoteX() + 1, y - 7);
			p.getChildren().add(a);
		}
	}

	private int getMeasureYLocation(int measureNumber) {
		Measure m = new Measure(0, p, 0);
		int measuresY = ((measureNumber-1) / 3)*200 + (m.getSpaceBetweenBarsHorizontal()*5) + 40;
		return measuresY;
	}
}
