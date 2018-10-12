package gameApplication;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;

import com.sun.glass.ui.Window.Level;

import javafx.fxml.FXML;
import javafx.geometry.Pos;

public class DesignerScreenController {
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
	
	private static final int DEFAULT_NROWS = 50;
	private static final int DEFAULT_NCOLS = 50;

	@FXML
    public void initialize() {
		//by creates a map of size 15x15
		//Note this uses the no args constructor.. 
		Level aLevel = new Level(); 

		//creates a map which has a size of default row and col (we will change this) 
		for (int row = 0; row < DEFAULT_NROWS + 1; row++) {
			for (int col = 0; col < DEFAULT_NCOLS + 1; col++) {
				//prbly gonna change with observer pattern: 
				map.add(new ImageView(new Image(getClass().getResourceAsStream("/wall.png"),15,15,true,true)), col, row);
	
			} 	
		}

	}
	
	
}
