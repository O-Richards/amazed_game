package GameMain;

import java.io.IOException;
import java.util.Scanner;

public class GameController {
	
	
	public static void main(String[] args) throws IOException {
		GameSystem gs = new GameSystem();
		//Setup template maze
		gs.placeEntity(new SwordUsableEntity(null), new Coord(4, 4));
		gs.placeEntity(new BoulderMobileEntity(null), new Coord(2, 3));
		gs.placeSwitch(new Coord(3, 3));
		gs.setSwitchWinCondition(true);
		System.out.println("Use W A S D keys to move me around");
		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.next();
			Direction playerDir = gs.strToDirection(input);
			//System.out.println("Input Dir: " + playerDir);
			gs.movePlayer(playerDir);
			gs.tick();
			System.out.println(gs.levelString());
			System.out.println(gs.inventoryString());
			if (gs.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
		}
		s.close();
	}
	



}
