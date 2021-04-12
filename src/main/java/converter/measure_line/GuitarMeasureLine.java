package converter.measure_line;

import GUI.TabInput;
import converter.Instrument;
import converter.Score;
import converter.note.GuitarNote;
import converter.note.Note;

import java.util.*;

public class GuitarMeasureLine extends MeasureLine {
    public static List<String> NAME_LIST = createLineNameSet();
    public static List<String> OCTAVE_LIST = createOctaveList();
    public static String COMPONENT = "[0-9hHpPsS\\/\\\\]";
    public static String INSIDES_PATTERN_SPECIAL_CASE = "$a"; // doesnt match anything

    private static ArrayList<String> createOctaveList() {
        String[] names = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(Arrays.asList(names));
        return nameList;
    }

    public GuitarMeasureLine(String line, String[] nameAndPosition, int position) {
        super(line, nameAndPosition, position);
        this.instrument = Instrument.GUITAR;
        this.noteList = this.createNoteList(this.line, position);
    }

    protected static List<String> createLineNameSet() {
        String[] names = GuitarNote.KEY_LIST;
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(Arrays.asList(names));
        for (String name : names) {
            nameList.add(name.toLowerCase());
        }
        return nameList;
    }


    public List<HashMap<String,String>> validate() {
        List<HashMap<String, String>> result = new ArrayList<>(super.validate());

        if (!isGuitarName(this.name)) {
            HashMap<String, String> response = new HashMap<>();
            if (isDrumName(this.name))
                response.put("message", "A Guitar measure line is expected here.");
            else
                response.put("message", "Invalid measure line.");
            response.put("positions", "["+this.namePosition+","+(this.position+this.line.length())+"]");
            int priority = 1;
            response.put("priority", ""+priority);
            if (TabInput.ERROR_SENSITIVITY>=priority)
                result.add(response);
        }

        for (HashMap<String, String> error : result) {
            if (Integer.parseInt(error.get("priority")) <= Score.CRITICAL_ERROR_CUTOFF) {
                return result;
            }
        }
        for (Note note : this.noteList)
            result.addAll(note.validate());

        return result;
    }
}
