package drawMusic;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Measure {
	private Pane p;
	private int numberOfMeasures;
	private int startingYSpace;
	private final int startingXSpace = 10;
	private int measureWidth = 300;
	private final int spaceBetweenBarsHorizontal = 17;
	private final int moveMeasureDownValue = 200;
	private final int measuresPerLine = 3;
	private int lines;
	private final int TABx = startingXSpace + 5;
	private int currentMeasureCount = 0;
	private int currentTopOfMeasureHeight = 0;
	private int tabsDrawnAmount = 0;
	private int fontsize = 50;

	public Measure(int numberofLines, Pane p, int numberOfMeasures) {
		lines = numberofLines;
		this.p = p;
		this.numberOfMeasures = numberOfMeasures;
	}

	public void drawMeasure() {
		startingYSpace=spaceBetweenBarsHorizontal*lines-1; //GUITAR TAB HAS 6 LINES
		int measureXAdjusted = startingXSpace;
		//draw measure box
		drawTabsorClef();
		drawMeasureNumber();
		for(int i = 0; i < this.numberOfMeasures; i++) {
			if(currentMeasureCount % measuresPerLine == 0 && currentMeasureCount != 0) {
				currentTopOfMeasureHeight += moveMeasureDownValue;
				measureXAdjusted = startingXSpace;

				/* DRAW T A B && MEASURE NUMBER BEGINNING OF EACH MEASURE */
				drawTabsorClef();
				drawMeasureNumber();
			}
			
			
//			if(false) {
//				drawTimeClef();
//			}

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
		return this.currentMeasureCount;
	}

	public int getCurrentTopOfMeasureHeight() {
		return this.currentTopOfMeasureHeight;
	}

	public int getSpaceBetweenBarsHorizontal() {return spaceBetweenBarsHorizontal;}

	public void drawTabsorClef() {
		if(this.lines == 5) {
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

		} else {
			// adds the bars
			int fontsize = 50;
			Text i1 = new Text(TABx - 8, spaceBetweenBarsHorizontal*3 + currentTopOfMeasureHeight, "I");
			Text i2 = new Text(TABx , spaceBetweenBarsHorizontal*3 + currentTopOfMeasureHeight, "I");
			i1.setFont(new Font(fontsize));
			i2.setFont(new Font(fontsize));
			p.getChildren().add(i1); 
			p.getChildren().add(i2);
			// adds the 44
		}
		tabsDrawnAmount++;

	}



	public void drawMeasureNumber() {	
		Text number = new Text(TABx - 5, spaceBetweenBarsHorizontal*2 + currentTopOfMeasureHeight - 60, "" + (getCurrentMeasureCount() + 1) + "");
		number.setFont(new Font(14));
		p.getChildren().add(number); 

	}

	public int getMoveMeasureDownValue() {return this.moveMeasureDownValue;}
	public int getHorizontalLinesInMeasure() {return this.lines;}
	public int getTabsDrawnAmount() {return this.tabsDrawnAmount;}
	public void setMeasureWidth(int measureWidth) {this.measureWidth = measureWidth;}
	public void setFontSize(int f) {this.fontsize =f;}
	public int getMeasureWidth() {return this.measureWidth;}
	public int getStartingYSpace() {return this.startingYSpace;}


}
