package gameController;


import gameModel.Coord;
import gameModel.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;	

public class PlayingController {
	
	@FXML
	private Button pause;
	@FXML
	private GridPane map;
	
	private Stage currStage;
	private Stage parentStage; 
	Thread hunterThread; 
	private Level l; 
	
	public PlayingController(Stage s) {
		currStage = s;
	}
	public PlayingController(Stage parentStage, Stage s) {
		currStage = s;
		this.parentStage = parentStage; 
		parentStage.hide();
	}
	
	@FXML
	public void handlePauseButton(ActionEvent event) {
		PauseMenuScreen pauseMenu = new PauseMenuScreen(new Stage());
		currStage.hide();
		hunterThread.interrupt();
		pauseMenu.start();
		currStage.show();
		hunterThread = new Thread(new MultiThreading());
		hunterThread.start();
	}
	@FXML
	public void returnHome() {
		hunterThread.interrupt();
		if(parentStage != null) {
			parentStage.show();
			currStage.close();
			return; 
		}
		PlayerModeHomeScreen modeHome = new PlayerModeHomeScreen(currStage);
		modeHome.start();
	}
	public void setMap(Level l) {
		this.l = l; 
		//Displays map: 
		int nCol = l.getNumCols();
		int nRow = l.getNumRows(); 
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				//Creates a JFXPane: 
				JFXPane aPane = new JFXPane(row,col);
				//Adds the pane to our gridView: 
				map.add(aPane.getPane(), row, col);
				//grab the current type of item on the tile:
				//Updates the pane with the correct images: 
				aPane.update(null, l.getVisType(new Coord(row, col)));
				//Attaches a listener to each tile: 
				l.getTile(new Coord(row, col)).addObserver(aPane);
			}
		}
        hunterThread= new Thread(new MultiThreading());
        hunterThread.start();
	}
    public void keyToAction(KeyCode pressedKeyNumber) {
    	System.out.println("detect key pressed");
    	System.out.println(pressedKeyNumber);
		if(pressedKeyNumber == KeyCode.ESCAPE) {
			//TODO: Link the pause menu or a key??? 
		}
		//Gets the direction(movement): 
		if (pressedKeyNumber == KeyCode.W) {
		}else if(pressedKeyNumber == KeyCode.A) {
		}else if(pressedKeyNumber == KeyCode.S) {
		}else if(pressedKeyNumber == KeyCode.D) {
		}
		
		//direction to shoot/hit: 
		if (pressedKeyNumber == KeyCode.UP) {
		}else if(pressedKeyNumber == KeyCode.DOWN) {
		}else if(pressedKeyNumber == KeyCode.LEFT) {
		}else if(pressedKeyNumber == KeyCode.RIGHT) {
		}    	   
    }
    
    //Used for Hunters: 
    class MultiThreading implements Runnable 
    { 

      public void run() { 
    	  /*
    	  if (l.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
			if (!player.isAlive()) {
				System.out.println("LOST THE GAME!!!");
				break;
			}*/
    	try {
    		while(true) {
          	  System.out.println("tick");
          	  Thread.sleep(1000);
      	  }
		} catch (InterruptedException e) {
			System.out.println("thread interrupted");
			return;
		}
    	  
      } 
    } 
}

