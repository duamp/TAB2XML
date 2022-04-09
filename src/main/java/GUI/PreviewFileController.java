package GUI;

import java.awt.BasicStroke;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;

import org.fxmisc.richtext.CodeArea;

import GUI.settingsGUI;
import converter.Converter;
import converter.measure.TabMeasure;
import drawMusic.DrawBars;
import drawMusic.DrawDrumsNotes;
import drawMusic.DrawGuitarNotes;
import drawMusic.DrawRepeats;
import drawMusic.DrawSlides;
import drawMusic.DrawSlurs;
import drawMusic.DrawTiming;
import drawMusic.Measure;
import drawMusic.ParseDrumNotes;
import drawMusic.ParseGuitarNotes;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import javafx.fxml.FXMLLoader;


import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.ScorePartwise;
import music_player.PlayerController;
import music_player.XmlSequence;
import note_information.DrumInformation;
import utility.Range;
import note_information.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.plaf.basic.BasicTextUI.BasicHighlighter;

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
	@FXML Button HomeBtn;
	private boolean check = false;
    Rectangle r = null;

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
		//int measureNumber = Integer.parseInt(gotoMeasureField.getText() );
		int measureNumber = Integer.parseInt(gotoMeasureField.getText());
		System.out.println(measureNumber);
		
		
		if (!goToMeasure(measureNumber)) {
			

            		Alert alert = new Alert(Alert.AlertType.ERROR);
            		
               alert.setContentText("Measure " + measureNumber + " could not be found.");
               
               
            alert.setHeaderText(null);
            
            	alert.show();
            	
          
            	
    } else {
            highlight(measureNumber);
    }
    
    // ancorPane.
    //.....
    // need to finish go to measure implementation
}
		
		
		
		//.....
	

	
	
	
	
	
	
	private boolean goToMeasure(int measureCount) {
		
		
        TabMeasure measure = converter.getScore().getMeasure(measureCount);
       
        System.out.println(measure);
        if (measure == null)
        	
                return false;
        
        else
        	
                return true;
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
			DrawGuitarNotes g = new DrawGuitarNotes(pane, aLGuitar, this.mvc.settings);
			m.drawMeasure(); //DRAWS MEASURES
			g.drawGuitarNotes(); //DRAWS NOTES + ADDS TO noteX && noteY to aLGuitar OBJECT
			DrawSlurs s = new DrawSlurs(aLGuitar, aLDrums, this.pane);
			s.drawSlursGuitar();
			DrawSlides sl = new DrawSlides(aLGuitar, this.pane);
			sl.drawSlides();
			DrawRepeats rep = new DrawRepeats(aLGuitar, aLDrums, this.pane);
			rep.drawRepeatsGuitar();
			DrawBars db = new DrawBars(aLGuitar, pane);
			db.drawbars();
			
		} else {
			m = new Measure(4,this.pane, this.getMeasureNumber());
			DrawDrumsNotes d = new DrawDrumsNotes(pane,aLDrums, this.mvc.settings);
			m.drawMeasure(); //DRAWS MEASURES
			d.drawDrumNotes();
			DrawRepeats rep = new DrawRepeats(aLGuitar, aLDrums, this.pane);
			DrawSlurs s = new DrawSlurs(aLGuitar, aLDrums, this.pane);
			s.drawSlursDrums();
			rep.drawRepeatsDrums();
		}
		DrawTiming t = new DrawTiming(aLGuitar, aLDrums, this.pane, sp);
		t.drawTiming();
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

	public void initialize() {
		
		
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
	@FXML
	public void BackHome() {
		Stage stage = (Stage) HomeBtn.getScene().getWindow();
		stage.close();	
	}
	@FXML
	private void SettingsHandle() {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/settingsGUI.fxml"));
			root = loader.load();
			settingsGUI controller = loader.getController();
			controller.setMainViewController(this.mvc, this);
			Window settingsWindow = this.openNewWindow(root, "Settings");
			Stage stage = (Stage) this.mainText.getScene().getWindow();
		}catch(IOException e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create new Window.", e);
		}
//		Stage stage2 = (Stage) HomeBtn.getScene().getWindow();
//  	    stage2.close();	

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
		
		
	}

	@FXML
	private void playMusic() throws MidiUnavailableException, InvalidMidiDataException {
		Sequencer sequencer = MidiSystem.getSequencer();
		XmlSequence sequence = new XmlSequence(converter.getScore());
		sequencer.setSequence(sequence.generateSequence());
		sequencer.open();
		sequencer.start();
	}

	private void highlight(int index) {
		
		 if (check) {
             pane.getChildren().remove(r);
     }
		 
		 
//		int x = aLGuitar.get(index).getNoteX() - 57;
//		System.out.println("x " + x);
//		int y = aLGuitar.get(index).getNoteY() - 70;
//		System.out.println("y " + y);
//		 r = new Rectangle(x,y,10,10);
//		
//		r.setFill(c);
//		r.setOpacity(0.3);
//		pane.getChildren().add(r);
//		check = true;
		 
		 double opp = 0.5; // opacity
		 Color c = Color.CYAN;
		 
		 if (index == 1) {
             pane.getChildren().remove(check);
             r = new Rectangle(10, 1, 300, 90);
             r.setFill(c);
             r.setOpacity(opp);
             pane.getChildren().add(r);
             check = true;
     } else if (index == 2) {
             pane.getChildren().remove(check);
             r = new Rectangle(310, 1, 300, 90);
             r.setFill(c);
             r.setOpacity(opp);
             pane.getChildren().add(r);
             check = true;
     } else if (index == 3) {
         r = new Rectangle(610, 1, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 4) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 5) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 6) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 7) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 8) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 9) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 10) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 11) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 12) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 13) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 14) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 15) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         TabMeasure measure = converter.getScore().getMeasure(index);
         List<Range> linePositions = measure.getRanges();
         int pos = linePositions.get(0).getStart();
         mainText.moveTo(pos);
         mainText.requestFollowCaret();
         mainText.requestFocus();
         check = true;
 } else if (index == 16) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 1000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 17) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 1000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 18) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 1000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         mainText.moveTo(index);
         
         check = true;
 } else if (index == 19) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 1200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 20) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 1200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 21) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 1200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 22) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 1400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 23) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 1400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 24) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 1400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 25) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 1600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 26) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 1600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 27) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 1600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 28) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 1800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 29) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 1800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 30) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 1800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 31) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 2000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 32) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 2000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 33) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 2000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 34) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 2200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 35) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 2200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 36) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 2200, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 38) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 2400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 39) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 2400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 40) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 2400, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 41) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 2600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 42) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 2600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 43) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 2600, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
 
         check = true;
 }else if (index == 44) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 2800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 45) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 2800, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 46) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 2800, 300, 90);                 
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r); 
         check = true;
 }else if (index == 47) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 3000, 300, 90);                  
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 48) {
         pane.getChildren().remove(check);
         r = new Rectangle(310, 3000, 300, 90);                 
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 } else if (index == 49) {
         pane.getChildren().remove(check);
         r = new Rectangle(610, 3000, 300, 90);
         
         
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 }else if (index == 50) {
         pane.getChildren().remove(check);
         r = new Rectangle(10, 3200, 300, 90);          
         r.setFill(c);
         r.setOpacity(opp);
         pane.getChildren().add(r);
         check = true;
 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	}
}