package GameMain;

import java.io.IOException;
import java.util.Scanner;

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
	private void performAction(String input) {
		//Check if the player has made an actual movement: 
		Direction playerDir = this.isAction(input);
		if(playerDir != null) {
			//Get the action the player has made: 
			Action playerAction = this.action(input);		
			// doAction(playerAction, playerDir);
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
	
	public static void main(String[] args) throws IOException {
		GameController gc = new GameController();
		Level l = new Level();
		//Setup template maze
		l.addEntity(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4));
		l.addEntity(new UnlitBombUsableEntity(new Coord(1, 5)), new Coord(1, 5));
		l.addEntity(new BoulderMobileEntity(new Coord(2, 3)), new Coord(2, 3));
		l.placeSwitch(new Coord(3, 3));
		l.addEntity(new TreasureEntity( new Coord(5,6)), new Coord(5,6));
		l.addEntity(new TreasureEntity(new Coord(10, 2)), new Coord(10, 2));
		l.placeWall(new Coord(4, 5));
		l.addEntity(new HoverPotion(new Coord(3, 4)), new Coord(3, 4));
		l.addEntity(new InvincibilityEntity(new Coord(1,5)), new Coord(1,5));
		l.placePit(new Coord(9,9));
		l.placeExit(new Coord(11,1));
		l.addEntity(new EnemyMobileEntity(new Coord(7, 7)), new Coord(7, 7));
		
		l.enableWinCondition(WinType.EXIT);
		l.enableWinCondition(WinType.ENEMY);
		l.enableWinCondition(WinType.SWITCH);
		l.enableWinCondition(WinType.TREASURE);

		System.out.println("Use W A S D keys to move me around");
		System.out.println("Use WASD followed by keys J to fire arrows OR K to swing with sword");
		System.out.println("Use keys L to place bomb");
		System.out.println("Sprite Key: E = enemy P = player B = boulder $ = Treasure b = bomb H = hover potion S = sword @ = switch");
		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.next();
			//Getting the direction: 
			Direction playerDir = gc.strToDirection(input);
			l.movePlayer(playerDir);

			//System.out.println("Input Dir: " + playerDir);
			//performs an action: 
			gc.performAction(input); 
		
			
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
