package drawings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.LocationDrums;
import models.LocationGuitar;

public class Measure {
	private Pane p;
	private int numberOfMeasures;
	private int startingYSpace;
	private final int startingXSpace = 10;
	private final int measureWidth = 300;
	private final int spaceBetweenBarsHorizontal = 17;
	private final int moveMeasureDownValue = 200;
	private final int measuresPerLine = 3;
	private int lines;
	private final int TABx = 15;
	private int currentMeasureCount = 0;
	private int currentTopOfMeasureHeight = 0;

	public Measure(int numberofLines, Pane p, int numberOfMeasures) {
		lines = numberofLines;
		this.p = p;
		this.numberOfMeasures = numberOfMeasures;
	}

	public void drawMeasure() {
		startingYSpace=spaceBetweenBarsHorizontal*lines-1; //GUITAR TAB HAS 6 LINES
		int measureXAdjusted = startingXSpace;
		//draw measure box
		drawTabs();
		drawMeasureNumber();
		for(int i = 0; i < this.numberOfMeasures; i++) {
			if(currentMeasureCount % measuresPerLine == 0 && currentMeasureCount != 0) {
				currentTopOfMeasureHeight += moveMeasureDownValue;
				measureXAdjusted = startingXSpace;
				
				/* DRAW T A B && MEASURE NUMBER BEGINNING OF EACH MEASURE */
				drawTabs();
				drawMeasureNumber();
			}

			/* DRAW MEASURE */
			Line l = new Line(measureXAdjusted, currentTopOfMeasureHeight, this.measureWidth + measureXAdjusted, currentTopOfMeasureHeight);	//TOP
			p.getChildren().add(l);
			l= new Line(measureXAdjusted, currentTopOfMeasureHeight, measureXAdjusted, this.startingYSpace + currentTopOfMeasureHeight);	//LEFT
			p.getChildren().add(l);
			l = new Line(measureXAdjusted + this.measureWidth, currentTopOfMeasureHeight, this.measureWidth+measureXAdjusted, this.startingYSpace + currentTopOfMeasureHeight);	//RIGHT
			p.getChildren().add(l);
			l = new Line(measureXAdjusted, this.startingYSpace + currentTopOfMeasureHeight, this.measureWidth+measureXAdjusted, this.startingYSpace + currentTopOfMeasureHeight);	//BOTTOM
			p.getChildren().add(l);



			//Lines in Rectangle
			for(int y = 1; y < lines; y++) {
				l = new Line(measureXAdjusted, spaceBetweenBarsHorizontal*y + currentTopOfMeasureHeight, this.measureWidth+measureXAdjusted, spaceBetweenBarsHorizontal*y + currentTopOfMeasureHeight);	
				p.getChildren().add(l);
			}

			measureXAdjusted += this.measureWidth;
			currentMeasureCount++;
		}
	}

	public int getCurrentMeasureCount() {
		return this.currentMeasureCount + 1;
	}

	public int getCurrentTopOfMeasureHeight() {
		return this.currentTopOfMeasureHeight;
	}

	public int getSpaceBetweenBarsHorizontal() {return spaceBetweenBarsHorizontal;}

	public void drawTabs() {
		//WRITE TAB
		Text T = new Text(TABx, spaceBetweenBarsHorizontal*2 + currentTopOfMeasureHeight, "T");
		Text A = new Text(TABx, spaceBetweenBarsHorizontal*3 + currentTopOfMeasureHeight, "A");
		Text B = new Text(TABx, spaceBetweenBarsHorizontal*4 + currentTopOfMeasureHeight, "B");


		/* BOLD T A B */
		T.setFont(new Font(20));
		A.setFont(new Font(20));
		B.setFont(new Font(20));

		/* add T A B to pane */
		p.getChildren().add(T); 
		p.getChildren().add(A); 
		p.getChildren().add(B); 
	}

	public void drawMeasureNumber() {	
		Text number = new Text(TABx - 5, spaceBetweenBarsHorizontal*2 + currentTopOfMeasureHeight - 60, "" + getCurrentMeasureCount() + "");
		number.setFont(new Font(14));
		p.getChildren().add(number); 

	}


}
