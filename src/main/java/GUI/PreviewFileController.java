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

import drawings.DrawDrumsNotes;
import drawings.DrawGuitarNotes;
import drawings.Measure;
import drawings.Slurs;

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
import models.DrumInformation;
import models.GuitarInformation;
import models.GuitarInformation;

public class PreviewFileController extends Application {

	private MainViewController mvc;
	public Window window;
	public Highlighter highlighter;
	@FXML private Pane pane;
	private ScorePartwise sp;
	private LinkedList<DrumInformation> aLDrums;
	private LinkedList<GuitarInformation> aLGuitar;
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


	public void update(ScorePartwise sp) throws IOException{
		instrument = sp.getPartList().getScoreParts().get(0).getPartName();
		this.sp = sp;
		createList();
		Measure m;
		if(instrument.equals("Guitar")) {
			m = new Measure(5,this.pane, this.getMeasureNumber());
			DrawGuitarNotes g = new DrawGuitarNotes(pane, aLGuitar);
			m.drawMeasure(); //DRAWS MEASURES
			g.drawGuitarNotes(); //DRAWS NOTES + ADDS TO noteX && noteY to aLGuitar OBJECT
			Slurs s = new Slurs(aLGuitar, this.pane, sp);
			s.drawNotesWithSlurs();

		} else {
			m = new Measure(4,this.pane, this.getMeasureNumber());
			DrawDrumsNotes d = new DrawDrumsNotes(pane,aLDrums, sp);
			m.drawMeasure(); //DRAWS MEASURES
			d.drawDrumNotes();
		}

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
					DrumInformation noteInformation = new DrumInformation(
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayStep(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayOctave(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null
							);

					aLDrums.add(noteInformation);
				}
			}
		} else {
			aLGuitar = new LinkedList<>();
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					GuitarInformation noteInformation;
					if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration() != null) {
						//(STRING, FRET, DURATION, CHORD, SLUR, ORIGINAL NOTEX, ORIGINAL NOTEY)
						noteInformation = new GuitarInformation(
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getDuration(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlurs(), 
								0, 
								0, 
								i + 1,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()
								);
					} else {
						noteInformation = new GuitarInformation(	
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
								this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlurs(), 
								0,
								0,
								i + 1,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()
								);
					}
					aLGuitar.add(noteInformation);
				}
			}
		}
	}

	public void initialize() {}

	public int findDuration(String type) {
		switch (type){
		case "16th":
			return 16;
		}
		return 0;
	}

	public void printSSHandle() {
		System.out.println("works");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}