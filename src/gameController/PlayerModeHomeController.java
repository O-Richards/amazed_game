package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PlayerModeHomeController {
	
	@FXML
	private Button homeButton;
	@FXML
	private Button easyLevelButton;
	@FXML
	private Button intermediateLevelButton;
	@FXML
	private Button hardLevelButton;
	
	private Stage currStage;
	
	public PlayerModeHomeController(Stage s) {
		currStage = s;
	}
	
	@FXML
	public void initialize() {
		
		
	}
	
	@FXML
	public void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
		
	}
	
	@FXML
	public void handleEasyLevelButton(ActionEvent event) {
		PlayingScreen easyLevel = new PlayingScreen(currStage);
		easyLevel.start();
	}
	
	@FXML
	public void handleIntermediateLevelButton(ActionEvent event) {
		PlayingScreen intermediateLevel = new PlayingScreen(currStage);
		intermediateLevel.start();
	}
	
	@FXML
	public void handleHardLevelButton(MouseEvent event) {
		PlayingScreen hardLevel = new PlayingScreen(currStage);
		hardLevel.start();
	}

}
