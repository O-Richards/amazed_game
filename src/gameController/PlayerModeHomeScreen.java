package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerModeHomeScreen {
	
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public PlayerModeHomeScreen(Stage s) {
		this.s = s;
		this.title = "Choose Your Level";
		this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/PlayerModeHomeScreen.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new PlayerModeHomeController(s));
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.setResizable(false);
            s.show();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}

}
