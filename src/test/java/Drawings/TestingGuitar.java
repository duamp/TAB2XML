package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import drawings.DrawGuitarNotes;
import drawings.Measure;
import javafx.scene.layout.Pane;
import models.GuitarInformation;
import models.Part;
import models.ScorePartwise;

class TestingGuitar {

	@Test
	void note_X_position() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();
		
		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE - DURATION(STRING)
		GuitarInformation l2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 1,"Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE - DURATION(STRING)
		aLGuitar.add(l1);
		aLGuitar.add(l2);

		LinkedList<GuitarInformation> t = g.getNoteObject();
		g.drawGuitarNotes();
		assertEquals(40,t.get(0).getNoteX(), 2); //FIRST NOTE LOCATION 40
		assertEquals(150,t.get(0).getNoteX() + (t.get(0).getDuration()/g.getUnitsInMeasure()) * g.getMeasureWidth(),2); //SECOND NOTE FIRST NOTE + DURATION FIRST
	}

	@Test
	void note_Y_position() {

	}

	@Test
	void correct_number_added_notes() {

	}
	
	@Test
	void correct_position_slur() {

	}
	
	@Test
	void note_Y_move_down_new_measure() {

	}
	
	@Test
	void note_X_reset_beginning_measure() {

	}


}
