package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DesignerModeHomeController {
	
	@FXML 
	private Button homeButton;
	
	private Stage currStage;
	
	public DesignerModeHomeController(Stage s) {
		currStage = s;
	}

	@FXML
	private void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
	}
}
