package gameController;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import com.sun.glass.ui.Window.Level;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class DesignerController {
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
	private GridPane map;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView someimage; 
	private Level aLevel; 

	private static final int DEFAULT_NROWS = 50;
	private static final int DEFAULT_NCOLS = 50;
	//Item we have clicked: 
	//Have an enum of what item we clicked depends on the button: 
	
	
	//Clicked rows and columns: 
	private int clickedRow = 0; 
	private int clickedColumn = 0; 
	
	//by creates a map of size 50x50
	@FXML
    public void initialize() {
		aLevel = new Level();
		//Note this uses the no args constructor.. 
		//creates a map which has a size of default row and col (we will change this) 
		/*will be moving this into an update fuction: 
		for (int row = 0; row < 50; row++) {
			for (int col = 0; col < 50; col++) {
				//adds an image view to the map: 
				ImageView aNewIMage = new ImageView(new Image(getClass().getResourceAsStream("/wall.png"),20,20,true,true));
				map.add(aNewIMage, col, row);
				//Adds a pane which listens for mouse clicks
				addPane(row, col);
			} 	
		}*/
	}
	@FXML
	public void setItem() {
		
	}
	//Adds a pane to every single grid we make so that we can listen for a mouse click event:  
	private void addPane(int row, int col) {
		Pane pane = new Pane(); 
		//Listens for a mouse click event: 
		//Sets the clicked rows and columns: 
		pane.setOnMouseClicked(e-> {
            clickedRow = row;
            clickedColumn = col;             
        });
        map.add(pane, col, row);		
	}
	
	//Manages button clicks: 
	// a class which manages button clicks: 
	/*private class buttonClick{
		
		
	}*/
}
