package gameController;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;

import gameModel.Coord;
import gameModel.EntityMaker;
import gameModel.Level;
import gameModel.entity.VisType;
import gameModel.mobileEntity.PlayerMobileEntity;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DesigningController {
	private VisType currentlySelected;
	private String name; 
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
	private Button hunter;
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
	private Button save;
	@FXML
	private Button exitScreen;
	@FXML
	private Button play;

	@FXML
	private GridPane map;
	
	private Stage parentStage;
	private Stage currStage;
	//Checks if we're meant to save the controller: 
	private SimpleBooleanProperty saveState; 
	private Level l; 
	private EntityMaker make; 
	//Player entity: 
	private PlayerMobileEntity newPlayer; 
	
	private static final int DEFAULT_NROWS = 50;
	private static final int DEFAULT_NCOLS = 50;
	//A list observable list: 
	//updates if there's changes to clicked boolean in JFXPanes 
	private ObservableList<JFXPane> gridOfPanes = FXCollections.observableArrayList(item -> new Observable[] {item.clickedProperty()}); 
	
	public DesigningController(Stage parentStage, Stage currStage) {
		//hides the parent stage
		this.parentStage = parentStage;
		this.parentStage.hide();
		this.currStage = currStage;
	}

	@FXML
    public void initialize() {
		saveState = new SimpleBooleanProperty(false); 
		name = "aNAME";
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
				map.add(aPane.getPane(), row, col);
				//Attach it to our observable list: 
				gridOfPanes.add(aPane);
				l.getTile(new Coord(row, col)).addObserver(aPane);
			} 	
		}
		//Listens for changes in our observableList: 
		gridOfPanes.addListener((ListChangeListener<JFXPane>) change -> {
			while(change.next()) {
				if(change.wasUpdated()) {
					//TODO: may need improvment??? comparing .equals(new simpleBooleanProperty) doesn't work... 
					JFXPane elementChanged = gridOfPanes.get(change.getFrom());
					if(elementChanged.clickedProperty().getValue() == true) {
						//Attemps to set the item down: 
						this.setItem(elementChanged.getRow(), elementChanged.getColumn());
						//Sets the boolean the clicked boolean to false
						elementChanged.resetClicked();
						//Resets it to the change to initial state: 
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
		if(newPlayer != null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.getDialogPane().setContent(new Text("A player has already been placed on the map"));
			alert.showAndWait();
			selectedItem.setText("-");
			currentlySelected = null;
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
		selectedItem.setText("Treasure");
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
		selectedItem.setText("Door");
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
	public void setHunter() {
		selectedItem.setText("Hunter");
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

	public void saveMap() {
		this.saveState.set(true);
		currStage.close();
		parentStage.show();
	}
	
	@FXML
	public void playMap(ActionEvent event) {
		PlayingScreen playing = new PlayingScreen(currStage);
		playing.start();
		
	}
	
	@FXML
	public void exitScreen() {
		//Closes the current stage
		currStage.close();
		//Shows the parent stage
		parentStage.show();
	}
	
	
	
	public void setItem(int row, int col){
		try {
			//attempts to set items down
			System.out.println(currentlySelected);
			switch (currentlySelected) {
			case ARROW:
				l.placeItem(make.makeArrow(new Coord(row, col)));
				System.out.println("placed an arrow");
				break;
			case BOMB:
				l.placeItem(make.makeBomb(new Coord(row, col)));
				System.out.println("placed a bomb");
				break;
			case BOULDER:
				l.placeMobileEntity(make.makeBoulder(new Coord(row, col)));		
				System.out.println("placed Boulder");
				break;
			case DOOR:
				l.placeDoor(new Coord(row,col));
				System.out.println("placed Door");
				break;
			case EMPTY_TILE:
				break;
			case EXIT:
				l.placeExit(new Coord(row, col));
				System.out.println("placed exit");
				break;
			case HOVER_POTION:
				l.placeItem(make.makeHoverPotion(new Coord(row, col)));
				System.out.println("hover_potion");
				break;
			case HUNTER:
				System.out.println("lol what hunter");
				break;
			case INVINCIBILITY_POTION:
				l.placeItem(make.makeInvincibilityPotion(new Coord(row, col)));
				System.out.println("invincibility potion");
				break;
			case KEY:
				l.placeItem(make.makeKey(new Coord(row, col)));
				System.out.println("key");
				break;
			case PIT:
				l.placePit(new Coord(row, col));
				System.out.println("pit");
				l.getTile(new Coord(row,col)).notifyObservers();
				break;
			case PLAYER:
				newPlayer = make.makePlayer(new Coord(row,col));
				l.placeMobileEntity(newPlayer);
				System.out.println("player placed");
				break;
			case SWITCH:
				l.placeSwitch(new Coord(row, col));
				System.out.println("placed switch");
				break;
			case SWORD:
				//l.placeItem(make.make(new Coord(row, col)));
				System.out.println("i can't place sword yet Not implemented in backend");
				break;
			case TREASURE:
				l.placeItem(make.makeTreasure(new Coord(row, col)));
				System.out.println("treasure placed");
				break;
			case WALL:
				l.placeWall(new Coord(row, col));
				System.out.println("wall placed");
				break;
			default: 
				break;
				
			}
			//Always sets it back to norm
			currentlySelected = null; 
			selectedItem.setText("-");
			
		} catch (Exception e) {
			if(this.currentlySelected != null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.getDialogPane().setContent(new Text("Unable to place item!!"));
				alert.showAndWait();
				selectedItem.setText("-");
				currentlySelected = null;
			}else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.getDialogPane().setContent(new Text("Please select an item"));
				alert.showAndWait();
				selectedItem.setText("-");
				currentlySelected = null;
			}
			
		}
	}
	
	public SimpleBooleanProperty getSaveProperty() {
		return this.saveState;
	}
	public Level getLevel() {
		return this.l;
	}
	public String getName() {
		return this.name;
	}

}