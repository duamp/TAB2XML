package GUI;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.fxmisc.richtext.CodeArea;

import models.measure.Measure;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import utility.Settings;

public class PreviewFileController extends JFrame {

    private MainViewController mvc;
    private static Window convertWindow = new Stage();
    @FXML public CodeArea mxlText; 

//    @FXML private TextField titleField;
//    @FXML private TextField artistField;
//    @FXML private TextField fileNameField;
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
      	setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
//    	Measure measure = new 
    	int numberOfTabs = 4;//change to real tabs value later
    	JLabel trebeclef = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png")));
//    	JLabel tab = );
//    	JLabel tab2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/Measure.png")));

//    	add(trebeclef);
    	
    	for(int i = 0; i<numberOfTabs;i++) {
    		add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/Measure.png"))));
    	}
    	
    	
 //		frame.add(panel, BorderLayout.CENTER);
//		frame.getContentPane().add()
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
		Settings s = Settings.getInstance();
		
		System.out.println("Image displayed");
//	    BufferedImage bufferedImage;
//	    bufferedImage = ImageIO.read(new File("/image_assets/MeasureWithTrebeclef.png"));
//	    image = SwingFXUtils.toFXImage(bufferedImage, null);
//	    this.imageView.setImage(image);
//	    System.out.println("Image displayed2");
	    
//		titleField.setText(s.title);
//		artistField.setText(s.artist);
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
//    	mvc.convertWindow.hide();
    }

}