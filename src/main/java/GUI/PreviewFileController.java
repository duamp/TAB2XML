package GUI;

import java.io.File;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PreviewFileController extends Application {
	
	public File saveFile;
    private MainViewController mvc;
	public Highlighter highlighter;
	
	Image image;
	@FXML TextField gotoMeasureField;
	@FXML Button goToline;

	public PreviewFileController() {}

	@FXML 
	public void initialize() {
//		image.setParagraphGraphicFactory(LineNumberFactory.get(image));
	}

    public void setMainViewController(MainViewController mvcInput) {
    	mvc = mvcInput;
    }
    
    public void update() {
//		image.replaceText(mvc.converter.getMusicXML());
//		image.moveTo(0);
//		image.requestFollowCaret();
//        image.requestFocus();
	// implement adding the dots here
    }
    
	@FXML
	private void saveMXLButtonHandle() {
		mvc.saveMXLButtonHandle();
	}

	//TODO add go to line button
//	@FXML
//	private void handleGotoMeasure() {
//		int measureNumber = Integer.parseInt(gotoMeasureField.getText() );
//		if (!goToMeasure(measureNumber)) {
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//			alert.setContentText("Measure " + measureNumber + " could not be found.");
//			alert.setHeaderText(null);
//			alert.show();
//		}
//	}

//    private boolean goToMeasure(int measureCount) {
//    	//Pattern textBreakPattern = Pattern.compile("((\\R|^)[ ]*(?=\\R)){2,}|^|$");
//    	Pattern mxlMeasurePattern = Pattern.compile("<measure number=\"" + measureCount + "\">");
//        Matcher mxlMeasureMatcher = mxlMeasurePattern.matcher(image.getText());
//        
//        if (mxlMeasureMatcher.find()) {
//        	int pos = mxlMeasureMatcher.start();
//        	image.moveTo(pos);
//        	image.requestFollowCaret();
//        	Pattern newLinePattern = Pattern.compile("\\R");
//        	Matcher newLineMatcher = newLinePattern.matcher(image.getText().substring(pos));
//        	for (int i = 0; i < 30; i++) newLineMatcher.find();
//        	int endPos = newLineMatcher.start();
//        	image.moveTo(pos+endPos);
//        	image.requestFollowCaret();
//        	//image.moveTo(pos);
//            image.requestFocus();
//            return true;
//            }
//        else return false;        
//    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.image = new Image("/image_assets/MeasureWithTrebeclef.png");
		ImageView image = new ImageView(this.image);
	    Pane root = new Pane();
	    root.getChildren().add(image);
	    Scene scene = new Scene(root, 300, 250);

	    primaryStage.setTitle("Hello World!");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    System.out.println("displayed image");
		
		
	}
}