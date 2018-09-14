package GameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSystem {
	private Level level;
	
	public GameSystem() {
		//Setup level with default size
		this.level = new Level();
		System.out.println("aMMMMMMMMMMMazing times starting...");
	}
	
	public boolean placeEntity(Entity entity, Coord coord) {
		return level.addEntity(entity, coord);
	}
	
	public boolean placeSwitch(Coord coord) {
		return this.level.placeSwitch(coord);
	}
	
	public void movePlayer(Direction dir) {
		System.out.println("GameSystem.movePlayer(" + dir + ") called");
		this.level.movePlayer(dir);
	}
	
	public void tick() {
		System.out.println("TickTock Goes The Clock");
		this.level.tick();
	}
	
	public boolean hasWon() {
		return this.level.hasWon();
	}
	
	public void setSwitchWinCondition(Boolean status) {
		this.level.setSwitchWinCondition(status);
	}
	
	public String levelString() {
		return this.level.toString();
	}
	
	public Direction strToDirection(String s) {
		s = s.toLowerCase();
		switch(s) {
			case "w": return Direction.UP;
			case "s": return Direction.DOWN;
			case "a": return Direction.LEFT;
			case "d": return Direction.RIGHT;
			default: return Direction.CENTRE;
		}
	}
		
	public static void main(String[] args) throws IOException {
		GameSystem gs = new GameSystem();
		//Setup template maze
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
			if (gs.hasWon()) {
				System.out.println("WON THE GAME!!!");
				break;
			}
		}
	}
	



}
