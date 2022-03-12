package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import utility.Settings;

public class SaveSheetMusic extends Application{

		private MainViewController mvc;
	    private static Window convertWindow = new Stage();
	    
	    @FXML private TextField fileNameField;
	    ImageView iv = null;	    
	    public void setMainViewController(MainViewController mvcInput, Pane pane) {
	    	mvc = mvcInput;
	    	WritableImage wi = pane.snapshot(null, null);
			
			Printer p = Printer.getDefaultPrinter();
			Double s;
			if(p == null) {
				s = Math.min(487.0/wi.getWidth(),734.0/wi.getHeight());	
			}else {
				PageLayout l = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
				s = Math.min(l.getPrintableWidth()/wi.getWidth(), l.getPrintableHeight()/wi.getHeight());
			}
			
			iv = new ImageView(wi);
			iv.getTransforms().add(new Scale(s,s));
	    }
	    
	    public void initialize() {
			Settings s = Settings.getInstance();
		}
	    
	    @FXML
	    private void saveButtonClicked() {
//	    	method which is being a bitch
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Save As");
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".pdf", "*.PDF", "*.JPG", "*.mxl");
	        fileChooser.getExtensionFilters().add(extFilter);

	        File initialDir = new File(Settings.getInstance().outputFolder);
	        String initialName = null;
	        if (!fileNameField.getText().isBlank() && fileNameField.getText().length()<50)
	            initialName = fileNameField.getText().strip();

	        if (iv != null) {
	        	int width = (int) Math.ceil(iv.getImage().getWidth());
	            int height = (int) Math.ceil(iv.getImage().getHeight());

	            BufferedImage bi = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_ARGB);

	            int[] buffer = new int[width];

	            PixelReader reader = ((javafx.scene.image.Image) iv.getImage()).getPixelReader();
	            WritablePixelFormat<IntBuffer> format = PixelFormat.getIntArgbInstance();
	            for (int y = 0; y < height; y++) {
	                reader.getPixels(0, y, width, 1, format, buffer, 0, width);
	                bi.getRaster().setDataElements(0, y, width, 1, buffer);
	            }
	        	File outputfile = new File(String.format("%s.png",initialName));
	            try {
					ImageIO.write(bi, "png", outputfile);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        if (initialName != null)
	            fileChooser.setInitialFileName(initialName);

	        if (!(initialDir.exists() && initialDir.canRead()))
	            initialDir = new File(System.getProperty("user.home"));

	        fileChooser.setInitialDirectory(initialDir);

	        File file = fileChooser.showSaveDialog(convertWindow);

	        if (file != null) {
//	        	need to do saving here
//	        	PrintWriter writer = new PrintWriter(outputfile);
//	            writer.println(mxlc.generateMusicXML());
//	            mvc.saveFile = file;
//	            
	            mvc.converter.saveMusicXMLFile(file);
	            mvc.saveFile = file;
	            cancelButtonClicked();
	        }
	    }

	    @FXML
	    private void cancelButtonClicked()  {
	    	mvc.convertWindow.hide();
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {}
	}