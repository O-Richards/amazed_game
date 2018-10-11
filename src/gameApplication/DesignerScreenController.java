package gameApplication;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import com.sun.glass.ui.Window.Level;

import javafx.fxml.FXML;

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
	
	private static final int DEFAULT_NROWS = 15;
	private static final int DEFAULT_NCOLS = 15;

	@FXML
    public void initialize() {
		//by creates a map of size 15x15
		//Note this uses the no args constructor.. 
		Level aLevel = new Level(); 
		aLevel.notifyAll();
		for(int i = 0; i < DEFAULT_NROWS; i++) {
	        //map.addRow(0,images);
			
		}
		for(int j = 0; j < DEFAULT_NCOLS; j++) {
			//map.getColumnConstraints().add(new ColumnConstraints());
			//map.add
		}
	}
	
	
}
