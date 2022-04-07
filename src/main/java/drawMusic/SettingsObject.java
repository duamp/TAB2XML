package drawMusic;

public class SettingsObject {
	private String fontType = null;
	private int noteSize;
	
	public SettingsObject() {
		// defaults
		this.fontType = "verdana";
		this.noteSize = 9;
	}
	public SettingsObject(String font, int size) {
		this.fontType = font;
		this.noteSize = size;
	}
	public String getFontType() {
		return fontType;
	}
	public void setFontType(String fontType) {
		this.fontType = fontType;
	}
	public int getNoteSize() {
		return noteSize;
	}
	public void setNoteSize(int noteSize) {
		this.noteSize = noteSize;
	}
}
