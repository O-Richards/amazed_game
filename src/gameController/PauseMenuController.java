package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseMenuController {
	
	@FXML
	private Button howToPlay;
	@FXML
	private Button quit;
	@FXML
	private Button restart;
	@FXML
	private Button resume;
	
	private Stage currStage;
	
	public PauseMenuController(Stage s) {
		currStage = s;
	}
	
	@FXML
	public void handleHowToPlayButton(ActionEvent event) {
		InstructionsScreen instructions = new InstructionsScreen(currStage);
		instructions.start();
		
	}
	
	@FXML
	public void handleQuitButton(ActionEvent event) {
		
	}
	
	@FXML 
	public void handleRestartButton(ActionEvent event) {
		
	}
	
	@FXML 
	public void handleResumeButton(ActionEvent event) {
		
	}
	

}
