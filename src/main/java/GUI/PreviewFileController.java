package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import javax.swing.JPanel;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import models.ScorePartwise;
import models.Location;
import models.LocationGuitar;

public class PreviewFileController extends JPanel {

	private MainViewController mvc;
	public Highlighter highlighter;
	private ScorePartwise sp;
	private LinkedList<LocationGuitar> aL;

	@FXML private ImageView imageView;

	
	public File saveFile;
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

    public void update() {
		mxlText.replaceText(mvc.converter.getMusicXML());
		mxlText.moveTo(0);
		mxlText.requestFollowCaret();
        mxlText.requestFocus();
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
        else{
        	return false;        
        }
    }
	
	public int getMeasureNumber() {
		return sp.getParts().get(0).getMeasures().size();
	}


	public void update(ScorePartwise sp) throws IOException{
		this.sp = sp;
		createList();
		createJFrame(new JFrame());
	}

	private void createList() {
		/*
		 * CREATE LINKED LIST OF NOTES FOLLOWED BY SPECIFIC Y-LOCATION
		 * 1. Get <PITCH> STRING (STARTING POINT) + FRET (TIMES REPEATS i.e., add 17/2)
		 */

		aL = new LinkedList<>();
		if(sp.getPartList().getScoreParts().get(0).getPartName() == "Drumset") {
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(0).getInstrument().getId(); //location in Y-DIRECTION
					sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(0).getDuration(); //duration
				}
			}
		} else {
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					LocationGuitar noteInformation = new LocationGuitar(
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null);

					aL.add(noteInformation);
				}
			}
		}
	}

	@FXML
	private void createJFrame(JFrame f) {
		f.add(new Draw(this.getMeasureNumber(), aL));
		f.setPreferredSize(new Dimension(600, 300));
		f.setTitle(this.sp.getPartList().getScoreParts().get(0).getPartName() + " Sheet Music");
		f.getContentPane().setBackground(Color.white);
		f.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

//    @FXML
//    private void saveButtonClicked() {
//        if (!titleField.getText().isBlank())
//            Settings.getInstance().title = titleField.getText();
//        if (!artistField.getText().isBlank())
//        	Settings.getInstance().artist = artistField.getText();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save As");
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MusicXML files", "*.musicxml", "*.xml", "*.mxl");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        File initialDir = new File(Settings.getInstance().outputFolder);
//        String initialName = null;
//        if (!fileNameField.getText().isBlank() && fileNameField.getText().length()<50)
//            initialName = fileNameField.getText().strip();
//
//        if (mvc.saveFile != null) {
//            if (initialName == null) {
//                String name = mvc.saveFile.getName();
//                if(name.contains("."))
//                    name = name.substring(0, name.lastIndexOf('.'));
//                initialName = name;
//            }
//            File parentDir = new File(mvc.saveFile.getParent());
//            if (parentDir.exists())
//                initialDir = parentDir;
//        }
//        if (initialName != null)
//            fileChooser.setInitialFileName(initialName);
//
//        if (!(initialDir.exists() && initialDir.canRead()))
//            initialDir = new File(System.getProperty("user.home"));
//
//        fileChooser.setInitialDirectory(initialDir);
//
//        File file = fileChooser.showSaveDialog(convertWindow);
//
//        if (file != null) {
//            mvc.converter.saveMusicXMLFile(file);
//            mvc.saveFile = file;
//            cancelButtonClicked();
//        }
//    }