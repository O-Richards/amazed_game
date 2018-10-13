package gameController;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.omg.CORBA.PUBLIC_MEMBER;

import gameModel.Level;
import javafx.fxml.FXML;

public class DesigningController {
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
	private GridPane map;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView someimage; 
	
	private Level aLevel; 
	
	private Stage currStage;
	
	private boolean playerPlaced = false; 

	private static final int DEFAULT_NROWS = 50;
	private static final int DEFAULT_NCOLS = 50;
	//Item we have clicked: 
	//Have an enum of what item we clicked depends on the button: 
	
	
	//Clicked rows and columns: 
	private int clickedRow = 0; 
	private int clickedColumn = 0; 
	
	public DesigningController(Stage s) {
		currStage = s;
	}
	
	//by creates a map of size 50x50
	@FXML
    public void initialize() {
		aLevel = new Level();
		//Prevents the user from setting checkboxes unless placed down:
		exitCondition.setDisable(true);
		enemyCondition.setDisable(true);
		switchCondition.setDisable(true);
		treasureCondition.setDisable(true);
		//Note this uses the no args constructor.. 
		//creates a map which has a size of default row and col (we will change this) 
		//will be moving this into an update fuction: 
		for (int row = 0; row < 15; row++) {
			for (int col = 0; col < 15; col++) {
				//adds an image view to the map: 
				ImageView aNewIMage = new ImageView(new Image(getClass().getResourceAsStream("/tile.png"),20,20,true,true));
				map.add(aNewIMage, col, row);
				//Adds a pane which listens for mouse clicks
				addPane(row, col);
				map.setGridLinesVisible(true);

			} 	
		}
	}
	@FXML
	public void setItem() {
		
	}
	//Adds a pane to every single grid we make so that we can listen for a mouse click event:  
	private void addPane(int row, int col) {
		Pane pane = new Pane(); 
		pane.setStyle("-fx-border-color: black;-fx-border-width: .5;-fx-border-color:#E8E8E8");
		//Listens for a mouse click event: 
		//Sets the clicked rows and columns: 
		pane.setOnMouseClicked(e-> {
            clickedRow = row;	
            clickedColumn = col;   
            
        });
		
        map.add(pane, col, row);		
	}
	
	
	// ALL the buttons: 	
	@FXML	
	public void setPlayer() {
		//if(!playerPlaced) {set the current enum to player: and set label}
	}
	@FXML
	public void setWall() {
		//Sets the wall down 
	}
	@FXML
	public void setDoor() {
		
	}
	@FXML
	public void setExit() {
		//Enables the exit checkbox: 
		exitCondition.setDisable(false);
	}
	@FXML
	public void setPit() {

	}
	@FXML
	public void setSwitch() {
		//Enables switch win condition:
		switchCondition.setDisable(false);
	}
	@FXML
	public void setBoulder() {
		
	}
	@FXML
	public void setTreasure() {
		//Enables treasure win condition:
		treasureCondition.setDisable(false);
	}
	@FXML
	public void setKey() {
	}
	@FXML
	public void setArrow() {
		
	}
	@FXML
	public void setSword() {
		
	}
	@FXML
	public void setBomb() {
		
	}
	@FXML
	public void setHound() {
		
	}
	@FXML
	public void setStrat() {
		
	}
	@FXML
	public void setCoward() {
		
	}
	@FXML
	public void setHoverPotion() {
		
	}
	@FXML
	public void setInvincibilityPotion() {
		
	}
}
