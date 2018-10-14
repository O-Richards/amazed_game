package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayingController {
	
	@FXML
	private Button pause;
	
	private Stage currStage;
	
	public PlayingController(Stage s) {
		currStage = s;
	}
	
	@FXML
	public void handlePauseButton(ActionEvent event) {
		PauseMenuScreen pauseMenu = new PauseMenuScreen(currStage);
		pauseMenu.start();
	}

}
