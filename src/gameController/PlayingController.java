package gameController;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Timer;


import gameModel.Coord;
import gameModel.Level;
import java.util.Iterator;
import java.util.Set;

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
	private ImageView player1Flying;
	@FXML
	private ImageView player2Flying; 

	@FXML
	private GridPane inventory;
	
	private ArrayList<ArrayList<JFXPane>> inventoryDisplayPanes;
	private ArrayList<PlayerMobileEntity> players; 
	PlayerMobileEntity player1;
	PlayerMobileEntity player2; 
	private Stage currStage;
	private Stage parentStage; 
	Thread hunterThread; 
	Timer timer; 
	private boolean player1MovedLastTick = false;
	private boolean player2MovedLastTick = false;
	private boolean stopPlayer1NextTick = false;
	private boolean stopPlayer2NextTick = false;
	


	private Level l; 
	public PlayingController(Stage s) {
		currStage = s;
		this.inventoryDisplayPanes = new ArrayList<>();
	}
	public PlayingController(Stage parentStage, Stage s) {
		currStage = s;
		this.parentStage = parentStage; 
		parentStage.hide();
		this.inventoryDisplayPanes = new ArrayList<>();
	}

	@FXML
	public void handlePauseButton(ActionEvent event) {
		//interrupts the hunter thread: 
		//Creates a new thread rather than resuming thread
		hunterThread.interrupt();
		currStage.hide();
		PauseMenuScreen pauseMenu = new PauseMenuScreen();
		pauseMenu.start();
		currStage.show();
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
		
		setInventory();
		
        hunterThread= new Thread(new MultiThreading());
        hunterThread.setDaemon(true);
        hunterThread.start();
        
	}

	private void setInventory() {
		for (int j = 0; j<players.size(); j++) {
			ArrayList<JFXPane> inventoryPanes  = new ArrayList<JFXPane>();
			for(int row = 0; row < l.getNumRows(); row++) {
					JFXPane aPane = new JFXPane();
					inventoryPanes.add(aPane);
					inventory.add(aPane.getPane(), j,row);						
			}
			this.inventoryDisplayPanes.add(inventoryPanes);
		}
	}

	
	private void updateInventory() {
		Iterator<ArrayList<JFXPane>> inventoryList = inventoryDisplayPanes.listIterator();
		for (PlayerMobileEntity player : this.players) {
			Iterator<UseAction> playerInventory = player.inventoryIterator();
			Iterator<JFXPane> inventoryPanes = inventoryList.next().iterator();
			while (inventoryPanes.hasNext() ) {
				if (playerInventory.hasNext() == false) {
					JFXPane aPane = inventoryPanes.next(); {
						aPane.update(null,VisType.INVENTORY);
						continue;
					}
				}
				UseAction inventoryItem = playerInventory.next();			
				//ensures we don't add an empty pane			
				if (inventoryItem == UseAction.INVINCIBILITY || inventoryItem == UseAction.HOVER ) {
					if(inventoryItem == UseAction.HOVER) {
						int playerNo = players.indexOf(player);
						System.out.println(playerNo);
						if(playerNo == 0) {
							player1Flying.setVisible(true);
						}else if(playerNo == 1) {
							player2Flying.setVisible(true);
						}
					}
					if(inventoryItem == UseAction.INVINCIBILITY) {
						
					}
					continue;					
				}
				
				JFXPane aPane = inventoryPanes.next();
				try {				
					switch (inventoryItem) {					
					case ARROW :
						aPane.update(null,VisType.INVENTORY_ARROW);
						System.out.println("Added arrow to Inventory");
						break;
					case BOMB:
						aPane.update(null,VisType.INVENTORY_BOMB);
						System.out.println("Added bomb to inventory");
						break;
					case SWORD:
						aPane.update(null,VisType.INVENTORY_SWORD);
						System.out.println("Added sword to inventory");
						break;
					case KEY:
						aPane.update(null,VisType.INVENTORY_KEY);
						System.out.println("Added key to inventory");
						break;				
					case TREASURE:
						aPane.update(null,VisType.INVENTORY_TREASURE);
						System.out.println("Added treasure to inventory");
						break;
					default:
						aPane.update(null,VisType.INVENTORY);
						break;
					}
				} catch (Exception e) {
					aPane.update(null,VisType.INVENTORY);
				}
	
			}
		}
	}	
	
    public void setPlayers(ArrayList<PlayerMobileEntity> players) {
    	this.players = players; 
    }
    public void keyDownToAction(KeyCode pressedKeyNumber) {
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

		
		if(player1 != null) {//player1.setMoving(false);
			//Gets the direction(movement): 
			Direction dir = null;
			switch(pressedKeyNumber) {
				case W: dir = Direction.LEFT; break;
				case A: dir = Direction.DOWN; break;
				case S: dir = Direction.RIGHT; break;
				case D: dir = Direction.UP; break;
				default: dir = null; break;
			}
			if (dir != null) {
				player1.setDirection(dir);
				player1.setMoving(true);
				l.moveMobileEntity(player1, dir);
			}
			//direction to shoot/hit: 
			if (pressedKeyNumber == KeyCode.Z) {
				player1.use(UseAction.ARROW);
			}else if(pressedKeyNumber == KeyCode.X) {
				player1.use(UseAction.SWORD);
			}else if(pressedKeyNumber == KeyCode.C) {
				player1.use(UseAction.BOMB);
			}
		}
		//Actions for player 2: 
		if(player2 != null) {
			//Gets the direction(movement): 
			Direction dir = null;
			switch(pressedKeyNumber) {
				case UP: dir = Direction.LEFT; break;
				case LEFT: dir = Direction.DOWN; break;
				case DOWN: dir = Direction.RIGHT; break;
				case RIGHT: dir = Direction.UP; break;
				default: dir = null; break;
			}
			if (dir != null) {
				player2.setDirection(dir);
				player2.setMoving(true);
				l.moveMobileEntity(player2, dir);
			}
			
			//direction to shoot/hit: 
			if (pressedKeyNumber == KeyCode.M) {
				player2.use(UseAction.ARROW);
			}else if(pressedKeyNumber == KeyCode.COMMA) {
				player2.use(UseAction.SWORD);
			}else if(pressedKeyNumber == KeyCode.STOP) {
				player2.use(UseAction.BOMB);
			}
		}
	
    }
    
    public void keyUpToAction(KeyCode e) {
    	Set<KeyCode> player1Keys = new HashSet<>(Arrays.asList(
    			KeyCode.W, KeyCode.A, KeyCode.D, KeyCode.S));
    	
    	Set<KeyCode> player2Keys = new HashSet<>(Arrays.asList(
    			KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));
    	
    	if (player1Keys.contains(e) && player1 != null) {
    		if (player1MovedLastTick) { 
    			player1.setMoving(false);
			} else {
				stopPlayer1NextTick = true;
			}
    	}
    	if (player2Keys.contains(e) && player2 != null) {
    		if (player2MovedLastTick) { 
    			player2.setMoving(false);
			} else {
				stopPlayer2NextTick = true;
			}
    	}
    }
    
    public void runTick() {
    	try {
    	    Platform.runLater(new Runnable(){
    	    	@Override
    	    	public void run(){
    	    		if (player1 != null) player1MovedLastTick = player1.isMoving();
    	    		if (player2 != null) player2MovedLastTick = player2.isMoving();
    	    		
    	    		l.tick();
    	    		
    	    		if (player1 != null && stopPlayer1NextTick) {
    	    			player1.setMoving(false);
    	    		}
    	    		if (player2 != null && stopPlayer2NextTick) {
    	    			player2.setMoving(false);
    	    		}
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
    				updateInventory();
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

