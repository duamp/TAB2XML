package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import drawings.DrawGuitarNotes;
import drawings.Measure;
import javafx.scene.layout.Pane;
import models.LocationGuitar;
import models.Part;
import models.ScorePartwise;

class TestingGuitar {

//	@Test
//	void note_X_position() {
//		Pane p = new Pane();
//		Measure m = new Measure(5, p, 1); 
//		m.drawMeasure();
//		LinkedList<LocationGuitar> aLGuitar = new LinkedList<LocationGuitar>();
//		ScorePartwise sp;
//
//		LocationGuitar l1 = new LocationGuitar(1, 6, 8, false, null, 0, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0
//		LocationGuitar l2 = new LocationGuitar(2, 3, 8, false, null, 0, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0
//		aLGuitar.add(l1);
//		aLGuitar.add(l2);
//
//		LinkedList<LocationGuitar> t = g.getNoteObject();
//		g.drawGuitarNotes();
//		assertEquals(40,t.get(0).getNoteX(), 2); //FIRST NOTE LOCATION 40
//		assertEquals(150,t.get(0).getNoteX() + (t.get(0).getDuration()/g.getUnitsInMeasure()) * g.getMeasureWidth(),2); //SECOND NOTE FIRST NOTE + DURATION FIRST
//	}

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
