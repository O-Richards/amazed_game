package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseMenuScreen {
	
	private Stage s;
	private Stage parentStage;
    private String title;
    private FXMLLoader fxmlLoader;

    public PauseMenuScreen	(Stage parentStage) {
        this.s = new Stage();
        this.parentStage = parentStage;
        this.title = "Game Paused";
        this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/PauseMenu.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new PauseMenuController(parentStage,s));
        try {
            // load into a Parent node called root
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
