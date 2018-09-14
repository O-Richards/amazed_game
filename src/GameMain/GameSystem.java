package GameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSystem {
	//Consists of a map/ all entities coordinates etc.
	private Level level;
	
	public GameSystem() {
		//Setup level with default size
		this.level = new Level();
		System.out.println("aMMMMMMMMMMMazing times starting...");
	}
	
	public boolean placeEntity(Entity entity, Coord coord) {
		return level.addEntity(entity, coord);
	}
	
	public void movePlayer(Direction dir) {
		this.level.movePlayer(dir);
	}
	
	public void tick() {
		System.out.println("TickTock Goes The Clock");
		this.level.tick();
	}
	
	public String levelString() {
		return this.level.toString();
	}
	
	public Direction charToDirection(Character c) {
		c = Character.toLowerCase(c);
		switch(c) {
			case 'w': return Direction.UP;
			case 's': return Direction.DOWN;
			case 'a': return Direction.LEFT;
			case 'd': return Direction.RIGHT;
			default: return Direction.CENTRE;
		}
	}
		
	public static void main(String[] args) throws IOException {
		GameSystem gs = new GameSystem();
		//Setup template maze
		gs.placeEntity(new BoulderMobileEntity(null), new Coord(2, 2));
		System.out.println("Use W A S D keys to move me around");
		while(true) {
			char c = (char)System.in.read();
			if (c == 0) break;
			gs.movePlayer(gs.charToDirection(new Character(c)));
			gs.tick();
			System.out.println(gs.levelString());
		}
	}
	



}
