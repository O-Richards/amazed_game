package gameController;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
	private Button editMap; 
	@FXML
	private Button delete;
	@FXML
	private ScrollPane scrollPane;
    @FXML
    //Contains a list of Game names: 
    private ListView<DesigningController> listOfGames;
    
	private Stage currStage;
	//Used for displaying items: 
	private ObservableList<DesigningController> savedControllers = FXCollections.observableArrayList(); 
	//Listens for changes in our Controllers: 
	private ObservableList<DesigningController> listOfDesignControllers = FXCollections.observableArrayList(item -> new Observable[] {item.getSaveProperty()}); 
	//Keeps a record of calling a designScreen: 
	private DesigningScreen designingScreen;
	
	public DesignerModeHomeController(Stage s) {
		currStage = s;
	}

	@FXML
	private void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
	}
	@FXML
    public void initialize() {
		scrollPane.setFitToWidth(true);
	}
	@FXML
	private void handleCreateNewButton(ActionEvent event) {
		 designingScreen= new DesigningScreen(currStage);
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
						//Sets items into listview: 
						listOfGames.setItems(savedControllers);
						//Get's the appropriate names: 
						listViewSet();
						elementChanged.resetSaveProperty();
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
	public void editAMap() {
		DesigningController designController = listOfGames.getSelectionModel().getSelectedItem();
		designingScreen.loadController(designController);
	}
	@FXML
	public void deleteMap(ActionEvent event) {
		Object selectedMap = listOfGames.getSelectionModel().getSelectedItem();
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
