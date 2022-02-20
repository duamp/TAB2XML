package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fxmisc.richtext.CodeArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import models.Part;
import models.ScorePartwise;

public class PreviewFileController extends JFrame {

    private MainViewController mvc;
	public Highlighter highlighter;
	private ScorePartwise sp;

    @FXML public CodeArea mxlText; 


    @FXML private ImageView imageView;
    
    public String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        return null;
    }
    
    public int getMeasureNumber() {
    	return sp.getParts().get(0).getMeasures().size();
    }
    
	@FXML
	private void saveMXLButtonHandle() {
		mvc.saveMXLButtonHandle();
	}

   
	public PreviewFileController (ScorePartwise sp) throws IOException {
    	this.sp = sp;
    }
    
    public void setMainViewController() throws IOException {
      	setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
    	JLabel trebeclef = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png")));

    	add(trebeclef);
    	for(int i = 0; i < sp.getParts().get(0).getMeasures().size();i++) {
    		add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/Measure.png"))));
    	}
    	
		setTitle("Sheet Music");
		pack();
		setVisible(true);
		
		
//		TO-DO implement this exception later
//		if(1==0) {
//			IOException e = new IOException();
//			throw e;
//		}
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