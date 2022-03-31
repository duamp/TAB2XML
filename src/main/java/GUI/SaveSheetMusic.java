package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.control.Button;
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
	File outputfile;
		private MainViewController mvc;
	    private static Window convertWindow = new Stage();
	    @FXML private Button CloseBtn;
	    @FXML private TextField fileNameField;
	    ImageView iv = null;	
	    BufferedImage bi;
	
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
	    private void saveButtonClicked() throws IOException, DocumentException {
	    
	    	
	    	
//	    	method which is being a bitch
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("Save As");
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(".pdf",".PDF","*.JPG", "*.mxl");
	        fileChooser.getExtensionFilters().add(extFilter);

	        File initialDir = new File(Settings.getInstance().outputFolder);
	        String initialName = null;
	        if (!fileNameField.getText().isBlank() && fileNameField.getText().length()<50)
	            initialName = fileNameField.getText().strip();
                
	        if (iv != null) {
	        	int width = (int) Math.ceil(iv.getImage().getWidth());
	            int height = (int) Math.ceil(iv.getImage().getHeight());

	             bi = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_ARGB);

	            int[] buffer = new int[width];

	            PixelReader reader = ((javafx.scene.image.Image) iv.getImage()).getPixelReader();
	            WritablePixelFormat<IntBuffer> format = PixelFormat.getIntArgbInstance();
	            for (int y = 0; y < height; y++) {
	                reader.getPixels(0, y, width, 1, format, buffer, 0, width);
	                bi.getRaster().setDataElements(0, y, width, 1, buffer);
	            }
	        outputfile = new File(String.format("%s.png",initialName));
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
	                     
	        
	        System.out.println(outputfile.getPath());    
	      
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(file));
	        document.open();
	        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(outputfile.getPath());
	        img.scaleToFit(PageSize.A4);
	        document.setPageSize(PageSize.A4);
	        document.newPage();
	        document.add(img);
	        document.close();
	        System.out.println("Done");
	     
	        if (file != null) {
//	        	need to do saving here
 //PrintWriter writer = new PrintWriter(outputfile);
 
  //        writer.println(mxlc.generateMusicXML());
//	            mvc.saveFile = file;
//	           
	        	 
	      // mvc.converter.saveMusicXMLFile(file);
	         mvc.saveFile = file;
	            cancelButtonClicked();
	        }
	    }

	    @FXML
	    private void cancelButtonClicked()  {
	    	  Stage stage = (Stage) CloseBtn.getScene().getWindow();
	    	    stage.close();
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {}
	}