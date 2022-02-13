package GUI;


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
    @FXML public CodeArea mxlText; 

    @FXML private ImageView imageView;

    public void updateNote() {
		mxlText.replaceText(mvc.converter.getNote());
		mxlText.moveTo(0);
		mxlText.requestFollowCaret();
        mxlText.requestFocus();
	}

    
    @SuppressWarnings("unused")
	public PreviewFileController (MainViewController mvcInput, int measurenumber) throws IOException {
    	this.mvc = mvcInput;
      	setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

    	int numberOfTabs = 4;//change to real tabs value later

    	JLabel trebeclef = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png")));

    	add(trebeclef);
    	
    	for(int i = 0; i<numberOfTabs;i++) {
    		add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/Measure.png"))));
    	}
    	
    	
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sheetssss");
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