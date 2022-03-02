package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreviewFileController extends JPanel{
	//
    public PreviewFileController() {
    	JFrame f = new JFrame();
    	int measures = 3;
    	f.add(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image_assets/MeasureWithTrebeclef.png"))));
    	f.add(new DrawMeasure(measures));

    
    	f.setSize(2000,2000);
    	f.getContentPane().setBackground(Color.white);
    	f.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    	f.pack();
    	f.setVisible(true);
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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