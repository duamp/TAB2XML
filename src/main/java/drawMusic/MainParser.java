package drawMusic;

import models.ScorePartwise;

public class MainParser {
	protected ScorePartwise sp;

	public MainParser(ScorePartwise sp) {
		this.sp = sp;
	}
	
	public int findDuration(String type, int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration() == null) {
			switch (type){
			case "16th":
				return 16;
			case "8":
				return 8;
			}
			return 0;
		} else {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration();
		}

	}
	
	public int getMeasureNumber() {
		return sp.getParts().get(0).getMeasures().size();
	}
}
