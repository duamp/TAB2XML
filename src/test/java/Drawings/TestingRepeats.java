package Drawings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import drawMusic.DrawDrumsNotes;
import drawMusic.DrawRepeats;
import drawMusic.Measure;
import javafx.scene.layout.Pane;
import note_information.DrumInformation;

class TestingRepeats {

	//ONLY WORKS FOR DRUMS THEREFORE ONLY TESTING FOR DRUMS
	@Test
	void testNumberOfRepeats() {
		Pane p = new Pane();
		Measure m = new Measure(5, p, 1); 
		m.drawMeasure();
		LinkedList<DrumInformation> aLDrum = new LinkedList<DrumInformation>();

		//REPEAT MEASURE 1 3 TIMES
		DrumInformation l1 = new DrumInformation("C", 8, 4, "x", false, 1, "Eight", 3, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l2 = new DrumInformation("D", 8, 4, "x", false, 1, "Eight", 3, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l3 = new DrumInformation("E", 8, 4, "x", false, 1, "Eight", 3, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l4 = new DrumInformation("F", 8, 4, "x", false, 1, "Eight", 3, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l5 = new DrumInformation("G", 8, 4, "x", false, 2, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l6 = new DrumInformation("A", 8, 4, "x", false, 2, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)
		DrumInformation l7 = new DrumInformation("B", 8, 4, "x", false, 2, "Eight", 0, null, null); //NOTE - DURATION - OCTAVE - TYPE - CHORD - MEASURE - DURATION(STRING)


		aLDrum.add(l1);	//ADD RANDOM NOTE 1
		aLDrum.add(l2);	//ADD RANDOM NOTE 2
		aLDrum.add(l3);	//ADD RANDOM NOTE 3
		aLDrum.add(l4);	//ADD RANDOM NOTE 4
		aLDrum.add(l5);	//ADD RANDOM NOTE 4
		aLDrum.add(l6);	//ADD RANDOM NOTE 4
		aLDrum.add(l7);	//ADD RANDOM NOTE 4


		DrawDrumsNotes d = new DrawDrumsNotes(p, aLDrum, null);
		d.drawDrumNotes();

		DrawRepeats rep = new DrawRepeats(null, aLDrum, p);
		rep.drawRepeatsDrums();

		assertEquals(4, rep.getNor());

	}

}
