package gameController;


import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import gameModel.Coord;
import gameModel.Level;
import java.util.Iterator;

import gameModel.entity.VisType;

import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.usable.UseAction;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private Text won; 

	@FXML
	private ImageView wonImage; 
	@FXML
	private ImageView lostImage; 


	@FXML
	private GridPane inventory;
	
	private ArrayList<JFXPane> invetoryDisplayPanes;
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
    public void initialize() {
		//lostPane.setVisible(false);  	TODO
		//wonPane.setVisible(false);	TODO
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
		
		//Create inventory panes
		setInventory();
		
        hunterThread= new Thread(new MultiThreading());
        hunterThread.setDaemon(true);
        hunterThread.start();
        
	}

	
	private void setInventory() {
		this.invetoryDisplayPanes = new ArrayList<JFXPane>();
		for(int col = l.getNumCols(); col >  0; col--) {
			JFXPane aPane = new JFXPane(col,l.getNumRows());
			invetoryDisplayPanes.add(aPane);
			inventory.add(aPane.getPane(), col,l.getNumRows());		
		}
	}
	

	
	private void updateInventory() {
		PlayerMobileEntity player = players.get(0);
		Iterator<UseAction> playerInventory = player.inventoryIterator();

		for (int i = 0; i < 7 && playerInventory.hasNext(); i++) {
			//System.out.println(playerInventory.next().name());
			JFXPane aPane = invetoryDisplayPanes.get(i);
			switch (playerInventory.next().name()) {
			
			case "ARROW":
				aPane.update(null, VisType.ARROW);
				System.out.println("Added arrow to Inventory");
				break;
			case "BOMB":
				aPane.update(null, VisType.BOMB);
				System.out.println("Added bomb to inventory");
				break;
			case "SWORD":
				aPane.update(null, VisType.SWORD);
				System.out.println("Added sword to inventory");
				break;
			case "KEY":
				aPane.update(null, VisType.KEY);
				System.out.println("Added key to inventory");
				break;				
			case "HOVER_POTION":
				aPane.update(null, VisType.HOVER_POTION);
				System.out.print("Added hover potion to inventory");
				break;
			case "INVINCIBILITY_POTION":
				aPane.update(null, VisType.INVINCIBILITY_POTION);
				System.out.println("Added invinibility potion to inventory");
				break;
			default:
				aPane.update(null, VisType.EMPTY_TILE);
				break;
				
			
			}
		}
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
		//Actions for player 2: 
		if(player2 != null)player2.setMoving(false);

		//direction to shoot/hit: 
		if (pressedKeyNumber == KeyCode.Z) {
			player2.use(UseAction.ARROW);
		}else if(pressedKeyNumber == KeyCode.X) {
			player2.use(UseAction.BOMB);
		}else if(pressedKeyNumber == KeyCode.C) {
			player2.use(UseAction.SWORD);
		}
		//Gets the direction(movement): 
		if (pressedKeyNumber == KeyCode.UP) {
			if(player2 != null)player2.setMoving(true);
			player2.setDirection(Direction.LEFT);
			System.out.println("player2 diretion set");
			
		}else if(pressedKeyNumber == KeyCode.LEFT) {
			if(player2 != null)player2.setMoving(true);
			player2.setDirection(Direction.DOWN);
			System.out.println("player2 diretion set");

		}else if(pressedKeyNumber == KeyCode.DOWN) {
			if(player2 != null)player2.setMoving(true);
			player2.setDirection(Direction.RIGHT);
			System.out.println("player 2 diretion set");

		}else if(pressedKeyNumber == KeyCode.RIGHT) {
			if(player2 != null)player2.setMoving(true);
			player2.setDirection(Direction.UP);
			System.out.println("player 2 diretion set");

		}
		
		//direction to shoot/hit: 
		if (pressedKeyNumber == KeyCode.M) {
			player2.use(UseAction.ARROW);
		}else if(pressedKeyNumber == KeyCode.COMMA) {
			player2.use(UseAction.BOMB);
		}else if(pressedKeyNumber == KeyCode.STOP) {
			player2.use(UseAction.SWORD);
		}
		
    }
    public void runTick() {
    	try {
    	    Platform.runLater(new Runnable(){
    	    	@Override
    	    	public void run(){
    	    		l.tick();
    	    		if (l.hasWon()) {
    	    			hunterThread.interrupt();
    	    			System.out.println("YOU WON");   		
    					wonPane.setVisible(true);
    					wonImage.setVisible(true);
    					won.setVisible(true);
    			        FadeTransition fadeInWonPane = new FadeTransition(Duration.millis(4000), wonPane);
    			        FadeTransition fadeInMessage = new FadeTransition(Duration.millis(9000), won);
    			        fadeInWonPane.setFromValue(0);
    			        fadeInWonPane.setToValue(100);
    			        fadeInMessage.setFromValue(0);
    			        fadeInMessage.setToValue(100);
    			        fadeInWonPane.play();
    			        fadeInMessage.setOnFinished(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
		    					returnHome();
							}
    			        	
    			        });
    			        fadeInMessage.play();
    				}	
    				if (player1 != null && !player1.isAlive()) {
    					hunterThread.interrupt();

    					System.out.println("Player1 lost");
    					lostPane.setVisible(true);

    					lostImage.setVisible(true);
    					player1Lost.setVisible(true);
    					
    			        FadeTransition fadeInLostPane = new FadeTransition(Duration.millis(4000), lostPane);
    			        FadeTransition fadeInPlayer = new FadeTransition(Duration.millis(9000), player1Lost);
    			        fadeInLostPane.setFromValue(0);
    			        fadeInLostPane.setToValue(100);
    			        fadeInPlayer.setFromValue(0);
    			        fadeInPlayer.setToValue(100);
    			        fadeInLostPane.play();
    			        fadeInPlayer.setOnFinished(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
		    					returnHome();
							}
    			        	
    			        });
    			        fadeInPlayer.play();
    				}
    				if (player2 != null &&!player2.isAlive()) {
    					System.out.println("Player2 lost");
    					lostPane.setVisible(true);
    					lostImage.setVisible(true);
    					player2Lost.setVisible(true);
    					
    			        FadeTransition fadeInLostPane = new FadeTransition(Duration.millis(5000), lostPane);
    			        FadeTransition fadeInPlayer = new FadeTransition(Duration.millis(10000), player2Lost);
    			        fadeInLostPane.setFromValue(0);
    			        fadeInLostPane.setToValue(100);
    			        fadeInPlayer.setFromValue(0);
    			        fadeInPlayer.setToValue(100);
    			        fadeInLostPane.play();
    			        fadeInPlayer.setOnFinished(new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent event) {
		    					returnHome();
							}
    			        	
    			        });
    			        fadeInPlayer.play();
    					returnHome();
    				}

    	    		//prevents the player from moving in the next iteration: 
    				if(player1 != null)player1.setMoving(false);    				
    				updateInventory();
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

