package gameController;

import gameModel.Coord;
import gameModel.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlayingController {
	
	@FXML
	private Button pause;
	@FXML
	private GridPane map;
	
	private Stage currStage;
	private Stage parentStage; 
	
	private Level l; 
	
	public PlayingController(Stage s) {
		currStage = s;
	}
	public PlayingController(Stage parentStage, Stage s) {
		currStage = s;
		this.parentStage = parentStage; 
		parentStage.hide();
	}
	
	@FXML
	public void handlePauseButton(ActionEvent event) {
		PauseMenuScreen pauseMenu = new PauseMenuScreen(currStage);
		pauseMenu.start();
	}
	@FXML
	public void returnHome() {
		if(parentStage != null) {
			parentStage.show();
			currStage.close();
			return; 
		}
		PlayerModeHomeScreen modeHome = new PlayerModeHomeScreen(currStage);
		modeHome.start();
	}
	public void setMap(Level l) {
		this.l = l; 
		//Displays map: 
		int col = l.getNumCols();
		int row = l.getNumRows(); 
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				//Creates a JFXPane: 
				JFXPane aPane = new JFXPane(i,j);
				//Adds the pane to our gridView: 
				map.add(aPane.getPane(), i, j);
				//grab the current type of item on the tile:
				//Updates the pane with the correct images: 
				aPane.update(null, l.getVisType(new Coord(i, j)));
				//Attaches a listener to each tile: 
				l.getTile(new Coord(i, j)).addObserver(aPane);
			}
		}
		
	}
}
