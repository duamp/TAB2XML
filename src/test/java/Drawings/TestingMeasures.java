package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import drawMusic.Measure;
import javafx.scene.layout.Pane;

class TestingMeasures {
	
	@Test
	void one_measure_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		assertTrue(1 == m.getCurrentMeasureCount());
	}

	@Test
	void one_measure_drums() {
		Pane p = new Pane();
		Measure m = new Measure(6, p, 1);  
		m.drawMeasure();
		assertTrue(1 == m.getCurrentMeasureCount());
	}

	@Test
	void measure_on_newline() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 4); 
		m.drawMeasure();
		assertTrue(4 == m.getCurrentMeasureCount());
		assertTrue(200 == m.getCurrentTopOfMeasureHeight());
	}

	@Test
	void multiple_measures_on_newline() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 99); 
		m.drawMeasure();
		assertTrue(99 == m.getCurrentMeasureCount());
		assertTrue(32*m.getMoveMeasureDownValue() == m.getCurrentTopOfMeasureHeight()); 
	}

	@Test
	void multiple_measures_drums() {
		Pane p = new Pane();
		Measure m = new Measure(6, p, 20); 
		m.drawMeasure();
		assertTrue(20 == m.getCurrentMeasureCount());
	}

	@Test
	void multiple_measures_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 20); 
		m.drawMeasure();
		assertTrue(20 == m.getCurrentMeasureCount());
	}

	@Test
	void four_bars_guitar_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		assertTrue(5 == m.getHorizontalLinesInMeasure());
	}

	@Test
	void three_bars_drums_measure() {
		Pane p = new Pane();
		Measure m = new Measure(6, p, 1); 
		m.drawMeasure();
		assertTrue(6 == m.getHorizontalLinesInMeasure());
	}

	@Test
	void tab_text_for_guitar_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		assertEquals(1, m.getTabsDrawnAmount());
	}

	@Test
	void multiple_tab_text_for_guitar_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 100); 
		m.drawMeasure();
		assertEquals(34,m.getTabsDrawnAmount());

	}
	
	@Test
	void single_doubleLines_for_drum_measure() {
		Pane p = new Pane();
		Measure m = new Measure(4, p, 1); 
		m.drawMeasure();
		assertEquals(1, m.getTabsDrawnAmount());
	}
	
	@Test
	void multiple_doubleLine_for_drum_measure() {
		Pane p = new Pane();
		Measure m = new Measure(4, p, 100); 
		m.drawMeasure();
		assertEquals(34,m.getTabsDrawnAmount());

	}

}
