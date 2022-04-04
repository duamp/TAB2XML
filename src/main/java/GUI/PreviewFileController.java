package GUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fxmisc.richtext.CodeArea;

import converter.Converter;
import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.DrawRepeats;
import drawMusic.DrawSlides;
import drawMusic.DrawSlurs;
import drawMusic.Measure;
import drawMusic.ParseDrumNotes;
import drawMusic.ParseGuitarNotes;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
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

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

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
	@FXML private Button button;
	@FXML private Button HomeBtn;

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

	public void startup(ScorePartwise sp, CodeArea mainText, Converter converter) throws IOException{
		this.mainText = mainText;
		this.converter = converter;
		instrument = sp.getPartList().getScoreParts().get(0).getPartName();
		this.sp = sp;
		this.update();
	}
	
	public void update() throws IOException{
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
			DrawRepeats rep = new DrawRepeats(aLGuitar, aLDrums, this.pane);
			rep.drawRepeatsGuitar();

		} else {
			m = new Measure(4,this.pane, this.getMeasureNumber());
			DrawDrumsNotes d = new DrawDrumsNotes(pane,aLDrums);
			m.drawMeasure(); //DRAWS MEASURES
			d.drawDrumNotes();
			DrawRepeats rep = new DrawRepeats(aLGuitar, aLDrums, this.pane);
			rep.drawRepeatsDrums();
		}
	}

	private void createList() {
		if(sp.getPartList().getScoreParts().get(0).getPartName() == "Drumset") {
			ParseDrumNotes d = new ParseDrumNotes(sp, converter);
			d.createList();
			aLDrums = d.getDrumInformation();
		} else {
			ParseGuitarNotes d = new ParseGuitarNotes(sp, converter);
			d.createList();
			aLGuitar = d.getGuitarInformation();
		}
	}

	public void initialize() {}

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
	@FXML
	public void BackHome() {
		  Stage stage = (Stage) HomeBtn.getScene().getWindow();
  	    stage.close();	
	}
	@FXML
	private void SettingsHandle() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/settings.fxml"));
		settings s = new settings();
		// need custom parameterized constructor
		loader.setControllerFactory(c -> {
			try {
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			return c;
		});
					
					
		/*
		 * 1. setNoteSize() -> DrawGuitarNotes || DrawDrumNotes
		 * 2. setFontType() -> DrawGuitarNotes || DrawDrumNotes
		 * 
		 *    Type of fonts: Make drop down menu
		 * 		1. Helvetica
		 * 		2. Calibri
		 * 		3. Futura
		 * 		4. Garamond
		 * 		5. Times New Roman
		 * 		6. Arial
		 * 		7. Cambria
		 * 		8. Verdana
		 * 3. Increase/Decrease Measure Size 
		 */

	    Parent root = (Parent) loader.load();
        Stage stage = (Stage) this.openNewWindow(root, "Settings");
		

		stage.setOnCloseRequest(event ->{
			stage.close();
			event.consume();

		});  
	}

	@FXML
	private void playMusic() throws MidiUnavailableException, InvalidMidiDataException {
		Sequencer sequencer = MidiSystem.getSequencer();
		XmlSequence sequence = new XmlSequence(converter.getScore());
		sequencer.setSequence(sequence.generateSequence());
		sequencer.open();
		sequencer.start();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}