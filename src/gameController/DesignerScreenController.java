package gameController;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import gameModel.Coord;
import gameModel.EntityMaker;
import gameModel.Level;
import gameModel.entity.VisType;
import gameModel.tile.EntityPlacementException;
import gameModel.usable.ArrowUsable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class DesignerScreenController {
	private VisType currentlySelected; 
	@FXML
	private Button arrow; 
	@FXML
	private Button bomb;
	@FXML
	private Button boulder; 
	@FXML
	private Button coward;
	@FXML
	private Button door;
	@FXML
	private Button exit; 
	@FXML
	private Button hound;
	@FXML
	private Button hoverPotion;
	@FXML
	private Button invincibilityPotion; 
	@FXML
	private Button key; 	
	@FXML
	private Button pit;
	@FXML
	private Button player;
	@FXML
	private Button strategist; 
	@FXML
	private Button switches; 
	@FXML
	private Button sword; 
	@FXML
	private Button treasure; 
	@FXML
	private Button wall; 
	@FXML
	private Label selectedItem; 
	@FXML
	private CheckBox exitCondition; 
	@FXML
	private CheckBox enemyCondition; 
	@FXML
	private CheckBox switchCondition;
	@FXML
	private CheckBox treasureCondition;
	@FXML
	private Button clear; 

	@FXML
	private GridPane map;
	
	private Level l; 
	private EntityMaker make; 
	
	private int xCoordClicked; 
	private int yCoordClicked; 

	private boolean playerPlaced = false; 

	private static final int DEFAULT_NROWS = 50;
	private static final int DEFAULT_NCOLS = 50;
	//A list observable list: 
	//updates if there's changes to clicked boolean in JFXPanes 
	private ObservableList<JFXPane> gridOfPanes = FXCollections.observableArrayList(item -> new Observable[] {item.clickedProperty()}); 

	public DesignerScreenController(Stage s) {
		// TODO Auto-generated constructor stub
	}

	@FXML
    public void initialize() {
		//we need to pass a value to the level constructor for the proper size: 
		l = new Level();
		make = new EntityMaker(l.getWinSystem(), l.getEntityMover());
		//Prevents the user from setting checkboxes unless corresponding items are placed down:
		exitCondition.setDisable(true);
		enemyCondition.setDisable(true);
		switchCondition.setDisable(true);
		treasureCondition.setDisable(true);
		
		//creates a map which has a size of default row and col (we will change this) 
		//will be moving this into an update function: 
		for (int row = 0; row < 15; row++) {
			for (int col = 0; col < 15; col++) {
				//Creates a JFXPane: 
				JFXPane aPane = new JFXPane(row,col);
				//Tell the JFXPane to detect Mouse clicks: 
				aPane.detectMouseClicks();
				//Adds the pane to our gridView: 
				map.add(aPane.getPane(), col, row);
				//Attach it to our observable list: 
				gridOfPanes.add(aPane);
			} 	
		}
		//Listens for changes in our observableList: 
		gridOfPanes.addListener((ListChangeListener<JFXPane>) change -> {
			while(change.next()) {
				if(change.wasUpdated()) {
					JFXPane elementChanged = gridOfPanes.get(change.getFrom());
					if(elementChanged.clickedProperty().equals(new SimpleBooleanProperty(true))) {
						this.xCoordClicked = elementChanged.getRow();
						this.yCoordClicked = elementChanged.getColumn();
						System.out.println("Ran");
						//Sets the boolean the clicked boolean to false
						elementChanged.resetClicked();
						//Resets it to the change to inital state: 
						change.reset();
						change.next();
					}

				}
			}
		});
		
	}

	@FXML	
	public void setPlayer() {
		selectedItem.setText("Player");
		//If player has already been placed:
		//Maybe frontend shouldn't implement this? 
		if(playerPlaced) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.getDialogPane().setContent(new Text("A player has already been placed on the map"));
			alert.showAndWait();
			selectedItem.setText("-");
		}else{
			currentlySelected = VisType.PLAYER;
		}

	}
	@FXML
	public void setExit() {
		selectedItem.setText("exit");
		//Enables the exit checkbox: 
		exitCondition.setDisable(false);
		currentlySelected = VisType.EXIT;
	}
	@FXML
	public void setSwitch() {
		selectedItem.setText("switch");
		//Enables switch win condition:
		switchCondition.setDisable(false);
		currentlySelected = VisType.SWITCH; 
	}
	@FXML
	public void setTreasure() {
		selectedItem.setText("treasure");
		//Enables treasure win condition:
		treasureCondition.setDisable(false);
		currentlySelected = VisType.TREASURE; 
	}
	@FXML
	public void setWall() {
		selectedItem.setText("Wall");
		currentlySelected = VisType.WALL; 
	}
	@FXML
	public void setDoor() {
		selectedItem.setText("door");
		currentlySelected = VisType.DOOR; 
	}

	@FXML
	public void setPit() {
		selectedItem.setText("Pit");
		currentlySelected = VisType.PIT;
	}
	
	@FXML
	public void setBoulder() {
		selectedItem.setText("Boulder");
		currentlySelected = VisType.BOULDER;
	}

	@FXML
	public void setKey() {
		selectedItem.setText("Key");
		currentlySelected = VisType.KEY; 
	}
	@FXML
	public void setArrow() {
		selectedItem.setText("Arrow");
		currentlySelected = VisType.ARROW;
	}
	@FXML
	public void setSword() {
		selectedItem.setText("Sword");
		currentlySelected = VisType.SWORD;
	}
	@FXML
	public void setBomb() {
		selectedItem.setText("Bomb");
		currentlySelected = VisType.BOMB;

	}
	@FXML
	public void setHound() {
		selectedItem.setText("Hound");
		//TODO: NEED TO CHANGE THIS!!!
		currentlySelected = VisType.HUNTER;

	}
	@FXML
	public void setStrat() {
		selectedItem.setText("Strategiest");
		//TODO: CHANGE THIS: 
		currentlySelected = VisType.HUNTER;
	}
	@FXML
	public void setCoward() {
		selectedItem.setText("Coward");
		//TODO: CHANGE THIS: 
		currentlySelected = VisType.HUNTER;
	}
	@FXML
	public void setHoverPotion() {
		selectedItem.setText("Hover Potion");
		currentlySelected = VisType.HOVER_POTION;
	}
	@FXML
	public void setInvincibilityPotion() {
		selectedItem.setText("Inviniciblity Potion");
		currentlySelected = VisType.INVINCIBILITY_POTION; 
	}
	@FXML 
	public void clearSelected() {
		selectedItem.setText("-");
		currentlySelected = null; 
	}
	@FXML
	public void setItem() throws EntityPlacementException {
		switch (currentlySelected) {
			case ARROW:
				l.placeItem(make.makeArrow(new Coord(this.xCoordClicked, this.yCoordClicked)));
				
		}
			
			
	}

}