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
	public void handleHardLevelButton(ActionEvent event) throws EntityPlacementException, EntityCreationException {
		PlayingScreen hardLevel = new PlayingScreen(currStage);
		makeHardMap();
		hardLevel.start();
		hardLevel.setPlayers(players);
		hardLevel.setMap(this.hard);
	}
	
	
	
	private void makeEasyMap() throws EntityPlacementException, EntityCreationException {
		this.easy = new Level();

		double enemyDif = 0.6;
		//creates an Empty array List of players: 
		this.players = new ArrayList<PlayerMobileEntity>();
		
		EntityMaker make = new EntityMaker(easy.getWinSystem(), easy.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(8,4));
		players.add(player);
		easy.placeMobileEntity(player);

		
		MobileEntity enemy = make.makeCoward(new Coord(15, 15), player, enemyDif);
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
		this.medium = new Level();

		double enemyDif = 0.6;
		//creates an Empty array List of players: 
		this.players = new ArrayList<PlayerMobileEntity>();
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
		MobileEntity enemy = make.makeHunter(new Coord(2, 10), player, enemyDif);
		medium.placeMobileEntity(enemy);
		medium.placeItem(make.makeKey(new Coord(1,10)));
		medium.placeItem(make.makeTreasure(new Coord (1,9)));
		
		
		// AREA 3
		medium.placeWall(new Coord(7,9));
		medium.placeWall(new Coord(7,11));
		medium.placeWall(new Coord(8,8));
		medium.placeWall(new Coord(8,7));
		medium.placeWall(new Coord(8,11));
		medium.placeWall(new Coord(9,11));
		medium.placeWall(new Coord(10,11));
		medium.placeWall(new Coord(11,11));
		medium.placeWall(new Coord(11,10));
		medium.placeWall(new Coord(11,9));
		medium.placeWall(new Coord(11,8));
		medium.placeWall(new Coord(11,7));
		medium.placePit(new Coord(10,6));
		medium.placePit(new Coord(9,8));
		medium.placePit(new Coord(8,9));
		medium.placePit(new Coord(9,9));
		medium.placeItem(make.makeArrow(new Coord(8,10)));
		medium.placeItem(make.makeArrow(new Coord(9,10)));
		medium.placeItem(make.makeArrow(new Coord(7,10)));

		// AREA 4
		medium.placeWall(new Coord(7,6));
		medium.placeWall(new Coord(7,5));
		medium.placeWall(new Coord(7,4));
		medium.placeDoor(new Coord(7,3));
		medium.placeItem(make.makeKey(new Coord(15,15)));
		medium.placeWall(new Coord(7,2));
		medium.placeWall(new Coord(7,1));
		medium.placeItem(make.makeTreasure(new Coord(5,1)));
		medium.placeItem(make.makeTreasure(new Coord(5,2)));
		medium.placeItem(make.makeTreasure(new Coord(5,3)));
		medium.placeItem(make.makeTreasure(new Coord(5,4)));
		medium.placeItem(make.makeTreasure(new Coord(5,5)));
		medium.placeItem(make.makeTreasure(new Coord(6,1)));
		medium.placeItem(make.makeTreasure(new Coord(6,2)));
		medium.placeItem(make.makeTreasure(new Coord(6,3)));
		medium.placeItem(make.makeTreasure(new Coord(6,4)));
		medium.placeItem(make.makeTreasure(new Coord(6,5)));
		medium.placeWall(new Coord(6,6));
		medium.placeWall(new Coord(12,6));
		medium.placeWall(new Coord(13,5));
		medium.placeWall(new Coord(15,5));
		medium.placeMobileEntity(make.makeBoulder(new Coord(14, 4)));
		medium.placeItem(make.makeSword(new Coord(14,3)));	
		
		
		// AREA 5
		medium.placeWall(new Coord(15,6));
		medium.placeWall(new Coord(15,7));
		medium.placeWall(new Coord(15,8));
		medium.placeWall(new Coord(15,9));
		medium.placeWall(new Coord(15,10));
		medium.placeWall(new Coord(13,6));
		medium.placeWall(new Coord(13,7));
		medium.placeWall(new Coord(13,8));
		medium.placeWall(new Coord(13,9));
		medium.placeWall(new Coord(13,10));
		medium.placePit(new Coord(15,13));
		medium.placePit(new Coord(14,13));
		medium.placePit(new Coord(13,13));
		medium.placePit(new Coord(13,14));
		medium.placePit(new Coord(13,15));
		medium.placeItem(make.makeHoverPotion(new Coord(12,15)));
		
		MobileEntity enemy1 = make.makeStrategist(new Coord(10, 15), player, enemyDif);
		medium.placeMobileEntity(enemy1);
		medium.enableWinCondition(WinType.TREASURE);		
	}
	
	private void makeHardMap() throws EntityPlacementException, EntityCreationException {
		this.hard = new Level();

		double enemyDif = 0.6;
		//creates an Empty array List of players: 
		this.players = new ArrayList<PlayerMobileEntity>();
		EntityMaker make = new EntityMaker(hard.getWinSystem(), hard.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(1,1));
		hard.placeMobileEntity(player);
		players.add(player);



		// START AREA
		hard.placeWall(new Coord(1, 3));
		hard.placeWall(new Coord(2, 3));
		hard.placeWall(new Coord(4, 3));
		hard.placeMobileEntity(make.makeBoulder(new Coord(3, 3)));
		hard.placeWall(new Coord(4, 2));
		hard.placeWall(new Coord(4, 1));
		hard.placeItem(make.makeSword(new Coord(2,2)));	
		
		// Tunnel Area 1 -> 2
		hard.placeWall(new Coord(4,4));
		hard.placeWall(new Coord(4,5));
		hard.placeWall(new Coord(4,6));
		hard.placeWall(new Coord(2,4));
		hard.placeWall(new Coord(2,5));
		hard.placeWall(new Coord(2,6));
		hard.placeWall(new Coord(1,7));
		
		// AREA 2	
		hard.placeWall(new Coord(5,6));
		hard.placeWall(new Coord(5,7));
		hard.placeWall(new Coord(6,7));
		hard.placeWall(new Coord(6,8));
		hard.placeWall(new Coord(6,9));
		hard.placeDoor(new Coord(6,10));
		hard.placeWall(new Coord(6,11));
		hard.placeWall(new Coord(5,11));
		hard.placeWall(new Coord(4,11));
		hard.placeWall(new Coord(3,11));
		hard.placeWall(new Coord(2,11));
		hard.placeWall(new Coord(1,11));
		hard.placeSwitch(new Coord(5,5));
		
		// AREA 2 ITEMS
		MobileEntity enemy = make.makeHunter(new Coord(2, 10), player, enemyDif);
		hard.placeSwitch(new Coord(3,10));
		hard.placeMobileEntity(enemy);
		hard.placeItem(make.makeKey(new Coord(1,10)));
		hard.placeItem(make.makeTreasure(new Coord (1,9)));
		
		
		// AREA 3
		hard.placeWall(new Coord(7,9));
		hard.placeWall(new Coord(7,11));
		hard.placeWall(new Coord(8,8));
		hard.placeWall(new Coord(8,7));
		hard.placeWall(new Coord(8,11));
		hard.placeWall(new Coord(9,11));
		hard.placeWall(new Coord(10,11));
		hard.placeWall(new Coord(11,11));
		hard.placeWall(new Coord(11,10));
		hard.placeWall(new Coord(11,9));
		hard.placeWall(new Coord(11,8));
		hard.placeWall(new Coord(11,7));
		hard.placePit(new Coord(10,6));
		hard.placePit(new Coord(9,8));
		hard.placePit(new Coord(8,9));
		hard.placePit(new Coord(9,9));
		hard.placeItem(make.makeArrow(new Coord(8,10)));
		hard.placeItem(make.makeArrow(new Coord(9,10)));
		hard.placeItem(make.makeArrow(new Coord(7,10)));

		// AREA 4
		hard.placeWall(new Coord(7,6));
		hard.placeWall(new Coord(7,5));
		hard.placeWall(new Coord(7,4));
		hard.placeDoor(new Coord(7,3));
		hard.placeItem(make.makeKey(new Coord(15,15)));
		hard.placeWall(new Coord(7,2));
		hard.placeWall(new Coord(7,1));
		hard.placeItem(make.makeTreasure(new Coord(5,1)));
		hard.placeItem(make.makeTreasure(new Coord(5,2)));
		hard.placeItem(make.makeTreasure(new Coord(5,3)));
		hard.placeItem(make.makeTreasure(new Coord(5,4)));
		hard.placeItem(make.makeTreasure(new Coord(5,5)));
		hard.placeItem(make.makeTreasure(new Coord(6,1)));
		hard.placeItem(make.makeTreasure(new Coord(6,2)));
		hard.placeItem(make.makeTreasure(new Coord(6,3)));
		hard.placeItem(make.makeTreasure(new Coord(6,4)));
		hard.placeItem(make.makeTreasure(new Coord(6,5)));
		hard.placeWall(new Coord(6,6));
		hard.placeWall(new Coord(12,6));
		hard.placeWall(new Coord(13,5));
		hard.placeWall(new Coord(15,5));
		hard.placeMobileEntity(make.makeBoulder(new Coord(14, 4)));
		hard.placeItem(make.makeSword(new Coord(14,3)));	
				
		// AREA 5
		hard.placeWall(new Coord(15,6));
		hard.placeWall(new Coord(15,7));
		hard.placeWall(new Coord(15,8));
		hard.placeWall(new Coord(15,9));
		hard.placeWall(new Coord(15,10));
		hard.placeWall(new Coord(13,6));
		hard.placeWall(new Coord(13,7));
		hard.placeWall(new Coord(13,8));
		hard.placeWall(new Coord(13,9));
		hard.placeWall(new Coord(13,10));
		hard.placePit(new Coord(15,13));
		hard.placePit(new Coord(14,13));
		hard.placePit(new Coord(13,13));
		hard.placePit(new Coord(13,14));
		hard.placePit(new Coord(13,15));
		hard.placeItem(make.makeHoverPotion(new Coord(12,15)));
		
		
		MobileEntity enemy1 = make.makeStrategist(new Coord(11, 15), player, enemyDif);
		hard.placeMobileEntity(enemy1);
		// AREA 6
		//hard.placeWall(new Coord(10,11));
		hard.placeWall(new Coord(10,12));
		hard.placeWall(new Coord(10,13));
		hard.placeDoor(new Coord(10,14));
		hard.placeItem(make.makeKey(new Coord(14,15)));
		hard.placeWall(new Coord(10,15));
		hard.placeItem(make.makeBomb(new Coord(9,14)));
		hard.placeItem(make.makeArrow(new Coord(9,13)));
		hard.placeSwitch(new Coord(1,15));
		hard.placeItem(make.makeInvincibilityPotion(new Coord(3,13)));
		
		MobileEntity enemy2 = make.makeHunter(new Coord(4, 13), player, enemyDif);
		hard.placeMobileEntity(enemy2);
		MobileEntity enemy3 = make.makeHound(new Coord(5,13), player, enemyDif);
		hard.placeMobileEntity(enemy3);
		
		
		hard.enableWinCondition(WinType.TREASURE);
		hard.enableWinCondition(WinType.SWITCH);
	}
}
