package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.DrawSlurs;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import models.measure.note.Grace;
import models.measure.note.notations.Slur;
import note_information.DrumInformation;
import note_information.GuitarInformation;

class TestGraceNotes {

	@Test
	void test_grace_guitar() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<GuitarInformation> aLGuitar = new LinkedList<GuitarInformation>();

		Grace g = new Grace();

		GuitarInformation s1 = new GuitarInformation(1, 6, 8, false, null, 1, "Eight", g, null, 0); //STRING 1 - FRET 6 - DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1- DURATION(STRING)
		GuitarInformation s2 = new GuitarInformation(2, 3, 8, false, null, 1, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 1 - DURATION(STRING)
		GuitarInformation s3 = new GuitarInformation(3, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 1 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2- DURATION(STRING)
		GuitarInformation s4 = new GuitarInformation(4, 3, 8, false, null, 2, "Eight", g, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s5 = new GuitarInformation(5, 3, 8, false, null, 2, "Eight", null, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)
		GuitarInformation s6 = new GuitarInformation(6, 3, 8, false, null, 2, "Eight", g, null, 0); //STRING 2 - FRET 3  DURATION 8 - SLUR NULL - NOTEX 0 - NOTEY 0 - CURRENT MEASURE 2 - DURATION(STRING)

		aLGuitar.add(s1);	//ADD RANDOM NOTE 1
		aLGuitar.add(s2);	//ADD RANDOM NOTE 2
		aLGuitar.add(s3);	//ADD RANDOM NOTE 3
		aLGuitar.add(s4);	//ADD RANDOM NOTE 4
		aLGuitar.add(s5);	//ADD RANDOM NOTE 4
		aLGuitar.add(s6);	//ADD RANDOM NOTE 4

		DrawGuitarNotes dr = new DrawGuitarNotes(p, aLGuitar, null);
		dr.drawGuitarNotes();
		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertTrue(aLGuitar.get(0).isGrace());
		assertFalse(aLGuitar.get(1).isGrace());
		assertFalse(aLGuitar.get(2).isGrace());
		assertTrue(aLGuitar.get(3).isGrace());
		assertFalse(aLGuitar.get(4).isGrace());
		assertTrue(aLGuitar.get(5).isGrace());

	}


	@Test
	void test_grace_drums() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();
		Grace g = new Grace();

		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight", 0, null, g); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 1, "Eight", 0, null, g); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, null, g); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l8 = new DrumInformation("B", 8, 4, "x", false, 1, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)

		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4
		aLDrum.add(l8);	//ADD RANDOM NOTE 4


		/*
		 * PRINT DIFFERENT STRING IN DIFFERENT LOCATIONS
		 */

		assertTrue(aLDrum.get(0).isGrace());
		assertFalse(aLDrum.get(1).isGrace());
		assertFalse(aLDrum.get(2).isGrace());
		assertFalse(aLDrum.get(3).isGrace());
		assertTrue(aLDrum.get(4).isGrace());
		assertFalse(aLDrum.get(5).isGrace());
		assertTrue(aLDrum.get(6).isGrace());
		assertFalse(aLDrum.get(7).isGrace());
	}

}
