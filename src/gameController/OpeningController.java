package gameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpeningController {

	
	@FXML
	private Button playerMode;
	@FXML
	private Button designerMode;
	
	private Stage currStage;
	
	public OpeningController(Stage s) {
		currStage = s;
	}
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void handlePlayerModeButton(ActionEvent event) {
		PlayerModeHomeScreen playerModeHome = new PlayerModeHomeScreen(currStage);
		playerModeHome.start();
		
	}
	
	@FXML
	public void handleDesignerModeButton(ActionEvent event) {
		DesignerModeHomeScreen designerModeHome = new DesignerModeHomeScreen(currStage);
		designerModeHome.start();
	}
	
	


}
