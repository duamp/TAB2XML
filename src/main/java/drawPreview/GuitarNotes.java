package drawPreview;

import java.awt.FontMetrics;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.LocationGuitar;

public class GuitarNotes {
	private LinkedList<LocationGuitar> aLGuitar;
	private final int measureWidth = 300;
	private final int moveMeasureDownValue = 200;
	private final int startingXSpace = 10;
	private Pane p;

	public GuitarNotes(Pane p, LinkedList<LocationGuitar> aL) {
		this.p=p;
		this.aLGuitar = aL;
		}

	public void drawGuitarNotes(int noteYLocation) {
		int noteX = 0;
		int measureNumber = 1;
		for(int j = 0; j < aLGuitar.size(); j++) {
			LocationGuitar lg = (LocationGuitar) aLGuitar.get(j);
			String note = "" + lg.getFret() + "";
//			FontMetrics fm = g.getFontMetrics();
//			Rectangle2D rect = fm.getStringBounds(note, g);
//			g.setColor(bgColor);
//			
			
			if(!lg.isChord()) { //NOT CHORD
				noteX += ((double)lg.getDuration()/64 * measureWidth); 
				//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
				if(measureNumber % 5 == 0) { 
					noteYLocation += moveMeasureDownValue;
					measureNumber = 1;
					noteX = this.startingXSpace + 10;
				}
				
				if(noteX > this.measureWidth * measureNumber + this.startingXSpace) {
					noteX = this.measureWidth*measureNumber + this.startingXSpace + 10;
					measureNumber++;
				}

				int yLocation = lg.getYCoord();
				//REMOVE LINE BEHIND NOTE
//				g.fillRect(noteX, noteYLocation + yLocation - fm.getAscent(), (int) rect.getWidth(),(int) rect.getHeight());
//				//SET BACKGROUND COLOR TO WHITE i.e., appears as if there was no line to begin with.
//				g.setColor(textColor);
//
//				g.drawString(note, noteX, noteYLocation + yLocation);
				Rectangle r = new Rectangle(noteX, noteYLocation + yLocation-15, 10, 15);
				r.setFill(Color.WHITE);
				r.opacityProperty().set(1);
				Text t = new Text(noteX, noteYLocation + yLocation, note);
				p.getChildren().add(r); //WHITE BACKGROUND
				p.getChildren().add(t); //TEXT

			} else { //IS CHORD
				int yLocation = lg.getYCoord();
				//REMOVE LINE BEHIND NOTE
//				g.fillRect(noteX, noteYLocation + yLocation - fm.getAscent(), (int) rect.getWidth(),(int) rect.getHeight());
//				//SET BACKGROUND COLOR TO WHITE i.e., appears as if there was no line to begin with.
//				g.setColor(textColor);
//				g.drawString("" + lg.getFret() + "", noteX, noteYLocation + lg.getYCoord());
//				g.drawString(note, noteX, noteYLocation + yLocation);
				
				Text t = new Text(noteX, noteYLocation + yLocation, note);
				p.getChildren().add(t);
				
				if((aLGuitar.size() - 2 > j+1) && !aLGuitar.get(j+1).isChord()) {
					noteX += ((double)lg.getDuration()/64 * measureWidth); 
				}
			}
		}
	}
}
