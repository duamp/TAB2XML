package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Location;
import models.ScorePartwise;

public class Draw extends JPanel{

	private int x = 300;
	private int y = 68;;
	private int numberOfMeasures;
	private JFrame f;
	private Graphics2D g;
	private LinkedList<Location> aL;
	private int numberOfNote;

	public Draw(int numberOfMeasures, JFrame f, LinkedList<Location> aL) {
		this.numberOfMeasures = numberOfMeasures;
		this.aL = aL;
		this.f = f;
	}

	public void paint(Graphics g2) {
		setSize(2000,2000);
		this.setBackground(Color.white);
		super.paintComponent(g2);
		this.g = (Graphics2D) g2;
		g.setStroke(new BasicStroke(2));
		int newline = 0;
		int measureY = 68;
		int measureX = 10;
		//draw measure box
		for(int i = 0; i< this.numberOfMeasures; i++) {
			if(newline%4 == 0 && newline != 0) {
				measureY+=100;
				measureX = 10;
				//f.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png"))));
			}

			//Measure box
			g.drawLine(measureX, measureY, this.x+measureX, measureY);//top
			g.drawLine(measureX, measureY, measureX, this.y + measureY);//left
			g.drawLine(measureX + this.x, measureY, this.x+measureX, this.y + measureY);// right
			g.drawLine(measureX, this.y + measureY, this.x+measureX, this.y + measureY);//bottom

			//Lines in Rectangle
			for(int y = 1; y<4; y++) {
				g.drawLine(measureX, 17*y + measureY, this.x+measureX, 17*y + measureY);				
			}
			measureX += this.x;
			newline++;
		}
		int noteX = 10;
		for(int j = 0; j < aL.size(); j++) {
			if(!aL.get(j).isChord()) { //NOT CHORD
				noteX += 35;
				int yLocation = aL.get(j).getYLocation();
				if(yLocation >= 187) { 
					g.drawLine(noteX - 2, 187, noteX+25, 187); 
					g.drawLine(noteX - 2, 170, noteX+25, 170);
					g.drawLine(noteX - 2, 153, noteX+25, 153);
				}
				else if(yLocation >= 170) { 
					g.drawLine(noteX - 2, 170, noteX+25, 170);
					g.drawLine(noteX - 2, 150, noteX+25, 150);
				} 
				else if (yLocation >= 150){g.drawLine(noteX - 2, 153, noteX+25, 153);}

				g.drawOval(noteX, yLocation, 20,10); //drawNotes() to decide Y-COORD 
			} else { //IS CHORD
				g.drawOval(noteX, aL.get(j).getYLocation(), 20,10); //drawNotes() to decide Y-COORD 
			}

		}


	}

}

