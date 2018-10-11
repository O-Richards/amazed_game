package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EasyLevelScreen {
	
	private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public EasyLevelScreen(Stage s) {
        this.s = s;
        this.title = "Easy";
        this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/EasyLevel.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new EasyLevelController(s));
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
