package GUI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.fxmisc.richtext.CodeArea;

import drawPreview.DrawDrumsNotes;
import drawPreview.DrawGuitarNotes;
import drawPreview.Measure;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.ScorePartwise;
import models.measure.note.Note;
import models.Location;
import models.LocationDrums;
import models.LocationGuitar;

public class PreviewFileController extends Application {

	private MainViewController mvc;
	public Window window;
	public Highlighter highlighter;
	@FXML private Pane pane;
	private ScorePartwise sp;
	private LinkedList<LocationDrums> aLDrums;
	private LinkedList<LocationGuitar> aLGuitar;
	@FXML private AnchorPane ancorPane;
	private String instrument;

	public int getMeasureNumber() {
		return sp.getParts().get(0).getMeasures().size();
	}


	public void setMainViewController(MainViewController mvcInput) {
		mvc = mvcInput;
	}

	@FXML
	private void saveMXLButtonHandle() {
		mvc.saveMXLButtonHandle();
	}
	
	private int setUnitsInMeasure() {
		/* GET AMOUNT OF NOTES IN MEASURE */
		int unitsInMeasure = 0;
		int j =  sp.getParts().get(0).getMeasures().get(0).getNotesBeforeBackup().size();
		for(int i = 0; i < j; i++) {
			Note n = sp.getParts().get(0).getMeasures().get(0).getNotesBeforeBackup().get(i);
			if(n.getChord() == null) {
				unitsInMeasure+= n.getDuration();
			}
		}
		return unitsInMeasure;
	}


	public void update(ScorePartwise sp) throws IOException{
		instrument = sp.getPartList().getScoreParts().get(0).getPartName();
		this.sp = sp;
		createList();
		Measure m;
		if(instrument.equals("Guitar")) {
			m = new Measure(5,this.pane, this.getMeasureNumber(), false);
			DrawGuitarNotes g = new DrawGuitarNotes(pane, aLGuitar, setUnitsInMeasure());
			g.drawGuitarNotes();
		} else {
			m = new Measure(6,this.pane, this.getMeasureNumber(), true);
			DrawDrumsNotes d = new DrawDrumsNotes(pane, this.aLDrums, setUnitsInMeasure());
			d.drawDrumNotes();
		}
		
		m.drawMeasure();
	}

	public PreviewFileController ()  {}

	private void createList() {
		/*
		 * CREATE LINKED LIST OF NOTES FOLLOWED BY SPECIFIC Y-LOCATION
		 * 1. Get <PITCH> STRING (STARTING POINT) + FRET (TIMES REPEATS i.e., add 17/2)
		 */

		if(sp.getPartList().getScoreParts().get(0).getPartName() == "Drumset") {
			aLDrums = new LinkedList<>();
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					LocationDrums noteInformation = new LocationDrums(pane,
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayStep(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayOctave(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()
							);

					aLDrums.add(noteInformation);
				}
			}
		} else {
			aLGuitar = new LinkedList<>();
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					LocationGuitar noteInformation = new LocationGuitar(
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null);

					aLGuitar.add(noteInformation);
				}
			}
		}
	}

	public void initialize() {}

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}