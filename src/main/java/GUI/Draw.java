package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import models.Location;
import models.LocationGuitar;

public class Draw extends JPanel{

	private final int measureWidth = 300;
	private final int spaceBetweenBarsHorizontal = 17;
	private final int startingYSpace = 5*spaceBetweenBarsHorizontal;
	private final int startingXSpace = 10;
	private int numberOfMeasures;
	private Graphics2D g;
	private LinkedList<LocationGuitar> aL;
	private final int moveMeasureDownValue = 200;
	private final Color textColor = Color.BLACK;
	private final Color bgColor = Color.WHITE;
	private final int measuresPerLine = 4;

	public Draw(int numberOfMeasures, LinkedList<LocationGuitar> aL) {
		this.numberOfMeasures = numberOfMeasures;
		this.aL = aL;
	}

	public void graphicSettings(Graphics g2) {
		setSize(2000,2000);
		this.setBackground(Color.white);
		super.paintComponent(g2);
		this.g = (Graphics2D) g2;
		g.setStroke(new BasicStroke(2));
	}

	public int getStartingYSpace() {return startingYSpace;}
	public int getSpaceBetweenBarsHorizontal() {return spaceBetweenBarsHorizontal;}

	public void paint(Graphics g2) {
		graphicSettings(g2);
		int currentMeasureCount = 0;
		int measureHeightAdjusted = startingYSpace;
		int noteYLocation = 0;
		int measureXAdjusted = startingXSpace;

		//draw measure box
		for(int i = 0; i < this.numberOfMeasures; i++) {
			if(currentMeasureCount%4 == 0 && currentMeasureCount != 0) {
				measureHeightAdjusted += moveMeasureDownValue;
				measureXAdjusted = startingXSpace;
			}

			//Measure box
			g.drawLine(measureXAdjusted, measureHeightAdjusted, this.measureWidth + measureXAdjusted, measureHeightAdjusted); //Top
			g.drawLine(measureXAdjusted, measureHeightAdjusted, measureXAdjusted, this.startingYSpace + measureHeightAdjusted); //Left
			g.drawLine(measureXAdjusted + this.measureWidth, measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.startingYSpace + measureHeightAdjusted); //Right
			g.drawLine(measureXAdjusted, this.startingYSpace + measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.startingYSpace + measureHeightAdjusted); //Bottom

			//Lines in Rectangle
			for(int y = 1; y < 5; y++) {
				g.drawLine(measureXAdjusted, spaceBetweenBarsHorizontal*y + measureHeightAdjusted, this.measureWidth+measureXAdjusted, spaceBetweenBarsHorizontal*y + measureHeightAdjusted);				
			}
			measureXAdjusted += this.measureWidth;
			currentMeasureCount++;
		}

		
		
		
		
		int noteX = -10;
		int measureNumber = 1;
		for(int j = 0; j < aL.size(); j++) {
			String note = "" + aL.get(j).getFret() + "";
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D rect = fm.getStringBounds(note, g);
			g.setColor(bgColor);

			if(!aL.get(j).isChord()) { //&& j<aL.size()-1 && !aL.get(j+1).isChord()) { //NOT CHORD
				noteX += ((double)aL.get(j).getDuration()/64 * measureWidth); 
				
				
				if(noteX > measureWidth*measureNumber + this.startingXSpace) {
					noteX = measureWidth*measureNumber+10+this.startingXSpace;
					measureNumber+=1;
				}
				
				
				int yLocation = aL.get(j).getYCoord();
				 //REMOVE LINE BEHIND NOTE
	            g.fillRect(noteX, noteYLocation + yLocation - fm.getAscent(), (int) rect.getWidth(),(int) rect.getHeight());
	            //SET BACKGROUND COLOR TO WHITE i.e., appears as if there was no line to begin with.
	            g.setColor(textColor);


				//START ADDING MEASURES ON NEW Y-COORD AND ORIGINAL X-COORD.
				if(noteX > this.measureWidth * this.measuresPerLine) { 
					noteYLocation += moveMeasureDownValue;
					noteX = this.startingXSpace + 10;
					
				}
				
				g.drawString(note, noteX, noteYLocation + yLocation);			
				
			} else { 
				int yLocation = aL.get(j).getYCoord();
				 //REMOVE LINE BEHIND NOTE
	            g.fillRect(noteX, noteYLocation + yLocation - fm.getAscent(), (int) rect.getWidth(),(int) rect.getHeight());
	            //SET BACKGROUND COLOR TO WHITE i.e., appears as if there was no line to begin with.
	            g.setColor(textColor);
				g.drawString("" + aL.get(j).getFret() + "", noteX, noteYLocation + aL.get(j).getYCoord());	
				
				}
			}
		}

	

//	|-----------0-----|-0---------------|-0---------------|-0---------------|-0---------------|-0---------------|
//	|---------0---0---|-0---------------|-0---------------|-0---------------|-0---------------|-0---------------|
//	|-------1-------1-|-1---------------|-0---------------|-0---------------|-0---------------|-0---------------|
//	|-----2-----------|-2---------------|-0---------------|-0---------------|-0---------------|-0---------------|
//	|---2-------------|-2---------------|-0---------------|-0---------------|-0---------------|-0---------------|
//	|-0---------------|-0---------------|-0---------------|-0---------------|-0---------------|-0---------------|

	//
	//		int noteX = -10;
	//		for(int j = 0; j < aL.size(); j++) {
	//			if(!aL.get(j).isChord()) { //NOT CHORD
	//				noteX += ((double)aL.get(j).getDuration()/64 * measureWidth);
	//				int yLocation = aL.get(j).getYLocation();
	//				
	//				ifDrawLineUnderMeasure(yLocation, noteX, noteYLocation);
	//			
	//				if(noteX > 300*4) { //NEW LINE
	//					noteYLocation += moveMeasureDownValue;
	//					noteX = 20;
	//				}
	//				
	//				g.drawOval(noteX, noteYLocation + yLocation, 20, 10); //drawNotes() to decide Y-COORD 
	//
	//			} else { //IS CHORD
	//				g.drawOval(noteX, noteYLocation + aL.get(j).getYLocation(), 20, 10); //drawNotes() to decide Y-COORD 
	//			}
	//		}
	//	}
	//
	//	public void ifDrawLineUnderMeasure(int yLocation, int noteX, int noteYLocation) {
	//		if(yLocation >= 187) { 
	//			g.drawLine(noteX - 2, noteYLocation + 187, noteX + 25, noteYLocation + 187); 
	//			g.drawLine(noteX - 2, noteYLocation + 170, noteX + 25, noteYLocation + 170);
	//			g.drawLine(noteX - 2, noteYLocation + 153, noteX + 25, noteYLocation + 153);
	//		}
	//		else if(yLocation >= 170) { 
	//			g.drawLine(noteX - 2, noteYLocation + 170, noteX+25, noteYLocation + 170);
	//			g.drawLine(noteX - 2, noteYLocation + 150, noteX+25, noteYLocation + 150);
	//		} 
	//		else if (yLocation >= 150) {
	//			g.drawLine(noteX - 2, noteYLocation + 153, noteX+25, noteYLocation + 153);
	//		}

}