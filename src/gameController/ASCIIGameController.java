package gameController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import gameModel.*;
import gameModel.entity.VisType;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;
import gameModel.usable.UseAction;
import gameModel.winCondition.WinType;

public class ASCIIGameController {
	
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
	
	public String visualiseLevel(Level l) {
		String retStr = "";
		Integer nRows = l.getNumRows();
		Integer nCols = l.getNumCols();
		for (int row = nRows-1; row >= 0; row--) {
			for (int col = 0; col < nCols; col++) {
				retStr += visTypeToSprite(l.getVisType(new Coord(row, col)));
			}
			retStr += "\n";
		}
		return retStr;
	}
	
	private String visTypeToSprite(VisType visType) {
		Map<VisType, String> spriteMap = new HashMap<>();
			spriteMap.put(VisType.PLAYER, "P");
			spriteMap.put(VisType.SWITCH,"@");
			spriteMap.put(VisType.ARROW, ">");
			spriteMap.put(VisType.BOULDER, "B");
			spriteMap.put(VisType.BOMB, "b");
			spriteMap.put(VisType.HOVER_POTION, "H");
			spriteMap.put(VisType.INVINCIBILITY_POTION, "I");
			spriteMap.put(VisType.TREASURE, "$");
			spriteMap.put(VisType.HUNTER, "h");
			spriteMap.put(VisType.SWORD, "S");
			spriteMap.put(VisType.EXIT, "e");
			spriteMap.put(VisType.PIT, "o");
			spriteMap.put(VisType.EMPTY_TILE, " ");
			spriteMap.put(VisType.KEY, "K");
			spriteMap.put(VisType.DOOR, "D");
			spriteMap.put(VisType.HUNTER, "E");
			spriteMap.put(VisType.WALL, "W");
			spriteMap.put(VisType.HOUND, "6");
			spriteMap.put(VisType.COWARD, "C");
			spriteMap.put(VisType.STRATEGIST, "+");
			spriteMap.put(VisType.LIT_BOMB, "}");
		return spriteMap.get(visType);
	}
	
	private void printInventory(Iterator<UseAction> inventoryIterator) {
		String ret = "";
		while (inventoryIterator.hasNext()) {
			ret += inventoryIterator.next().toString() + ", ";
		}
		System.out.println("Inventory: " + ret);
	}

	public static void main(String[] args) throws IOException, EntityPlacementException {
		ASCIIGameController gc = new ASCIIGameController();
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
		l.placePit(new Coord(9, 9));
		// MobileEntity hunter = make.makeHunter(new Coord(12, 10), player, 0.4);
		// l.placeMobileEntity(hunter);
		// l.placeMobileEntity(make.makeHound(new Coord(11, 11), player, hunter, 0.4));
		l.placeItem(make.makeKey(new Coord(5, 5)));
		l.placeItem(make.makeKey(new Coord(7, 7)));
		// l.addItem(make.makeSword(new Coord(2, 4)));
		l.placeMobileEntity(make.makeBoulder(new Coord(6, 5)));
		l.placeMobileEntity(make.makeCoward(new Coord(15, 15), player, 0.4));
		l.placeMobileEntity(make.makeStrategist(new Coord(15, 13), player, 0.4));
		//l.placeItem(make.makeSword(new Coord(2, 3)));
		l.placeItem(make.makeSword(new Coord(1, 3)));
		l.placeMobileEntity(make.makeBoulder(new Coord(2, 2)));
		l.placeItem(make.makeBomb(new Coord(2, 3)));

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
			System.out.println(gc.visualiseLevel(l));
			gc.printInventory(player.inventoryIterator());
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
