package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesignerModeHomeScreen {
	
	private Stage s;
	private String title;
	private FXMLLoader fxmlLoader;
	
	public DesignerModeHomeScreen(Stage s) {
		this.s = s;
		this.title = "Design your maze";
		this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/DesignerModeHomeScreen.fxml"));
	}
	
	public void start() {
		s.setTitle(title);
		fxmlLoader.setController(new DesignerModeHomeController(s));
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
