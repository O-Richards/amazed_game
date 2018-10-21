package gameController;

import java.util.ArrayList;

import gameModel.Coord;
import gameModel.EntityCreationException;
import gameModel.EntityMaker;
import gameModel.Level;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;
import gameModel.winCondition.WinType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PlayerModeHomeController {
	
	private Level easy;
	private Level medium;
	private Level hard;
	private ArrayList<PlayerMobileEntity> players;
	
	
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
		this.medium = new Level();
		//creates an Empty array List of players: 
		this.players = new ArrayList<PlayerMobileEntity>();
	}
	
	@FXML
	public void handleHomeButton(ActionEvent event) {
		OpeningScreen openingScreen = new OpeningScreen(currStage);
		openingScreen.start();
		
	}
	
	@FXML
	public void handleEasyLevelButton(ActionEvent event) throws EntityPlacementException, EntityCreationException {
		makeEasyMap();
		PlayingScreen easyLevel = new PlayingScreen(currStage);
		easyLevel.start();
		easyLevel.setPlayers(players);
		easyLevel.setMap(this.easy);
	}
	
	@FXML
	public void handleIntermediateLevelButton(ActionEvent event) throws EntityPlacementException, EntityCreationException {
		PlayingScreen intermediateLevel = new PlayingScreen(currStage);
		makeMediumMap();
		intermediateLevel.start();
		intermediateLevel.setPlayers(players);
		intermediateLevel.setMap(this.medium);
	}
	
	@FXML
	public void handleHardLevelButton(ActionEvent event) {
		PlayingScreen hardLevel = new PlayingScreen(currStage);
		hardLevel.start();
	}
	
	
	
	private void makeEasyMap() throws EntityPlacementException, EntityCreationException {
		
		EntityMaker make = new EntityMaker(easy.getWinSystem(), easy.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(8,4));
		easy.placeMobileEntity(player);
		players.add(player);

		
		MobileEntity enemy = make.makeCoward(new Coord(15, 15), player, 1);
		easy.placeMobileEntity(enemy);
		
		//WALLS
		easy.placeWall(new Coord(6, 3));
		easy.placeWall(new Coord(7, 3));
		easy.placeWall(new Coord(8, 3));
		easy.placeWall(new Coord(9, 3));
		easy.placeWall(new Coord(10, 3));
		
		easy.placeWall(new Coord(6, 4));
		easy.placeWall(new Coord(6, 5));
		easy.placeWall(new Coord(7, 5));
		
		easy.placeWall(new Coord(10, 4));
		easy.placeWall(new Coord(10, 5));
		easy.placeWall(new Coord(9, 5));
		easy.placeWall(new Coord(9, 6));
		easy.placeWall(new Coord(9, 7));
		easy.placeWall(new Coord(9, 8));
		
		// WALLS AROUND EXIT TILE
		easy.placeWall(new Coord(7, 6));
		easy.placeWall(new Coord(7, 7));
		easy.placeWall(new Coord(7, 8));
		easy.placeWall(new Coord(7, 9));
		easy.placeWall(new Coord(7, 10));
		easy.placeWall(new Coord(7, 11));
		easy.placeWall(new Coord(7,12));
		easy.placeWall(new Coord(7,13));
		easy.placeWall(new Coord(8, 11));
		easy.placeWall(new Coord(9, 10));
		easy.placeWall(new Coord(9, 11));
		
		easy.placeWall(new Coord(10, 8));
		easy.placeWall(new Coord(11, 8));
		easy.placeWall(new Coord(12,8));
		easy.placeWall(new Coord(13,8));
		easy.placeWall(new Coord(14,8));

		// FIRST PIT TUNNEL
		easy.placeWall(new Coord(14,10));
		easy.placeWall(new Coord(13,10));
		easy.placeWall(new Coord(12,10));
		easy.placeWall(new Coord(11,10));
		
		
		easy.placeWall(new Coord(11,11));
		easy.placeWall(new Coord(14,11));
		easy.placeWall(new Coord(13,11));
		easy.placeWall(new Coord(12,11));

		
		easy.placeWall(new Coord(11,13));
		easy.placeWall(new Coord(14,13));
		easy.placeWall(new Coord(13,13));
		easy.placeWall(new Coord(12,13));
		easy.placeWall(new Coord(13,14));
		easy.placeWall(new Coord(13,15));
		
		
		// PLACE HOVER POTION AROUND THESE WALL CHUNKS
		easy.placeItem(make.makeHoverPotion(new Coord(14, 12)));
		easy.placeWall(new Coord(14,14));
		easy.placeWall(new Coord(14,15));
		
		easy.placeWall(new Coord(7,15));
		easy.placeWall(new Coord(7,14));
		easy.placeWall(new Coord(7,13));
		easy.placeDoor(new Coord(8,13));
		easy.placeWall(new Coord(9,13));
		easy.placeWall(new Coord(9,14));
		easy.placeWall(new Coord(9,15));
		easy.placeWall(new Coord(15,8));
		easy.placeWall(new Coord(15,8));
		
		//KEY
		easy.placeItem(make.makeKey(new Coord(15,10)));
		
		// EXIT
		easy.placeExit(new Coord(8,15));
		easy.enableWinCondition(WinType.EXIT);
		
		//PITS
		easy.placePit(new Coord(12,12));
		easy.placePit(new Coord(13,12));
		
		// BOULDERS
		easy.placeMobileEntity(make.makeBoulder(new Coord(10,12)));
		easy.placeMobileEntity(make.makeBoulder(new Coord(11,12)));
		easy.placeMobileEntity(make.makeBoulder(new Coord(8,8)));
	}
	
	
	private void makeMediumMap() throws EntityPlacementException, EntityCreationException {
		
		EntityMaker make = new EntityMaker(medium.getWinSystem(), medium.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(1,1));
		medium.placeMobileEntity(player);
		players.add(player);


		// START AREA
		medium.placeWall(new Coord(1, 3));
		medium.placeWall(new Coord(2, 3));
		medium.placeWall(new Coord(4, 3));
		medium.placeMobileEntity(make.makeBoulder(new Coord(3, 3)));
		medium.placeWall(new Coord(4, 2));
		medium.placeWall(new Coord(4, 1));
		medium.placeItem(make.makeSword(new Coord(2,2)));	
		
		// Tunnel Area 1 -> 2
		medium.placeWall(new Coord(4,4));
		medium.placeWall(new Coord(4,5));
		medium.placeWall(new Coord(4,6));
		medium.placeWall(new Coord(2,4));
		medium.placeWall(new Coord(2,5));
		medium.placeWall(new Coord(2,6));
		medium.placeWall(new Coord(1,7));
		
		// AREA 2	
		medium.placeWall(new Coord(5,6));
		medium.placeWall(new Coord(5,7));
		medium.placeWall(new Coord(6,7));
		medium.placeWall(new Coord(6,8));
		medium.placeWall(new Coord(6,9));
		medium.placeDoor(new Coord(6,10));
		medium.placeWall(new Coord(6,11));
		medium.placeWall(new Coord(5,11));
		medium.placeWall(new Coord(4,11));
		medium.placeWall(new Coord(3,11));
		medium.placeWall(new Coord(2,11));
		medium.placeWall(new Coord(1,11));
		
		// AREA 2 ITEMS
		MobileEntity enemy = make.makeHunter(new Coord(2, 10), player, 1);
		medium.placeMobileEntity(enemy);
		medium.placeItem(make.makeKey(new Coord(1,10)));
		medium.placeItem(make.makeTreasure(new Coord (1,9)));
		
		
		// AREA 3
		medium.placeWall(new Coord(7,9));
		medium.placeWall(new Coord(8,8));
		medium.placeWall(new Coord(8,7));
		medium.placeWall(new Coord(7,11));
		medium.placeWall(new Coord(7,12));
		medium.placeWall(new Coord(8,13));
		medium.placeWall(new Coord(8,14));
	}
	

}
