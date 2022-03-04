package drawPreview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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
