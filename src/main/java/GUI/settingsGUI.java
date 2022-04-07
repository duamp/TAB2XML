package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import custom_exceptions.TXMLException;
import drawMusic.SettingsObject;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class settingsGUI {
	SettingsObject settings = new SettingsObject();
	private MainViewController mvc;
	private PreviewFileController pvc;
	@FXML ChoiceBox size;
	@FXML Button save;
	@FXML TextField font;
	
	@FXML
	private void saveSettingsHandle() throws IOException, TXMLException{
		SettingsObject so = new SettingsObject(font.getText(), Integer.valueOf(font.getText()));
		this.mvc.setSettings(so);
		System.out.println(this.mvc.getSettings().getFontType());
		Stage stage = (Stage) pvc.HomeBtn.getScene().getWindow();
		stage.close();	
//		((Stage) pvc.mainText.getScene().getWindow()).close();
	}

	public void setMainViewController(MainViewController mvc, PreviewFileController pvc) {
		this.mvc = mvc;
		this.pvc = pvc;
		
	}
	
}
