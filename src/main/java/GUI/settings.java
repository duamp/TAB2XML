package GUI;

import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class settings {
	private String fontType = "verdana"; //DEFAULT
	private int noteSize = 9; //DEFAULT
	public settings() {
	}
	@FXML ChoiceBox size;
	@FXML Button save;
	@FXML TextField font;

	
	@FXML
	private void saveSettingsHandle() throws IOException{
//		this.fontType = font.getText();
//		this.noteSize = ((int) size.getValue());
		System.out.println("test");
		System.out.println(this.fontType); 
		System.out.println(this.noteSize);
	}
	public void setFontType(String fontType) {
		this.fontType = fontType;
		}
	public void setNoteSize(int noteSize) {
		this.noteSize = noteSize;
		}
	public String getFontType() {
		return fontType;
	}
	public int getNoteSize() {
		return noteSize;
	}
}
