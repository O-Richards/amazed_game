package gameApplication;
import gameController.OpeningScreen;
import gameController.OpeningController;
import gameController.PlayerModeHomeScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class gameApplicationMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
//			primaryStage.setTitle("The a MAZE ing Escape");
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView/OpeningScreen.fxml"));
//			//OpeningScreen startingGame = new OpeningScreen(primaryStage); 
//			//loader.setController(startingGame);
//			//startingGame.start();
//			Parent root = loader.load();
//			Scene scene = new Scene(root);
//		
//			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
//			primaryStage.show();
			
			OpeningScreen openingScreen = new OpeningScreen(primaryStage);
			openingScreen.start();
			
		} catch(Exception e) { 
			e.printStackTrace();
		}

			
	}

	
	public static void main(String[] args) {
	    launch(args);
	}

}
