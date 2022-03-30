package musicPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.jfugue.pattern.Pattern;
import org.junit.jupiter.api.Test;


import converter.Score;
import music_player.Instrument;
import music_player.PlayerController;
import music_player.StaccatoHelper;
import music_player.XmlSequence;

import utility.MusicXMLCreator;
import javax.sound.midi.Sequence;


public class MusicPlayerTest {

	@Test
	public void testPlayerController(){
		
		try {
			PlayerController controller = new PlayerController(null);
			fail("expected exception due to invalid sequence");
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Pattern generatePattern(String tab) {
		
		Score score = new Score(tab);
		XmlSequence sequence = new XmlSequence(score);
		sequence.generateSequence();
		return sequence.getPattern();
	}
	
	@Test 
	public static void testBasicDrumSequence() {
			String tab = "  4/4\n"
					+ "C |X---------------|----------------|----------------|----------------|\n"
					+ "H |--X-X-X-X-X-X-X-|X-x-X-X-X-X-X-X-|x-x-X-X-X-X-X-X-|x-x-X-X-X-X-X---|\n"
					+ "S |----o-------o---|----o-------o---|----o-------o---|----o-------o-fo|\n"
					+ "B |o--o---o-ooo-o--|oo-o---o-ooo-o--|oo-o---o-ooo-o--|oo-o---o-ooo-o--|\n"
					+ "  |1 +a2 +a3e+a4e+ |1e+a2 +a3e+a4e+ |1e+a2 +a3e+a4e+ |1e+a2 +a3e+a4e+a|\n"
					+ "\n"
					+ "C |X---------------|----------------|----------------|----------------|\n"
					+ "H |--X-X-X-X-X-X-X-|x-x-X-X-X-X-X-X-|x-x-X-X-X-X-X-X-|x-x-X-X-X-X-X---|\n"
					+ "S |----o-------o---|----o-------o---|----o-------o---|----o-o--g--o---|\n"
					+ "T |----------------|----------------|----------------|--------------fo|\n"
					+ "B |o--o---o-ooo-o--|oo-o---o-ooo-o--|oo-o---o-ooo-o--|oo-o---o---o-o--|\n"
					+ "  |1 +a2 +a3e+a4e+ |1e+a2 +a3e+a4e+ |1e+a2 +a3e+a4e+ |1e+a2 +a3e+a4e+a|\n";
		
			Pattern pattern = generatePattern(tab);
			
			assertTrue(pattern.toString().equals("V9 [CRASH_CYMBAL_1]i+[BASS_DRUM]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [ELECTRIC_SNARE]i [ELECTRIC_SNARE]s [ELECTRIC_SNARE]s [CRASH_CYMBAL_1]i+[BASS_DRUM]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]i [CLOSED_HI_HAT]s+[BASS_DRUM]s [BASS_DRUM]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [CLOSED_HI_HAT]s [ELECTRIC_SNARE]s [CLOSED_HI_HAT]s [BASS_DRUM]s [CLOSED_HI_HAT]s+[ELECTRIC_SNARE]s [BASS_DRUM]s [HI_MID_TOM]i [HI_MID_TOM]s [HI_MID_TOM]s"));
			
	
			
			
	}
	
	// need to fix
	@Test
	public void testGuitarDefaultSignature() {
		String tab = "e|-------5-7-----7-|-8-----8-2-----2-|-0---------0-----|-----------------|\n"
				+ "B|-----5-----5-----|---5-------3-----|---1---1-----1---|-0-1-1-----------|\n"
				+ "G|---5---------5---|-----5-------2---|-----2---------2-|-0-2-2---2-------|\n"
				+ "D|-7-------6-------|-5-------4-------|-3---------------|-----------------|\n"
				+ "A|-----------------|-----------------|-----------------|-2-0-0---0--/8-7-|\n"
				+ "E|-----------------|-----------------|-----------------|-----------------|\n"
				+ "\n"
				+ "e|---------7-----7-|-8-----8-2-----2-|-0---------0-----|-----------------|\n"
				+ "B|-------5---5-----|---5-------3-----|---1---1-----1---|-0-1-1-----------|\n"
				+ "G|-----5-------5---|-----5-------2---|-----2---------2-|-0-2-2-----------|\n"
				+ "D|---7-----6-------|-5-------4-------|-3---------------|-----------------|\n"
				+ "A|-0---------------|-----------------|-----------------|-2-0-0-------0-2-|\n"
				+ "E|-----------------|-----------------|-----------------|-----------------|";
	
		Pattern pattern = generatePattern(tab);

		assertEquals(pattern.toString(),"I[Guitar] V0 A3ia90 C4ia90 E4ia90 A4ia90 B4ia90 @0.5 G#3ia90 E4ia90 C4ia90 B4ia90 C5ia90 @1.0 G3ia90 E4ia90 C4ia90 C5ia90 F#4ia90 @1.5 F#3ia90 D4ia90 A3ia90 F#4ia90 E4ia90 @2.0 F3ia90 C4ia90 A3ia90 C4qa90 E4ia90 C4ia90 A3ia90 B3ia90 @3.0 G3ia90 @3.0 B2ia90 C4ia90 @3.125 A3ia90 @3.125 A2ia90 C4qa90 @3.25 A3qa90 @3.25 A2qa90 A3qa90 @3.5 A2qa90 F3ia90 E3ia90 A2ia90 A3ia90 C4ia90 E4ia90 B4ia90 @4.5 G#3ia90 E4ia90 C4ia90 B4ia90 C5ia90 @5.0 G3ia90 E4ia90 C4ia90 C5ia90 F#4ia90 @5.5 F#3ia90 D4ia90 A3ia90 F#4ia90 E4ia90 @6.0 F3ia90 C4ia90 A3ia90 C4qa90 E4ia90 C4ia90 A3ia90 B3ia90 @7.0 G3ia90 @7.0 B2ia90 C4ia90 @7.125 A3ia90 @7.125 A2ia90 C4ha90 @7.25 A3ha90 @7.25 A2ha90 A2ia90 B2ia90");

		
	}

   
	@Test
	public static void testUndefinedInstrument() {
	
		// does not exist in list of suported instruments
		assertNull(StaccatoHelper.getInstrument("P1-1799"));
		
		
	}
	

	@Test
	public static void testRepeat() {
		String tab = "   7/4\n"
				+ "  REPEAT 8x\n"
				+ "G---4-----------|\n"
				+ "D----4----------|\n"
				+ "A-2---2-----2-5-|\n"
				+ "E-------2-5-----|\n"
				+ "\n"
				+ "G---------------|-----------------------------|--4-----------|\n"
				+ "D-4-4-4-------4-|-3---2-----------------------|---4----------|\n"
				+ "A------4----4---|-------2---------0---2---5---|2---2-----2-5-|\n"
				+ "E-------2-5-----|---------0---3---------------|------2-5-----|";
		Pattern pattern = generatePattern(tab);
		
		Pattern repeatPattern = new Pattern("B1q B2i F#2i B1q F#1q A1q B1q D2q").repeat(8);
		Pattern patternAfter = new Pattern("F#2q F#2q F#2i C#2i F#1q A1q C#2q F#2q F2q E2i B1i E1q G1q A1q B1q D2q B1q B2i F#2i B1q F#1q A1q B1q D2q");
		
		assertEquals(pattern.toString(), new Pattern(repeatPattern, patternAfter).toString());
	}
}


