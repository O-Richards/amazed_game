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
	private Stage parentStage; 
	public InstructionsController(Stage parentStage, Stage s) {
		this.parentStage = parentStage; 
		parentStage.hide(); 
		currStage = s;
	}
	
	public void exitScreen(ActionEvent event) {
		parentStage.show();
		currStage.close();
	}

}
