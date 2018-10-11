package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		EasyLevelScreen easyLevel = new EasyLevelScreen(currStage);
		easyLevel.start();
	}
	
	@FXML
	public void handleIntermediateLevelButton(ActionEvent event) {
		IntermediateLevelScreen intermediateLevel = new IntermediateLevelScreen(currStage);
		intermediateLevel.start();
	}
	
	@FXML
	public void handleHardLevelButton(ActionEvent event) {
		HardLevelScreen hardLevel = new HardLevelScreen(currStage);
		hardLevel.start();
	}

}
