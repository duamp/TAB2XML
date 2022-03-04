package drawPreview;

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

	public Measure(int numberofLines, Pane p, int numberOfMeasures) {
		lines = numberofLines;
		this.p = p;
		this.numberOfMeasures = numberOfMeasures;
	}

	public void drawMeasure() {
		int currentMeasureCount = 0;
		startingYSpace=spaceBetweenBarsHorizontal*lines-1; //GUITAR TAB HAS 6 LINES

		int measureHeightAdjusted = startingYSpace;
		int measureXAdjusted = startingXSpace;
		//draw measure box
		for(int i = 0; i < this.numberOfMeasures; i++) {
			if(currentMeasureCount % measuresPerLine == 0 && currentMeasureCount != 0) {
				measureHeightAdjusted += moveMeasureDownValue;
				measureXAdjusted = startingXSpace;
			}

			/* DRAW MEASURE */
			Line l = new Line(measureXAdjusted, measureHeightAdjusted, this.measureWidth + measureXAdjusted, measureHeightAdjusted);	//TOP
			p.getChildren().add(l);
			l= new Line(measureXAdjusted, measureHeightAdjusted, measureXAdjusted, this.startingYSpace + measureHeightAdjusted);	//LEFT
			p.getChildren().add(l);
			l = new Line(measureXAdjusted + this.measureWidth, measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.startingYSpace + measureHeightAdjusted);	//RIGHT
			p.getChildren().add(l);
			l = new Line(measureXAdjusted, this.startingYSpace + measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.startingYSpace + measureHeightAdjusted);	//BOTTOM
			p.getChildren().add(l);

			//WRITE TAB
			Text T = new Text(TABx, spaceBetweenBarsHorizontal*2 + measureHeightAdjusted, "T");
			Text A = new Text(TABx, spaceBetweenBarsHorizontal*3 + measureHeightAdjusted, "A");
			Text B = new Text(TABx, spaceBetweenBarsHorizontal*4 + measureHeightAdjusted, "B");

			/* BOLD T A B */
			T.setFont(new Font(20));
			A.setFont(new Font(20));
			B.setFont(new Font(20));
			
			/* add T A B to pane */
			p.getChildren().add(T); 
			p.getChildren().add(A); 
			p.getChildren().add(B); 

			//Lines in Rectangle
			for(int y = 1; y < lines; y++) {
				l = new Line(measureXAdjusted, spaceBetweenBarsHorizontal*y + measureHeightAdjusted, this.measureWidth+measureXAdjusted, spaceBetweenBarsHorizontal*y + measureHeightAdjusted);	
				p.getChildren().add(l);
			}

			measureXAdjusted += this.measureWidth;
			currentMeasureCount++;

		}
	}

}
