package GUI;

import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import converter.Converter;
import custom_exceptions.TXMLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import models.ScorePartwise;

public class settings {
	private String fontType = "verdana"; //DEFAULT
	private int noteSize = 9; //DEFAULT
	private MainViewController mvc;
	
	@FXML ChoiceBox size;
	@FXML Button save;
	@FXML TextField font;
	
	@FXML
	private void saveSettingsHandle() throws IOException, TXMLException{
		this.fontType = font.getText();
		this.noteSize = ((int) size.getValue());
		System.out.println("test");
		System.out.println(this.fontType); 
		System.out.println(this.noteSize);
		this.mvc.setSettings(this);
		this.mvc.previewButtonHandle();
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
