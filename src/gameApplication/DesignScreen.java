package gameApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesignScreen  extends Application{
	//This will be changed!
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("The a MAZE ing Escape");			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView/DesigningScreen.fxml"));
			DesignerScreenController designerScreenController = new DesignerScreenController(); 
			loader.setController(designerScreenController);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		launch(args);
	}*/

}
