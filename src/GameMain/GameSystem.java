package GameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameSystem {
	//Consists of a map/ all entities coordinates etc.
	private Level level;

	public GameSystem() {
		//Setup level with default size
		this.level = new Level();
		System.out.println("aMMMMMMMMMMMazing times starting...");
	}

	//Places an entitiy in the coordinate specified:
	public boolean placeEntity(Entity entity, Coord coord) {
		return level.addEntity(entity, coord);
	}

	//Moves the player in a direction:
	public void movePlayer(Direction dir) {
		this.level.movePlayer(dir);
	}
	
	//Moves and updates all mobile entities:
	public void tick() {
		System.out.println("TickTock Goes The Clock");
		this.level.tick();
	}

	//Prints out a map:
	public String levelString() {
		return this.level.toString();
	}
	
	public boolean hasWon() {
		return this.level.hasWon();
	}
	
	public void setSwitchWinCondition(Boolean status) {
		this.level.setSwitchWinCondition(status);
	}
	
	public String inventoryString() {
		return this.level.inventoryString();
	}
	
	public boolean placeSwitch(Coord coord) {
		return this.level.placeSwitch(coord);
	}
    //Direction inputs:
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

}
