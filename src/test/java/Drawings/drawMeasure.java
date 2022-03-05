package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawings.Measure;
import javafx.scene.layout.Pane;

class drawMeasure {

	@Test
	void one_measure_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); //GUITAR HAS 5 LINES
		m.drawMeasure();
		assertTrue(1 == m.getCurrentMeasureCount());
	}
	
	@Test
	void one_measure_drums() {
		Pane p = new Pane();
		Measure m = new Measure(6, p, 1); //DRUMS HAS 6 LINES
		assertTrue(1 == m.getCurrentMeasureCount());
	}
	
	@Test
	void multiple_measures_drums() {
		Pane p = new Pane();
		Measure m = new Measure(6, p, 1); //DRUMS HAS 6 LINES
		assertTrue(20 == m.getCurrentMeasureCount());
	}
	
	@Test
	void multiple_measures_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 20); //DRUMS HAS 6 LINES
		assertTrue(20 == m.getCurrentMeasureCount());
	}
	
	@Test
	void measure_on_newline() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 6); //DRUMS HAS 6 LINES
		assertTrue(6 == m.getCurrentMeasureCount());
		assertTrue(2 == m.getCurrentTopOfMeasureHeight());
	

	}
	
//	@Test
//	void five_bars_guitar_measure() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void six_bars_drums_measure() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void tab_for_guitar_measure() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void clef_for_guitar_measure() {
//		fail("Not yet implemented");
//	}


}
