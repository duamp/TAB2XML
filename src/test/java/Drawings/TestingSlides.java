package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.DrawSlides;
import drawMusic.DrawSlurs;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import models.measure.note.notations.Slide;
import models.measure.note.notations.Slur;
import note_information.DrumInformation;
import note_information.GuitarInformation;

class TestingSlides {

	@Test
	void test_slides_input_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		Slide st = new Slide("start");
		Slide sto = new Slide("stop");
		ArrayList<Slide> start = new ArrayList<>();
		start.add(st);
		ArrayList<Slide> stop = new ArrayList<>();
		stop.add(sto);

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", null, start, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, start, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, start, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		g.drawGuitarNotes();
		DrawSlides sl = new DrawSlides(aLGuitar, p);
		sl.drawSlides();		
		LinkedList<GuitarInformation> t = g.getNoteObject();


		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertTrue(aLGuitar.get(0).isSlide());
		assertTrue(aLGuitar.get(1).isSlide());
		assertTrue(aLGuitar.get(2).isSlide());
		assertTrue(aLGuitar.get(3).isSlide());
		assertTrue(aLGuitar.get(4).isSlide());
		assertTrue(aLGuitar.get(5).isSlide());
	}

	@Test
	void test_slides_output_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		Slide st = new Slide("start");
		Slide sto = new Slide("stop");
		ArrayList<Slide> start = new ArrayList<>();
		start.add(st);
		ArrayList<Slide> stop = new ArrayList<>();
		stop.add(sto);

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", null, start, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, start, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, start, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "Eight", null, stop, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		g.drawGuitarNotes();
		DrawSlides sl = new DrawSlides(aLGuitar, p);
		sl.drawSlides();		

		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertEquals(6, sl.getNos()); //TEST TO SEE CORRECT AMOUNT OF SLURS
	}
}
