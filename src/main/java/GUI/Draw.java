package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

import models.Location;

public class Draw extends JPanel{

	private final int measureWidth = 300;
	private final int measureHeightDefault = 68;;
	private int numberOfMeasures;
	private Graphics2D g;
	private LinkedList<Location> aL;
	private final int moveMeasureDownValue = 200;

	public Draw(int numberOfMeasures, LinkedList<Location> aL) {
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

	public void paint(Graphics g2) {
		graphicSettings(g2);

		int currentMeasureCount = 0;
		int measureHeightAdjusted = 68;
		int noteYLocation = 0;
		int measureXAdjusted = 10;

		//draw measure box
		for(int i = 0; i < this.numberOfMeasures; i++) {
			if(currentMeasureCount%4 == 0 && currentMeasureCount != 0) {
				measureHeightAdjusted += moveMeasureDownValue;
				measureXAdjusted = 10;
			}

			//Measure box
			g.drawLine(measureXAdjusted, measureHeightAdjusted, this.measureWidth + measureXAdjusted, measureHeightAdjusted); //Top
			g.drawLine(measureXAdjusted, measureHeightAdjusted, measureXAdjusted, this.measureHeightDefault + measureHeightAdjusted); //Left
			g.drawLine(measureXAdjusted + this.measureWidth, measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.measureHeightDefault + measureHeightAdjusted); //Right
			g.drawLine(measureXAdjusted, this.measureHeightDefault + measureHeightAdjusted, this.measureWidth+measureXAdjusted, this.measureHeightDefault + measureHeightAdjusted); //Bottom

			//Lines in Rectangle
			for(int y = 1; y<4; y++) {
				g.drawLine(measureXAdjusted, 17*y + measureHeightAdjusted, this.measureWidth+measureXAdjusted, 17*y + measureHeightAdjusted);				
			}
			measureXAdjusted += this.measureWidth;
			currentMeasureCount++;
		}

		int noteX = -10;
		for(int j = 0; j < aL.size(); j++) {
			if(!aL.get(j).isChord()) { //NOT CHORD
				noteX += ((double)aL.get(j).getDuration()/64 * measureWidth);
				int yLocation = aL.get(j).getYLocation();
				
				ifDrawLineUnderMeasure(yLocation, noteX, noteYLocation);
			
				if(noteX > 300*4) { //NEW LINE
					noteYLocation += moveMeasureDownValue;
					noteX = 20;
				}
				
				g.drawOval(noteX, noteYLocation + yLocation, 20, 10); //drawNotes() to decide Y-COORD 

			} else { //IS CHORD
				g.drawOval(noteX, noteYLocation + aL.get(j).getYLocation(), 20, 10); //drawNotes() to decide Y-COORD 
			}
		}
	}

	public void ifDrawLineUnderMeasure(int yLocation, int noteX, int noteYLocation) {
		if(yLocation >= 187) { 
			g.drawLine(noteX - 2, noteYLocation + 187, noteX + 25, noteYLocation + 187); 
			g.drawLine(noteX - 2, noteYLocation + 170, noteX + 25, noteYLocation + 170);
			g.drawLine(noteX - 2, noteYLocation + 153, noteX + 25, noteYLocation + 153);
		}
		else if(yLocation >= 170) { 
			g.drawLine(noteX - 2, noteYLocation + 170, noteX+25, noteYLocation + 170);
			g.drawLine(noteX - 2, noteYLocation + 150, noteX+25, noteYLocation + 150);
		} 
		else if (yLocation >= 150) {
			g.drawLine(noteX - 2, noteYLocation + 153, noteX+25, noteYLocation + 153);
		}
	}
}