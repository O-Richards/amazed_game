package gameController;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionsScreen {
	private Stage parentStage;
	private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;

    public InstructionsScreen(Stage parentstage) {
    	this.parentStage = parentstage; 
        this.s = new Stage();
        this.title = "How To Play";
        this.fxmlLoader = new FXMLLoader(getClass().getResource("/GameView/Instructions.fxml"));
    }

    public void start()  {
        s.setTitle(title);
        // set controller for start.fxml
        fxmlLoader.setController(new InstructionsController(parentStage,s));
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
