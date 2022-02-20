package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Draw extends JPanel{
	
	private int x = 300;
	private int y = 17*5;;
	private int numberOfMeasures;
	private JFrame f;
	private Graphics2D g;
	
	public Draw(int numberOfMeasures, JFrame f) {
		this.numberOfMeasures = numberOfMeasures;
		this.f = f;
	}
	
	public void paint(Graphics g2) {
		setSize(2000,2000);
		this.setBackground(Color.white);
		super.paintComponent(g2);
		this.g = (Graphics2D) g2;
	    g.setStroke(new BasicStroke(2));
	    int newline = 0;
	    int yStart = 0;
	    int xStart = 0;
	    //draw measure box
	    for(int i = 0; i< this.numberOfMeasures; i++) {
	    	
	    	if(newline%4 == 0 && newline != 0) {
	    		yStart+=100;
	    		xStart = 0;
	    		//f.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png"))));
	    	}
	    	
	    	//Measure box
	    	g.drawLine(xStart, yStart, this.x+xStart, yStart);//top
		    g.drawLine(xStart, yStart, xStart, this.y + yStart);//left
			g.drawLine(xStart + this.x, yStart, this.x+xStart, this.y + yStart);// right
			g.drawLine(xStart, this.y + yStart, this.x+xStart,this.y + yStart);//bottom
			
            //Lines in Rectangle
		    for(int y = 1; y<5; y++) {
				g.drawLine(xStart, 17*y + yStart, this.x+xStart, 17*y + yStart);
				g.drawOval(xStart+10 + y*35,14*y + yStart,20,10);
		    }	
		    xStart += this.x;
		    newline++;
	    }
	}

}

