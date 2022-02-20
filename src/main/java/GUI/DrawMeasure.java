package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawMeasure extends JPanel{
	
	private int x = 300;
	private int y = 17*5;;
	private int numberOfMeasures;
	public DrawMeasure(int numberOfMeasures) {
		this.numberOfMeasures = numberOfMeasures;
	}
	
	public void paint(Graphics g2) {
		setSize(2000,2000);
		this.setBackground(Color.white);
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
	    g.setStroke(new BasicStroke(2));
	    
	    for(int i = 0; i< this.numberOfMeasures; i++) {
//		    Basic rectrangle around outside
	    	g.drawLine(this.x*i, 0, this.x+this.x*i, 0);//top
		    g.drawLine(this.x*i, 0, this.x*i, this.y);//left
			g.drawLine(this.x*i+this.x, 0, this.x+this.x*i, this.y);// right
			g.drawLine(this.x*i, this.y, this.x+this.x*i,this.y);//bottom
			
//			lines inside rectangle
		    for(int y = 1; y<5; y++) {
				g.drawLine(this.x*i, 17*y, this.x+i*this.x,17*y);
		    }	
	    }
	}
}

