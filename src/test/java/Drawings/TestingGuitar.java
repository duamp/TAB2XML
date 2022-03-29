package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import drawMusic.DrawGuitarNotes;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import models.Part;
import models.ScorePartwise;
import note_information.GuitarInformation;

class TestingGuitar {

	//@Test
	void note_X_position() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE - DURATION(STRING)
		GuitarInformation l2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 1, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE - DURATION(STRING)
		aLGuitar.add(l1);	//ADD RANDOM NOTE 1
		aLGuitar.add(l2);	//ADD RANDOM NOTE 2

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		LinkedList<GuitarInformation> t = g.getNoteObject();

		g.drawGuitarNotes();
		assertEquals(35, t.get(0).getNoteX(), 2); //FIRST NOTE LOCATION 40
		assertEquals(185,t.get(0).getNoteX() + ((double)t.get(0).getDuration()/(double)g.getUnitsInMeasure()) * g.getMeasureWidth(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
	}

	@Test
	void starting_note_Y_position_all_strings() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 1, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		LinkedList<GuitarInformation> t = g.getNoteObject();


		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		g.drawGuitarNotes();
		assertEquals(4, t.get(0).getNoteY(), 2); //FIRST NOTE LOCATION 40
		assertEquals(21,t.get(1).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(38,t.get(2).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(55,t.get(3).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(72,t.get(4).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(89,t.get(5).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
	}

	@Test
	void correctly_added__one_note() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)

		aLGuitar.add(l1);	//ADD RANDOM NOTE 1

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		g.drawGuitarNotes();
		assertTrue(1 == g.getCurrentNotesPrinted());
	}

	@Test
	void correctly_added_many_notes() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation l2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 1, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation l3 = new GuitarInformation(1, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation l4 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation l5 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation l6 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation l7 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)


		aLGuitar.add(l1);	//ADD RANDOM NOTE 1
		aLGuitar.add(l2);	//ADD RANDOM NOTE 2
		aLGuitar.add(l3);	//ADD RANDOM NOTE 3
		aLGuitar.add(l4);	//ADD RANDOM NOTE 4
		aLGuitar.add(l5);	//ADD RANDOM NOTE 4
		aLGuitar.add(l6);	//ADD RANDOM NOTE 4
		aLGuitar.add(l7);	//ADD RANDOM NOTE 4


		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		g.drawGuitarNotes();
		assertTrue(7 == g.getCurrentNotesPrinted());
	}

	@Test
	void note_Y_move_down_new_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation l2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 1, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation l3 = new GuitarInformation(1, 3, 8, false, null, 0, 0, 2, "Eight"); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation l4 = new GuitarInformation(1, 3, 8, false, null, 0, 0, 3, "Eight"); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation l5 = new GuitarInformation(1, 3, 8, false, null, 0, 0, 4, "Eight"); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation l6 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 4, "Eight"); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(l1);	//ADD RANDOM NOTE 1
		aLGuitar.add(l2);	//ADD RANDOM NOTE 2
		aLGuitar.add(l3);	//ADD RANDOM NOTE 3
		aLGuitar.add(l4);	//ADD RANDOM NOTE 4
		aLGuitar.add(l5);	//ADD RANDOM NOTE 5
		aLGuitar.add(l6);	//ADD RANDOM NOTE 6

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		LinkedList<GuitarInformation> t = g.getNoteObject();

		System.out.println(m.getCurrentTopOfMeasureHeight());
		g.drawGuitarNotes();
		assertEquals(4, t.get(0).getNoteY(), 2); //FIRST NOTE LOCATION 40
		assertEquals(21,t.get(1).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(204,t.get(4).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(221,t.get(5).getNoteY(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
	}

	@Test
	void note_X_reset_beginning_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 6); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation l1 = new GuitarInformation(1, 6, 8, false, null, 0, 0, 1, "Eight"); 
		GuitarInformation l2 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 2, "Eight"); 
		GuitarInformation l3 = new GuitarInformation(2, 3, 8, false, null, 0, 0, 3, "Eight");
		GuitarInformation l4 = new GuitarInformation(3, 3, 8, false, null, 0, 0, 4, "Eight");
		GuitarInformation l5 = new GuitarInformation(3, 3, 8, false, null, 0, 0, 5, "Eight");


		aLGuitar.add(l1);	//ADD RANDOM NOTE 1
		aLGuitar.add(l2);	//ADD RANDOM NOTE 2
		aLGuitar.add(l3);	//ADD RANDOM NOTE 3
		aLGuitar.add(l4);	//ADD RANDOM NOTE 4
		aLGuitar.add(l5);	//ADD RANDOM NOTE 5

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar);
		LinkedList<GuitarInformation> t = g.getNoteObject();

		g.drawGuitarNotes();
		System.out.println(t.get(0).getNoteX() + " " + t.get(1).getNoteX() + " " + t.get(2).getNoteX() + " " + t.get(3).getNoteX() + " " + t.get(4).getNoteX());
		assertEquals(35, t.get(0).getNoteX(), 2); //FIRST NOTE - BEGINNING OF LINE
		assertEquals(320,t.get(1).getNoteX(), 2);  //SECOND NOTE - BEGINNING OF SECOND MEASURE
		assertEquals(620,t.get(2).getNoteX(), 2);  //THIRD NOTE - BEGINNING OF THIRD MEASURE
		assertEquals(35,t.get(3).getNoteX(), 2);  //FOURTH NOTE - BEGINNING OF LINE
	}
}
