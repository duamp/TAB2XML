package GUI;

import java.io.IOException;
import java.util.LinkedList;


import drawings.DrawDrumsNotes;
import drawings.DrawGuitarNotes;
import drawings.DrawSlides;
import drawings.Measure;
import drawings.DrawSlurs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.ScorePartwise;
import note_information.DrumInformation;
import note_information.*;

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
			DrawSlurs s = new DrawSlurs(aLGuitar, this.pane);
			s.drawNotesWithSlurs();
			DrawSlides sl = new DrawSlides(aLGuitar, this.pane);
			sl.drawSlides();

		} else {
			m = new Measure(4,this.pane, this.getMeasureNumber());
			DrawDrumsNotes d = new DrawDrumsNotes(pane,aLDrums);
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
				if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup() != null) {
					for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
						DrumInformation noteInformation;
						noteInformation = new DrumInformation(
								this.getDisplayedStepNote(i, j),
								this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
								this.getDisplayedStepOctave(i, j),
								getXorO(i, j),
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null, 
								i+1,
								sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType()
								);
						aLDrums.add(noteInformation);
					}
				}
			}
		} else {
			aLGuitar = new LinkedList<>();
			for(int i = 0; i < this.getMeasureNumber(); i++) {
				for(int j = 0; j < sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().size(); j++) {
					GuitarInformation noteInformation = new GuitarInformation(	
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getString(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getTechnical().getFret(),
							this.findDuration(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(), i, j),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getChord() != null,
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlurs(), 
							i + 1,
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getType(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getGrace(),
							sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotations().getSlides() 
							);

					aLGuitar.add(noteInformation);
				}
			}
		}
	}

	public void initialize() {}

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

	@FXML
	public void printSSHandle() {
		System.out.println("Printing");
		WritableImage wi = pane.snapshot(null, null);

		Printer p = Printer.getDefaultPrinter();
		PageLayout l = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

		ImageView iv = new ImageView(wi);

		System.out.println(l.getPrintableHeight());
		System.out.println(l.getPrintableWidth());

		double s = Math.min(l.getPrintableWidth()/wi.getWidth(), l.getPrintableHeight()/wi.getHeight());
		iv.getTransforms().add(new Scale(s,s));
		PrinterJob pj = PrinterJob.createPrinterJob();
		if(pj == null || !pj.showPageSetupDialog(pane.getScene().getWindow())) {
			System.out.println("Error Printing");
		}else {
			if(pj.printPage(iv)) {
				pj.endJob();
			}
		}
		System.out.println("Saved");
	}

	public String getXorO(int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead() == null) {
			return null;
		}
		return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getNotehead().getXorOtype();
	}

	public String getDisplayedStepNote(int i, int j) {
		if(sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched() != null) {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayStep();
		}
		return "R";
	}

	public int getDisplayedStepOctave(int i, int j) {
		if(	sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched() != null) {
			return sp.getParts().get(0).getMeasures().get(i).getNotesBeforeBackup().get(j).getUnpitched().getDisplayOctave();
		}
		return -1;
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}