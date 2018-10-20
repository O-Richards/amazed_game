package gameController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseMenuController {
	
	@FXML
	private Button howToPlay;
	@FXML
	private Button restart;
	@FXML
	private Button resume;
	
	private Stage currStage;
	
	public PauseMenuController(Stage s) {
		currStage = s;
	}

	
	@FXML
	public void handleHowToPlayButton() {
		InstructionsScreen instructions = new InstructionsScreen();
		instructions.start();
		currStage.show();
	}

	
	@FXML 
	public void handleRestartButton() {
		/*PlayingScreen playing = new PlayingScreen(parentStage);
		playing.start();*/
		
	}
	
	@FXML 
	public void handleResumeButton() {
		currStage.close(); 
	}
	
	

}
