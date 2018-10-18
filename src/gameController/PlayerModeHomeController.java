package gameController;

import gameModel.Coord;
import gameModel.EntityMaker;
import gameModel.Level;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;
import gameModel.winCondition.WinType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PlayerModeHomeController {
	
	private Level easy;
	
	
	@FXML
	private Button homeButton;
	@FXML
	private Button easyLevelButton;
	@FXML
	private Button intermediateLevelButton;
	@FXML
	private Button hardLevelButton;
	
	private Stage currStage;
	
	public PlayerModeHomeController(Stage s) {
		currStage = s;
		this.easy = new Level();
		try {
			makeEasyMap();
		} catch (EntityPlacementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		
		
	}
	
	@FXML
	public void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
		
	}
	
	@FXML
	public void handleEasyLevelButton(ActionEvent event) {
		//PlayingController playingController = new PlayingController(currStage);
		//playingController.setMap(easy);
		PlayingScreen easyLevel = new PlayingScreen(currStage);
		easyLevel.start();
		easyLevel.setMap(this.easy);
		//easyLevel.setPlayers(players);
		//PlayingController playingController = listOfGames.getSelectionModel().getSelectedItem();
			
		
	}
	
	@FXML
	public void handleIntermediateLevelButton(ActionEvent event) {
		PlayingScreen intermediateLevel = new PlayingScreen(currStage);
		intermediateLevel.start();
	}
	
	@FXML
	public void handleHardLevelButton(ActionEvent event) {
		PlayingScreen hardLevel = new PlayingScreen(currStage);
		hardLevel.start();
	}
	
	
	
	private void makeEasyMap() throws EntityPlacementException {
		
		EntityMaker make = new EntityMaker(easy.getWinSystem(), easy.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(1,1));
		easy.placeMobileEntity(player);
		
		//Setup template maze
		easy.placeItem(make.makeArrow(new Coord(1, 2)));
		easy.placeItem(make.makeBomb(new Coord(2, 2)));
		easy.placeSwitch(new Coord(3, 6));
		easy.placeItem(make.makeTreasure(new Coord(3, 3)));
		easy.placeItem(make.makeTreasure(new Coord(4, 4)));
		easy.placeWall(new Coord(4, 5));
		easy.placeExit(new Coord(6, 1));
		easy.placeItem(make.makeHoverPotion(new Coord(2, 4)));
		easy.placeItem(make.makeInvincibilityPotion(new Coord(4, 2)));
		easy.placePit(new Coord(9,9));
		easy.placeMobileEntity(make.makeEnemy(new Coord(8, 7), player, 0.4));
		easy.placeItem(make.makeKey(new Coord(5, 5)));
		easy.placeItem(make.makeKey(new Coord(7, 7)));
		// l.addItem(make.makeSword(new Coord(2, 4)));
		easy.placeMobileEntity(make.makeBoulder(new Coord(6, 5)));
		easy.placeMobileEntity(make.makeEnemy(new Coord(10, 10), player, 0.4));
		
		easy.placeItem(make.makeSword(new Coord(2, 3)));
		easy.placeItem(make.makeSword(new Coord(1, 3)));

		easy.placeDoor(new Coord(4,1));
		easy.enableWinCondition(WinType.TREASURE);
		easy.enableWinCondition(WinType.SWITCH);
		easy.enableWinCondition(WinType.EXIT);
		
	}

}
