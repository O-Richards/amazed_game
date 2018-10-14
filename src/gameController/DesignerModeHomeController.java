package gameController;


import gameModel.Level;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;


public class DesignerModeHomeController {
	
	@FXML 
	private Button homeButton;
	@FXML
	private Button createNewButton;
	@FXML
	private Button play;
	@FXML
	private Button delete;
	
    @FXML
    //Contains a list of Game names: 
    private ListView<DesigningController> listOfGames;
    
	private Stage currStage;
	//Used for displaying items: 
	private ObservableList<DesigningController> savedControllers = FXCollections.observableArrayList(); 
	//Listens for changes in our Controllers: 
	private ObservableList<DesigningController> listOfDesignControllers = FXCollections.observableArrayList(item -> new Observable[] {item.getSaveProperty()}); 

	
	public DesignerModeHomeController(Stage s) {
		currStage = s;
	}

	@FXML
	private void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
	}
	
	@FXML
	private void handleCreateNewButton(ActionEvent event) {
		DesigningScreen designingScreen = new DesigningScreen(currStage);
		//Adds it to our list of DesignControllers:
		listOfDesignControllers.add(designingScreen.start());
		//Listens to the saveState of the Controllers: 
		//Saves to our list if we saved:  
		listOfDesignControllers.addListener((ListChangeListener<DesigningController>) change -> {
			while(change.next()) {
				if(change.wasUpdated()) {
					DesigningController elementChanged = listOfDesignControllers.get(change.getFrom());
					System.out.println(elementChanged);
					if(elementChanged.getSaveProperty().getValue() == true) {
						System.out.println("State is saved");
						//Saves the current Controller if state = true: 
						savedControllers.add(elementChanged);
						System.out.println(savedControllers.get(0).getSaveProperty());
						//Sets items into listview: 
						listOfGames.setItems(savedControllers);
						//Get's the appropriate names: 
						listViewSet();
					}
					change.reset();
					change.next();
				}
			}
		});
	}
	
	@FXML
	public void playMap() {
		
		
	}
	
	@FXML
	public void deleteMap(ActionEvent event) {
		Object selectedMap = listOfGames.getSelectionModel().getSelectedItem();
		listOfGames.getItems().remove(selectedMap);
		savedControllers.remove(selectedMap);
	}
	//Sets the names in list view: 
	private void listViewSet() {
		//Cell factory is used to populate  a list view: 
		listOfGames.setCellFactory(new Callback<ListView<DesigningController>, ListCell<DesigningController>>(){
            @Override
            public ListCell<DesigningController> call(ListView<DesigningController> p) {
                 
                ListCell<DesigningController> cell = new ListCell<DesigningController>(){
 
                    @Override
                    protected void updateItem(DesigningController controller, boolean empty) {
                        super.updateItem(controller, empty);
                        if (controller != null) {
                            setText(controller.getName());
                        }
                    }
 
                };
                return cell;
            }
        });
	}
}
