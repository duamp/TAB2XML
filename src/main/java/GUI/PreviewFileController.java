package GUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fxmisc.richtext.CodeArea;

import converter.Converter;
import drawings.DrawDrumsNotes;
import drawings.DrawGuitarNotes;
import drawings.DrawSlides;
import drawings.Measure;
import drawings.DrawSlurs;

import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;


import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.ScorePartwise;
import music_player.PlayerController;
import music_player.XmlSequence;
import note_information.DrumInformation;
import note_information.*;

public class PreviewFileController extends Application {

	private MainViewController mvc;
	public Window window;
	public Highlighter highlighter;
	private ScorePartwise sp;
	private LinkedList<DrumInformation> aLDrums;
	private LinkedList<GuitarInformation> aLGuitar;
	private String instrument;
	public Converter converter;

	@FXML public CodeArea mainText;
	@FXML private AnchorPane ancorPane;
	@FXML TextField gotoMeasureField;
	@FXML private Pane pane;

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
	@FXML
	private void SettingsHandle() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/settings.fxml"));
		
	    Parent root = (Parent) loader.load();
        Stage stage = (Stage) this.openNewWindow(root, "Music player");
		PlayerController controller = loader.getController();
		
		
	       
    	// cannot be put in initialize() b/c stage/scene is not loaded yet
		stage.setOnCloseRequest(event ->{
			
			event.consume();
			controller.pause();
			
			ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.YES);
			ButtonType nSave = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
			ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "", save, nSave, cancel);
			alert.setHeaderText("Do you want to save changes?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.isPresent()) {
				
				if (result.get() == save && controller.saveSong()) {
					controller.closeSequencer();
					stage.close();
				}	
				
				else if (result.get() == nSave) {
					controller.closeSequencer();
					stage.close();
				}
				else alert.close();
				
			}
		});  
		
    	
	}
	Window openNewWindow(Parent root, String windowName) {
		Stage stage = new Stage();
		stage.setTitle(windowName);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(MainApp.STAGE);
		stage.setResizable(false);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return scene.getWindow();
	}

	@FXML
	private void handleGotoMeasure() {
		int measureNumber = Integer.parseInt(gotoMeasureField.getText() );
		System.out.println(measureNumber);
//		ancorPane.
	// need to finish go to measure implementation
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
	public void saveSSHandle() {
		System.out.println("Saving");
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/SaveSheetMusic.fxml"));
			root = loader.load();
			SaveSheetMusic controller = loader.getController();
			controller.setMainViewController(mvc, pane);
			Window convertWindow = mvc.openNewWindow(root, "Save MusicXML");
		} catch (IOException e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create new Window.", e);
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