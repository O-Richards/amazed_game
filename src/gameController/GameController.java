package gameController;

import java.io.IOException;

import java.util.Scanner;

import gameModel.*;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;
import gameModel.usable.UseAction;
import gameModel.winCondition.WinType;

public class GameController {
	
    //Direction inputs:
	private Direction strToDirection(String s) {
		s = s.toLowerCase();
		switch(s) {
			case "w": return Direction.UP;
			case "s": return Direction.DOWN;
			case "a": return Direction.LEFT;
			case "d": return Direction.RIGHT;
			default: return null;
		}
	}	
	
	private UseAction getUse(String s) {
		s = s.toLowerCase();
		if (s.contains("j")) {
			return UseAction.ARROW;
		}
		if (s.contains("k")) return UseAction.SWORD;
		
		if (s.contains("l")) return UseAction.BOMB;
		return null;
	}
	
	public static void main(String[] args) throws IOException, EntityPlacementException {
		GameController gc = new GameController();
		Level l = new Level();
		EntityMaker make = new EntityMaker(l.getWinSystem(), l.getEntityMover());
		
		PlayerMobileEntity player = make.makePlayer(new Coord(1,1));
		l.placeMobileEntity(player);
		
		//Setup template maze
		l.placeItem(make.makeArrow(new Coord(1, 2)));
		l.placeItem(make.makeBomb(new Coord(2, 2)));
		l.placeSwitch(new Coord(3, 6));
		l.placeItem(make.makeTreasure(new Coord(3, 3)));
		l.placeItem(make.makeTreasure(new Coord(4, 4)));
		l.placeWall(new Coord(4, 5));
		l.placeExit(new Coord(6, 1));
		l.placeItem(make.makeHoverPotion(new Coord(2, 4)));
		l.placeItem(make.makeInvincibilityPotion(new Coord(4, 2)));
		l.placePit(new Coord(9,9));
		// l.addEnemy(make.makeEnemy(new Coord(15, 15)));
		l.placeItem(make.makeKey(new Coord(5, 5)));
		l.placeItem(make.makeKey(new Coord(7, 7)));
		// l.addItem(make.makeSword(new Coord(2, 4)));
		l.placeMobileEntity(make.makeBoulder(new Coord(6, 5)));

		l.placeDoor(new Coord(4,1));
		l.enableWinCondition(WinType.TREASURE);
		l.enableWinCondition(WinType.SWITCH);
		l.enableWinCondition(WinType.EXIT);
		
		System.out.println("Use W A S D keys to move me around");
		System.out.println("Use WASD followed by keys J to fire arrows, K to swing with sword, l to drop bombs");
		System.out.println("Use keys L to place bomb");
		System.out.println("Sprite Key: E = enemy P = player B = boulder $ = Treasure b = bomb H = hover potion S = sword @ = switch");
		System.out.println(l.toString());
		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.nextLine();
			//Getting the direction: 
			Direction playerDir = gc.strToDirection(input);
			//l.moveMobileEntity(player, playerDir);
			System.out.println("Read player dir " + playerDir);
			if (playerDir != null) {
				player.setDirection(playerDir);
				player.setMoving(true);
			} else {
				player.setMoving(false);
			}
			UseAction use = gc.getUse(input);
			if (use != null) {
				player.use(use);
			}

			//System.out.println("Input Dir: " + playerDir);
			//performs an action: 
			//gc.performAction(l, input); 
		
			l.tick();
			System.out.println(l.toString());
			System.out.println(player.inventoryString());
			//l.checkInventory(); 
			if (l.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
			if (!player.isAlive()) {
				System.out.println("LOST THE GAME!!!");
				break;
			}
		}
		s.close();
	}
	
}
