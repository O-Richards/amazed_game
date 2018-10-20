package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InstructionsController {
	
	@FXML
	private Button exitScreen;
	public Stage currStage;
	public InstructionsController(Stage s) {
		currStage = s;
	}
	
	public void exitScreen(ActionEvent event) {
		currStage.close();
	}

}
