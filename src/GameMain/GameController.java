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
		System.out.println("Use WASD followed by keys J to fire arrows OR K to swing with sword");
		System.out.println("Use keys L to place bomb");

		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.next();
			Direction playerDir = gs.strToDirection(input);
			gs.movePlayer(playerDir);

			//System.out.println("Input Dir: " + playerDir);
			//Could refactored: 
			//Player hasn't moved, Checking if it is an action: 
			if(playerDir == Direction.CENTRE) {
				playerDir = gs.isAction(input);
				if(playerDir != null) {
					//Get the action the player has made: 
					Action playerAction = gs.action(input);					
					
				}
			}
			gs.tick();
			System.out.println(gs.levelString());
			System.out.println(gs.inventoryString());
			//gs.checkInventory(); 
			if (gs.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
		}
		s.close();
	}
	



}
