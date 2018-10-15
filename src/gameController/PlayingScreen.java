package gameController;

import java.io.IOException;

import gameModel.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayingScreen {
	
	private Stage s;
	private Stage parentStage; 
    private String title;
    private FXMLLoader fxmlLoader;
    private PlayingController playingController; 
    public PlayingScreen(Stage parentStage) {
        this.s = new Stage();
        this.parentStage = parentStage; 
        this.title = "The a MAZE ing Escape";
        this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/PlayingScreen.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        // set controller for start.fxml
        if(parentStage != null) {
            playingController = new PlayingController(parentStage, s);
        }else {
        	playingController = new PlayingController(s);
        }
        fxmlLoader.setController(playingController);
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setMap(Level l) {
    	//Passes to the controller the map
    	playingController.setMap(l); 
    }
   
}
