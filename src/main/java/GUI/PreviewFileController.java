package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.fxmisc.richtext.CodeArea;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class PreviewFileController extends JFrame {

    private MainViewController mvc;
	public Highlighter highlighter;
	private ArrayList<String> notes;
	private String XmlString;
	private double measureNumber;
	private int octaveNumber;

    @FXML public CodeArea mxlText; 


    @FXML private ImageView imageView;

    public void updateNote() {
    	XmlString = mvc.converter.getMusicXML();
    	System.out.println("Original: " + XmlString);
    	getInformation();
    	System.out.println("ArrayList of Notes: " + notes);
    	System.out.println("Measure: " + ((int) this.measureNumber - 1));
		mxlText.replaceText(mvc.converter.getNote());
		mxlText.moveTo(0);
		mxlText.requestFollowCaret();
        mxlText.requestFocus();
	}

    public void getInformation() {
    	Scanner s = new Scanner(this.XmlString);
    	ArrayList<String> aL = new ArrayList<>();
    	XmlString = "";
    	while(s.hasNext()) {
    		String info = s.next();
    		
    		/*
    		 * Look for <pitch> then take note located in next string at position [6,7)
    		 * Value stored in arrayList
    		 */
    		
    		if(info.equals("<pitch>")){
    			String stepSequence = s.next();
    			String note = stepSequence.substring(6,7);
    			aL.add(note);
    			XmlString += (note);
    		}
    		
    		/*
    		 * Measure is present twice in XML for every measure,
    		 * Every iteration adds 0.5 to result in 1 measure for every two occurrences.
    		 */
    		if(info.contains("measure")) {
    			this.measureNumber += 0.5;
    		}
    		
    	}
    	notes = aL;
    	s.close();
    }
    
    public ArrayList<String> getNotes(){
    	return this.notes;
    }

    
    public int getMeasureNumber() {
    	return (int) this.measureNumber; //cast required, see getInformation() for details
    }
    
	@FXML
	private void saveMXLButtonHandle() {
		mvc.saveMXLButtonHandle();
	}

    @SuppressWarnings("unused")
	public PreviewFileController (MainViewController mvcInput) throws IOException {
    	this.mvc = mvcInput;
      	setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
      	
      	XmlString = mvc.converter.getMusicXML();
    	getInformation();
    	System.out.println("ArrayList of Notes: " + notes);
    	System.out.println("Measure: " + this.measureNumber);

    	int numberOfTabs = (int)this.measureNumber - 1; //change to real tabs value later

    	JLabel trebeclef = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png")));

    	add(trebeclef);
    	
    	for(int i = 0; i<numberOfTabs;i++) {
    		add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/Measure.png"))));
    	}
    	
    	
// 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sheet Music");
		pack();
		setVisible(true);
		
		
//		implement this exception later
		if(1==0) {
			IOException e = new IOException();
			throw e;
		}
    }
    
    public void setMainViewController() {
    	
    }
    
    public void initialize() {
		}
    
    @FXML
    private void saveButtonClicked() {
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
    }

    @FXML
    private void cancelButtonClicked()  {
    	mvc.convertWindow.hide();
    }

}