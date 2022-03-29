package GUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fxmisc.richtext.CodeArea;

import converter.Converter;
import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
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
		XmlSequence sequence = new XmlSequence(mainText.getText(), converter);

		// need custom parameterized constructor
					loader.setControllerFactory(c -> {
						try {
								return new PlayerController(sequence);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						return c;
					});
					
	    Parent root = (Parent) loader.load();
        Stage stage = (Stage) this.openNewWindow(root, "Settings");
		PlayerController controller = loader.getController();
		
    	// cannot be put in initialize() b/c stage/scene is not loaded yet
		stage.setOnCloseRequest(event ->{
			
			event.consume();
			controller.pause();
			
			ButtonType update = new ButtonType("Save", ButtonBar.ButtonData.YES);
//			ButtonType nSave = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
//			ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "", update);
			alert.setHeaderText("Do you want to save changes?");

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

		} else {
			m = new Measure(4,this.pane, this.getMeasureNumber());
			DrawDrumsNotes d = new DrawDrumsNotes(pane,aLDrums);
			m.drawMeasure(); //DRAWS MEASURES
			d.drawDrumNotes();
		}

	}

	private void createList() {
		if(sp.getPartList().getScoreParts().get(0).getPartName() == "Drumset") {
			ParseDrumNotes d = new ParseDrumNotes(sp);
			d.createList();
			aLDrums = d.getDrumInformation();
		} else {
			ParseGuitarNotes d = new ParseGuitarNotes(sp);
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}