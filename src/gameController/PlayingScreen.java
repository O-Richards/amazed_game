package gameController;

import java.io.IOException;

import gameModel.Level;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Timer;
 
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
            this.playingController = new PlayingController(parentStage, s);
        }else {
        	this.playingController = new PlayingController(s);
        }
        fxmlLoader.setController(playingController);
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					KeyCode pressedKeyNumber = e.getCode();
					//Passes the key to the controller: 
					playingController.keyToAction(pressedKeyNumber);
				}
            	
            });
            s.setScene(sc);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	//Passes to the map to controller
    public void setMap(Level l) {
    	playingController.setMap(l); 
    }
}

