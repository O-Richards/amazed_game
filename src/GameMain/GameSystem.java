package GameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	
	public static void main(String[] args) {
		GameSystem gs = new GameSystem();
		System.out.println(gs.levelString());
		//gs.tick();
		gs.movePlayer(Direction.DOWN);
		System.out.println(gs.levelString());
		gs.tick();
		System.out.println(gs.levelString());
	}
	



}
