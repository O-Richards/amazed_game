package gameController;

import java.util.ArrayList;

import gameModel.Level;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class DesignerModeHomeController {
	
	@FXML 
	private Button homeButton;
	@FXML
	private Button createNewButton;
	@FXML
	private Button play;
	@FXML
	private Button delete;
	
	private ArrayList<Level> savedMaps;
	
	private ListView listOfMaps;

	
	private Stage currStage;
	
	public DesignerModeHomeController(Stage s) {
		currStage = s;
		savedMaps = new ArrayList<>();
	}

	@FXML
	private void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
	}
	
	@FXML
	private void handleCreateNewButton(ActionEvent event) {
		DesigningScreen designingScreen = new DesigningScreen(currStage);
		designingScreen.start();
	}
	
	@FXML
	public void playMap() {
		
		
	}
	
	@FXML
	public void deleteMap(ActionEvent event) {
		Object selectedMap = listOfMaps.getSelectionModel().getSelectedItem();
		listOfMaps.getItems().remove(selectedMap);
		savedMaps.remove(selectedMap);
	}
	
	public void addMap(Level map) {
		savedMaps.add(map);
		ObservableList<Level> maps = FXCollections.observableArrayList();
		listOfMaps.setItems(maps);
	}
}
