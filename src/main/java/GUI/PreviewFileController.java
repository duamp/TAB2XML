package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


import org.fxmisc.richtext.CodeArea;


import javax.swing.JPanel;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import models.ScorePartwise;
import models.Location;
import models.LocationGuitar;

public class PreviewFileController extends JPanel {

	private MainViewController mvc;
	public Highlighter highlighter;
	private ScorePartwise sp;
	private LinkedList<LocationGuitar> aL;

	@FXML public CodeArea mxlText; 


	@FXML private ImageView imageView;

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
		this.sp = sp;
		createList();
		createJFrame(new JFrame());
	}

	public PreviewFileController ()  {}

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

	public void initialize() {}


	@FXML
	private void createJFrame(JFrame f) {
		f.add(new Draw(this.getMeasureNumber(), aL));
		f.setPreferredSize(new Dimension(1400, 300));
		f.setTitle(this.sp.getPartList().getScoreParts().get(0).getPartName() + " Sheet Music");
		f.getContentPane().setBackground(Color.white);
		f.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		f.pack();
		f.setVisible(true);
		//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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