package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import note_information.DrumInformation;
import note_information.GuitarInformation;

class TestingDrums {

	@Test
	void note_X_position() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		DrumInformation l1 = new DrumInformation("A", 8, 3, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("A", 8, 4,"x", false, 1, "Eight");  ///NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2

		DrawDrumsNotes g = new DrawDrumsNotes(p, aLDrum);
		LinkedList<DrumInformation> t = g.getNoteObject();

		g.drawDrumNotes();
		assertEquals(35, t.get(0).getNoteX(), 2); //FIRST NOTE LOCATION 40
		assertEquals(185,t.get(0).getNoteX() + ((double)t.get(0).getDuration()/(double)g.getUnitsInMeasure()) * g.getMeasureWidth(), 2); //SECOND NOTE FIRST NOTE + DURATION FIRST		
	}

	@Test
	void starting_note_Y_position_all_strings() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4


		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum);
		LinkedList<DrumInformation> t = d.getNoteObject();


		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		d.drawDrumNotes();
		assertEquals(88, t.get(0).getNoteY(), 1); 
		assertEquals(80,t.get(1).getNoteY(), 1); 
		assertEquals(72,t.get(2).getNoteY(), 1); 
		assertEquals(64,t.get(3).getNoteY(), 1); 	
		assertEquals(56,t.get(4).getNoteY(), 1); 	
		assertEquals(46.5,t.get(5).getNoteY(), 1); 	
		assertEquals(38,t.get(6).getNoteY(), 1); 	

	}

	@Test
	void correctly_added__one_note() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

		aLDrum.add(l1);	//ADD RANDOM NOTE 1

		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum);
		d.drawDrumNotes();
		assertTrue(1 == d.getCurrentNotesPrinted());
	}

	@Test
	void correctly_added_many_notes() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)


		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4


		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum);
		d.drawDrumNotes();
		assertTrue(7 == d.getCurrentNotesPrinted());
	}

	@Test
	void note_Y_move_down_new_measure() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 2, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 2, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 3, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 4, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 4, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)


		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 5
		aLDrum.add(l6);	//ADD RANDOM NOTE 6

		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum);
		LinkedList<DrumInformation> t = d.getNoteObject();

		System.out.println(m.getCurrentTopOfMeasureHeight());
		d.drawDrumNotes();

		assertEquals(88, t.get(0).getNoteY(), 1); //FIRST NOTE LOCATION 40
		assertEquals(80,t.get(1).getNoteY(), 1); //SECOND NOTE FIRST NOTE + DURATION FIRST	
		assertEquals(72,t.get(2).getNoteY(), 1); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(256,t.get(4).getNoteY(), 1); //SECOND NOTE FIRST NOTE + DURATION FIRST		
		assertEquals(246.5,t.get(5).getNoteY(), 1); //SECOND NOTE FIRST NOTE + DURATION FIRST		
	}

		@Test
		void note_X_reset_beginning_measure() {
			Pane p = new Pane();
			Measure m = new Measure(5, p, 6); 
			m.drawMeasure();
			LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();
	
			DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
			DrumInformation l2 = new DrumInformation("C", 8, 4, "x", false, 2, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
			DrumInformation l3 = new DrumInformation("C", 8, 4, "x", false, 3, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
			DrumInformation l4 = new DrumInformation("C", 8, 4, "x", false, 4, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
			DrumInformation l5 = new DrumInformation("C", 8, 4, "x", false, 5, "Eight"); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

	
	
			aLDrum.add(l1);	//ADD RANDOM NOTE 1
			aLDrum.add(l2);	//ADD RANDOM NOTE 2
			aLDrum.add(l3);	//ADD RANDOM NOTE 3
			aLDrum.add(l4);	//ADD RANDOM NOTE 4
			aLDrum.add(l5);	//ADD RANDOM NOTE 5
	
			DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum);
			LinkedList<DrumInformation> t = d.getNoteObject();
	
			d.drawDrumNotes();
			System.out.println(t.get(0).getNoteX() + " " + t.get(1).getNoteX() + " " + t.get(2).getNoteX() + " " + t.get(3).getNoteX() + " " + t.get(4).getNoteX());
			assertEquals(35, t.get(0).getNoteX(), 2); //FIRST NOTE - BEGINNING OF LINE
			assertEquals(320,t.get(1).getNoteX(), 2);  //SECOND NOTE - BEGINNING OF SECOND MEASURE
			assertEquals(620,t.get(2).getNoteX(), 2);  //THIRD NOTE - BEGINNING OF THIRD MEASURE
			assertEquals(35,t.get(3).getNoteX(), 2);  //FOURTH NOTE - BEGINNING OF LINE

		}

}
