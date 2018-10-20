package gameController;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import gameModel.Coord;
import gameModel.Level;

import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.usable.UseAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayingController {
	
	@FXML
	private Button pause;
	@FXML
	private GridPane map;
	@FXML
	private Pane lostPane; 
	@FXML
	private Pane wonPane; 
	@FXML 
	private Text player1Lost; 
	@FXML
	private Text player2Lost; 
	@FXML
	private Text player1Won; 
	@FXML
	private Text player2Won; 
	@FXML
	private ImageView wonImage; 
	@FXML
	private ImageView lostImage; 
	
	private ArrayList<PlayerMobileEntity> players; 
	PlayerMobileEntity player1;
	PlayerMobileEntity player2; 
	private Stage currStage;
	private Stage parentStage; 
	Thread hunterThread; 
	Timer timer; 
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
		PauseMenuScreen pauseMenu = new PauseMenuScreen(currStage);
		//interrupts the hunter thread: 
		hunterThread.interrupt();
		//hides the game: 
		currStage.hide();
		pauseMenu.start();
		hunterThread = new Thread(new MultiThreading());		
		hunterThread.setDaemon(true);
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
		//Sets the grid size to match the map: 
		
        hunterThread= new Thread(new MultiThreading());
        hunterThread.setDaemon(true);
        hunterThread.start();
        
	}
    public void setPlayers(ArrayList<PlayerMobileEntity> players) {
    	this.players = players; 
    }
    public void keyToAction(KeyCode pressedKeyNumber) {
    	System.out.println("detect key pressed");
    	System.out.println(pressedKeyNumber);

		if(pressedKeyNumber == KeyCode.ESCAPE) {
			//TODO: Link the pause menu or a key??? 
		}
		
		//If there's only 1 player
		if(players.size() == 1) {
			player1 = players.get(0);
		//More than one player: 
		}else if(players.size() > 1) {
			player1 = players.get(0);
			player2 = players.get(1);
		}


		if(player1 != null)player1.setMoving(false);

		//Gets the direction(movement): 
		if (pressedKeyNumber == KeyCode.W) {
			if(player1 != null)player1.setMoving(true);
			player1.setDirection(Direction.LEFT);
			System.out.println("player diretion set");
			
		}else if(pressedKeyNumber == KeyCode.A) {
			if(player1 != null)player1.setMoving(true);
			player1.setDirection(Direction.DOWN);
			System.out.println("player diretion set");

		}else if(pressedKeyNumber == KeyCode.S) {
			if(player1 != null)player1.setMoving(true);
			player1.setDirection(Direction.RIGHT);
			System.out.println("player diretion set");

		}else if(pressedKeyNumber == KeyCode.D) {
			if(player1 != null)player1.setMoving(true);
			player1.setDirection(Direction.UP);
			System.out.println("player diretion set");

		}
		
		//direction to shoot/hit: 
		if (pressedKeyNumber == KeyCode.J) {
			player1.use(UseAction.ARROW);
		}else if(pressedKeyNumber == KeyCode.K) {
			player1.use(UseAction.BOMB);
		}else if(pressedKeyNumber == KeyCode.L) {
			player1.use(UseAction.SWORD);
		}
		
    }
    public void runTick() {
    	try {
    	    Platform.runLater(new Runnable(){
    	    	@Override
    	    	public void run(){
    	    		l.tick();
    	    		if (l.hasWon()) {
    	    			System.out.println("YOU WON");
    	    			
    	    			returnHome();
    				}
    				if (player1 != null && !player1.isAlive()) {
    					System.out.println("Player1 lost");
//    					lostPane.setVisible(true);
//    					wonImage.setVisible(true);
//    					player1Lost.setVisible(true);
    					wonImage.setOpacity(0);
    					player1Lost.setOpacity(0);


    					
    					timer = new Timer(); 
    					timer.schedule(new TimerTask() {
        					int x = 0; 
    						@Override
    						public void run() {
    							wonImage.setOpacity(x);
    	    					player1Lost.setOpacity(x);
    	    					x++; 
    	    					if(x>=100) {
    	    						timer.cancel();
    	    					}
    						}
    					}, 100);
    					returnHome();
    				}
    				if (player2 != null &&!player2.isAlive()) {
    					System.out.println("Player2 lost");
    					
    					returnHome();
    				}

    	    		//prevents the player from moving in the next iteration: 
    				if(player1 != null)player1.setMoving(false);
    				if(player2 != null)player2.setMoving(false);

    	    	}
    	    });
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    //returns the map
    public GridPane getMap() {
    	return this.map;
    }
    //Used for Hunters: 
    class MultiThreading implements Runnable 
    { 

      public void run() { 
    	 synchronized (l) {
			try {
	    		while(true) {
	          	  System.out.println("tick");
	          	  runTick();
	          	  Thread.sleep(400);
	    		}
			} catch (InterruptedException e) {
				System.out.println("thread interrupted");
				return;
			}
    	 }	  
      } 
    } 
}

