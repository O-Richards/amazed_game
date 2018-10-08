package gameController;

import java.io.IOException;

import java.util.Scanner;

import gameModel.*;

public class GameController {
	
    //Direction inputs:
	private Direction strToDirection(String s) {
		s = s.toLowerCase();
		switch(s) {
			case "w": return Direction.UP;
			case "s": return Direction.DOWN;
			case "a": return Direction.LEFT;
			case "d": return Direction.RIGHT;
			default: return Direction.CENTRE;
		}
	}

	//Get action: 
	private void performAction(Level l, String input) {
		//Check if the player has made an actual movement: 
		System.out.println("GameSystem.performAction perfoming " + input);
		Direction playerDir = this.isAction(input);
		if(playerDir != null) {
			//Get the action the player has made: 
			Action playerAction = this.action(input);		
			l.playerDo(playerAction, playerDir);
		}
	}
	
	//Gets the action: i.e. represented by the last char: 
	private Action action(String s) {
		s = s.toLowerCase();
		char temp = s.charAt(s.length()-1);
		switch(temp) {
			//Cases for shooting an arrow
			case 'j': return Action.ARROW;
			case 'k': return Action.SWORD;
			case 'l': return Action.BOMB;
			default: return null;
		}
	}
	
	//Checks if it is an action input: 
	private Direction isAction(String s) {
		s = s.toLowerCase();
		switch(s) {
			//Cases for shooting an arrow
			case "wj": return Direction.UP;
			case "sj": return Direction.DOWN;
			case "aj": return Direction.LEFT;
			case "dj": return Direction.RIGHT;
			//Swinging a sword
			case "wk": return Direction.UP;
			case "sk": return Direction.DOWN;
			case "ak": return Direction.LEFT;
			case "dk": return Direction.RIGHT;
			//placing a bomb
			case "l": return Direction.CENTRE;
			default: return null;
		}
	}
	
	public static void main(String[] args) throws IOException, EntityPlacementException {
		GameController gc = new GameController();
		Level l = new Level();
		//Setup template maze
		try {
			l.addPlayer(new Coord(1, 1));
		} catch (EntityPlacementException e) {
			e.printStackTrace();
		}

		EntityMaker make = new EntityMaker(l.getWinSystem(), l.getEntityMover());
		l.addItem(make.makeArrow(new Coord(1, 1)));
		l.addItem(make.makeBomb(new Coord(2, 2)));
		l.placeSwitch(new Coord(3, 6));
		l.addItem(make.makeTreasure(new Coord(3, 3)));
		l.addItem(make.makeTreasure(new Coord(4, 4)));
		l.placeWall(new Coord(4, 5));
		l.placeExit(new Coord(6, 1));
		l.addItem(make.makeHoverPotion(new Coord(2, 4)));
		l.addItem(make.makeInvincibilityPotion(new Coord(4, 2)));
		l.placePit(new Coord(9,9));
		// l.addEnemy(make.makeEnemy(new Coord(15, 15)));
		l.addItem(make.makeKey(new Coord(5, 5)));
		l.addItem(make.makeKey(new Coord(7, 7)));
		// l.addItem(make.makeSword(new Coord(2, 4)));
		l.addItem(make.makeBoulder(new Coord(3, 1)));

		l.placeDoor(new Coord(4,1));
		
		System.out.println("Use W A S D keys to move me around");
		System.out.println("Use WASD followed by keys J to fire arrows OR K to swing with sword");
		System.out.println("Use keys L to place bomb");
		System.out.println("Sprite Key: E = enemy P = player B = boulder $ = Treasure b = bomb H = hover potion S = sword @ = switch");
		System.out.println(l.toString());
		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.nextLine();
			//Getting the direction: 
			Direction playerDir = gc.strToDirection(input);
			l.moveMobileEntity(player, playerDir)

			//System.out.println("Input Dir: " + playerDir);
			//performs an action: 
			gc.performAction(l, input); 
		
			l.tick();
			System.out.println(l.toString());
			System.out.println(l.inventoryString());
			//l.checkInventory(); 
			if (l.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
			if (l.hasLost()) {
				System.out.println("LOST THE GAME!!!");
				break;
			}
		}
		s.close();
	}
	
}
