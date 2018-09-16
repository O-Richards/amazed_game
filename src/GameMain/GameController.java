package GameMain;

import java.io.IOException;
import java.util.Scanner;

public class GameController {
	
	/**
	 * @param input the input string to parse
	 * @return The appropriate action if an action char is in the string. Null else
	 */
	private static Action parseItemUsage(String input) {
		if (input.contains("K")) return Action.SWORD;
		if (input.contains("J")) return Action.ARROW;
		if (input.contains("L")) return Action.BOMB;
		return null;
	}

	/**
	 * @param input the input string to parse
	 * @return The appropriate direction if a direction char is in the string. Null else
	 */
	private static Direction parseDirection(String input) {
		if (input.contains("w")) return Direction.UP;
		if (input.contains("s")) return Direction.DOWN;
		if (input.contains("a")) return Direction.LEFT;
		if (input.contains("d")) return Direction.RIGHT;
		return null;
	}
	
	/**
	 * @param input the input string to parse
	 * @return 1 if the string contains 1, 0 else.
	 */
	private static Integer parseSpeed(String input) {
		if (input.contains("1")) return 1;
		return 0;
	}


	
	public static void main(String[] args) throws IOException {
		Level l = new Level();
		//Setup template maze

		l.enableWinCondition(WinType.EXIT);
		l.enableWinCondition(WinType.ENEMY);
		l.enableWinCondition(WinType.SWITCH);
		l.enableWinCondition(WinType.TREASURE);

		l.placeEntity(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4));
		l.placeEntity(new UnlitBombUsableEntity(new Coord(1, 5)), new Coord(1, 5));
		l.placeEntity(new BoulderMobileEntity(new Coord(2, 3)), new Coord(2, 3));
		l.placeSwitch(new Coord(3, 3));
		l.placeEntity(new TreasureEntity( new Coord(5,6)), new Coord(5,6));
		l.placeEntity(new TreasureEntity(new Coord(10, 2)), new Coord(10, 2));
		l.placeWall(new Coord(4, 5));
		l.placeExit(new Coord(6, 1));
		l.placeEntity(new HoverPotion(new Coord(3, 4)), new Coord(3, 4));
		l.placeEntity(new InvincibilityEntity(new Coord(1,5)), new Coord(1,5));
		l.placePit(new Coord(9,9));
		l.placeEntity(new EnemyMobileEntity(new Coord(7, 7)), new Coord(7, 7));
		l.placeEntity(new KeyUsableEntity(null), new Coord(2,5));
		l.placeEntity(new KeyUsableEntity(null), new Coord(7,8));
		l.placeEntity(new ArrowUsableEntity(null), new Coord(5,8));

		l.placeDoor(new Coord(4,1));
		
		System.out.println("Use W A S D keys to move me around");
		System.out.println("Use WASD followed by keys J to fire arrows OR K to swing with sword");
		System.out.println("Use keys L to place bomb");
		System.out.println("Sprite Key: E = enemy P = player B = boulder $ = Treasure b = bomb H = hover potion S = sword @ = switch");
		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.nextLine();
			input = input.toLowerCase();
			//Getting the direction: 
			Direction newDir = parseDirection(input);
			if (!(newDir == null)) {
				l.setPlayerDirection(newDir);
			}
			Action newAction = parseItemUsage(input);
			if (!(newAction == null)) {
				l.useItem(newAction);
			}
			
			Integer speed = parseSpeed(input);
			l.setPlayerSpeed(speed);
		
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
