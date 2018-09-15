package GameMain;

import java.io.IOException;
import java.util.Scanner;

public class GameController {
	
	
	public static void main(String[] args) throws IOException {
		GameSystem gs = new GameSystem();
		//Setup template maze
		gs.placeEntity(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4));
		gs.placeEntity(new UnlitBombUsableEntity(new Coord(1, 5)), new Coord(1, 5));
		gs.placeEntity(new BoulderMobileEntity(new Coord(2, 3)), new Coord(2, 3));
		gs.placeSwitch(new Coord(3, 3));
		gs.placeEntity(new TreasureEntity( new Coord(5,6)), new Coord(5,6));
		gs.placeEntity(new TreasureEntity(new Coord(10, 2)), new Coord(10, 2));
		gs.placeWall(new Coord(4, 5));
		gs.placeEntity(new HoverPotion(new Coord(3, 4)), new Coord(3, 4));
		gs.placeEntity(new InvincibilityEntity(new Coord(1,5)), new Coord(1,5));
		gs.setSwitchWinCondition(true);
		gs.setTreasureWinCondition(true);
		System.out.println("Use W A S D keys to move me around");
		System.out.println("Use WASD followed by keys J to fire arrows OR K to swing with sword");
		System.out.println("Use keys L to place bomb");

		Scanner s = new Scanner(System.in);
		while(true) {
			String input = s.next();
			//Getting the direction: 
			Direction playerDir = gs.strToDirection(input);
			gs.movePlayer(playerDir);

			//System.out.println("Input Dir: " + playerDir);
			//performs an action: 
			gs.performAction(input); 
		
			
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
