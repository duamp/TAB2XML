package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.itextpdf.text.List;

import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.DrawSlurs;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import models.measure.note.notations.Slur;
import note_information.DrumInformation;
import note_information.GuitarInformation;

class TestingSlurs {

	@Test
	void test_slurs_input_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		Slur stop = new Slur("stop");
		ArrayList<Slur> s = new ArrayList<>();
		s.add(stop);

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", null, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, s, 1, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, s, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, s, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		LinkedList<GuitarInformation> t = g.getNoteObject();


		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertFalse(aLGuitar.get(0).isSlur());
		assertTrue(aLGuitar.get(1).isSlur());
		assertFalse(aLGuitar.get(2).isSlur());
		assertTrue(aLGuitar.get(3).isSlur());
		assertFalse(aLGuitar.get(4).isSlur());
		assertTrue(aLGuitar.get(5).isSlur());

		
	}
	
	@Test
	void test_slurs_output_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		Slur stop = new Slur("stop");
		ArrayList<Slur> s = new ArrayList<>();
		s.add(stop);

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", null, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, s, 1, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, s, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, s, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s7 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s8 = new GuitarInformation(6, 3, 8, false, s, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 5
		aLGuitar.add(s6);	//ADD RANDOM NOTE 6
		aLGuitar.add(s7);	//ADD RANDOM NOTE 7
		aLGuitar.add(s8);	//ADD RANDOM NOTE 8

		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		DrawGuitarNotes g = new DrawGuitarNotes(p, aLGuitar, null);
		g.drawGuitarNotes();
		DrawSlurs slur = new DrawSlurs(aLGuitar, null, p);
		slur.drawSlursGuitar();
		assertEquals(4, slur.getNos()); //TEST TO SEE CORRECT AMOUNT OF SLURS
		assertEquals(115, slur.getSlurMiddleX(aLGuitar.get(0).getNoteX(), aLGuitar.get(1).getNoteX())); //GET MIDDLE X OF SLURS
		assertEquals(356, slur.getSlurMiddleX(aLGuitar.get(2).getNoteX(), aLGuitar.get(3).getNoteX())); //GET MIDDLE X OF SLURS
		assertEquals(442, slur.getSlurMiddleX(aLGuitar.get(4).getNoteX(), aLGuitar.get(5).getNoteX())); //GET MIDDLE X OF SLURS
	}
	
	@Test
	void test_slurs_input_drums() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();
		Slur stop = new Slur("stop");
		ArrayList<Slur> s = new ArrayList<>();
		s.add(stop);

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l8 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4
		aLDrum.add(l8);	//ADD RANDOM NOTE 4



		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum, null);
		DrawSlurs slur = new DrawSlurs(null, aLDrum, p);
		
		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertFalse(aLDrum.get(0).isSlur());
		assertTrue(aLDrum.get(1).isSlur());
		assertFalse(aLDrum.get(2).isSlur());
		assertTrue(aLDrum.get(3).isSlur());
		assertFalse(aLDrum.get(4).isSlur());
		assertTrue(aLDrum.get(5).isSlur());
		assertFalse(aLDrum.get(6).isSlur());
		assertTrue(aLDrum.get(7).isSlur());
	}
	
	@Test
	void test_slurs_output_drums() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		Slur stop = new Slur("stop");
		ArrayList<Slur> s = new ArrayList<>();
		s.add(stop);

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l8 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, s, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4
		aLDrum.add(l8);	//ADD RANDOM NOTE 4

		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum, null);
		d.drawDrumNotes();
		DrawSlurs slur = new DrawSlurs(null,aLDrum, p);
		slur.drawSlursDrums();
		assertEquals(4, slur.getNos()); //TEST TO SEE CORRECT AMOUNT OF SLURS
		assertEquals(70, slur.getSlurMiddleX(aLDrum.get(0).getNoteX(), aLDrum.get(1).getNoteX())); //GET MIDDLE X OF SLURS
		assertEquals(132, slur.getSlurMiddleX(aLDrum.get(2).getNoteX(), aLDrum.get(3).getNoteX())); //GET MIDDLE X OF SLURS
		assertEquals(194, slur.getSlurMiddleX(aLDrum.get(4).getNoteX(), aLDrum.get(5).getNoteX())); //GET MIDDLE X OF SLURS
	}
	
}
