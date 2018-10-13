package gameApplication;
import java.util.Observable;
import java.util.Observer;
import gameModel.tile.Tile;
import javafx.scene.layout.Pane;

public class JFXPane implements Observer {
	

	private Pane pane;
	private Image image; // TODO <<== enum fed from Tile based upon movable object 	
			
	public JFXPane() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}

}
