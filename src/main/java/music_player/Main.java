package music_player;
	
import java.io.IOException;

import org.jfugue.player.Player;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	   
	@Override
	public void start(Stage stage) throws IOException {
	
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/music_player.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Music Player");
        stage.setScene(scene);
   
        stage.setMinWidth(scene.getRoot().minWidth(0) + 20);
        stage.setMinHeight(scene.getRoot().minHeight(0) + 40);
        
        stage.show();
        
        
		// cannot be put in initialize() b/c stage/scene is not loaded yet
		stage.setOnCloseRequest(event ->{
			 //r u sure window...
			System.out.println("xd");
			PlayerController xd = loader.getController();
			xd.closeSequencer();
			 
		 });
      

       
   }
	

	public static void main(String[] args) {
		launch(args);
	}
}
