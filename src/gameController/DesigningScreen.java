package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesigningScreen {
	
	private Stage s;
	private Stage parentStage; 
	private String title;
	private FXMLLoader fxmlLoader;
	public DesigningScreen(Stage parentStage) {
		//creates a new stage of itself and keeps track of the parent stage
		this.s = new Stage();
		this.parentStage = parentStage; 
		this.title = "Design your maze";
		this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/DesigningScreen.fxml"));
	}
	
	//does this function have to be void? ?? 
	public DesigningController start() {
		s.setTitle(title);
		DesigningController designingController = new DesigningController(parentStage, s);
		fxmlLoader.setController(designingController);
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.setResizable(true);
            s.show();
            //Listen for User to save: 
            
        } catch(IOException e) {
        	e.printStackTrace();
        }
        //returns the controller to the main function: 
        return designingController;
	}
	
	
}
