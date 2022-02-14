package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PreviewFileController extends Application {
	
	public File saveFile;
    private MainViewController mvc;
	public Highlighter highlighter;
	private ArrayList<String> Notes;
	private String XmlString;
	private int measureNumber;

	@FXML public CodeArea mxlText;
	@FXML TextField gotoMeasureField;
	@FXML Button goToline;

	public PreviewFileController() {}

	@FXML 
	public void initialize() {
		mxlText.setParagraphGraphicFactory(LineNumberFactory.get(mxlText));
	}

    public void setMainViewController(MainViewController mvcInput) {
    	mvc = mvcInput;
    }
    
    public void updateNote() {
    	XmlString = mvc.converter.getMusicXML();
    	System.out.println("Original: " + XmlString);
    	getInformation();
    	System.out.println("Edited: " + XmlString);
		mxlText.replaceText(mvc.converter.getNote());
		mxlText.moveTo(0);
		mxlText.requestFollowCaret();
        mxlText.requestFocus();
	}
    
    public void getInformation() {
    	Scanner s = new Scanner(this.XmlString);
    	XmlString = "";
    	while(s.hasNext()) {
    		String info = s.next();
    		if(info.equals("<pitch>")){
    			String stepSequence = s.next();
    			String note = stepSequence.substring(6,7);
    			XmlString += (note);
    		}
    	}
    }
    
	@FXML
	private void saveMXLButtonHandle() {
		mvc.saveMXLButtonHandle();
	}

	//TODO add go to line button
	@FXML
	private void handleGotoMeasure() {
		int measureNumber = Integer.parseInt(gotoMeasureField.getText() );
		if (!goToMeasure(measureNumber)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Measure " + measureNumber + " could not be found.");
			alert.setHeaderText(null);
			alert.show();
		}
	}

    private boolean goToMeasure(int measureCount) {
    	//Pattern textBreakPattern = Pattern.compile("((\\R|^)[ ]*(?=\\R)){2,}|^|$");
    	Pattern mxlMeasurePattern = Pattern.compile("<measure number=\"" + measureCount + "\">");
        Matcher mxlMeasureMatcher = mxlMeasurePattern.matcher(mxlText.getText());
        
        if (mxlMeasureMatcher.find()) {
        	int pos = mxlMeasureMatcher.start();
        	mxlText.moveTo(pos);
        	mxlText.requestFollowCaret();
        	Pattern newLinePattern = Pattern.compile("\\R");
        	Matcher newLineMatcher = newLinePattern.matcher(mxlText.getText().substring(pos));
        	for (int i = 0; i < 30; i++) newLineMatcher.find();
        	int endPos = newLineMatcher.start();
        	mxlText.moveTo(pos+endPos);
        	mxlText.requestFollowCaret();
        	//mxlText.moveTo(pos);
            mxlText.requestFocus();
            return true;
            }
        else return false;        
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {}
}