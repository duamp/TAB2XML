package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import drawMusic.DrawBars;
import drawMusic.DrawGuitarNotes;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import note_information.GuitarInformation;

class TestingBars {

	@Test
	void test_bar_x_location() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", null, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		LinkedList<GuitarInformation> t = g.getNoteObject();
		g.drawGuitarNotes();
		
		DrawBars db = new DrawBars(aLGuitar, p);
		db.drawbars();
		LinkedList<Integer> i = db.getXLocation();
		assertEquals(aLGuitar.get(0).getNoteX(), i.get(0));
		assertEquals(aLGuitar.get(1).getNoteX(), i.get(1));
		assertEquals(aLGuitar.get(2).getNoteX(), i.get(2));
		assertEquals(aLGuitar.get(3).getNoteX(), i.get(3));
		assertEquals(aLGuitar.get(4).getNoteX(), i.get(4));
		assertEquals(aLGuitar.get(5).getNoteX(), i.get(5));

	}
	@Test
	void test_bar_eight_note() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "eighth", null, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "eighth", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "eighth", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "eighth", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "eighth", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "eighth", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		LinkedList<GuitarInformation> t = g.getNoteObject();
		g.drawGuitarNotes();
		
		DrawBars db = new DrawBars(aLGuitar, p);
		db.drawbars();
		LinkedList<Integer> i = db.getXLocation();
		assertEquals(0, db.get16thBars());
		assertEquals(2, db.get8thBars());

	}
	
	@Test
	void test_bar_16_note() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "16th", null, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "16th", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "16th", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "16th", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "16th", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "16th", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		LinkedList<GuitarInformation> t = g.getNoteObject();
		g.drawGuitarNotes();
		
		DrawBars db = new DrawBars(aLGuitar, p);
		db.drawbars();
		LinkedList<Integer> i = db.getXLocation();
		assertEquals(3, db.get16thBars());
		assertEquals(0, db.get8thBars());
	}

}
