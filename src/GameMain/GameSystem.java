package GameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSystem {
	private Level level;
	private Integer tickNum = 0;
	
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
	
	private void placeWall(Coord coord) {
		this.level.placeWall(coord);
	}
	
	private void placeDoor(Coord coord) {
		this.level.placeDoor(coord);
	}
	
	public void movePlayer(Direction dir) {
		System.out.println("GameSystem.movePlayer(" + dir + ") called");
		this.level.movePlayer(dir);
	}
	
	public void tick() {
		System.out.println("TickTock Goes The Clock");
		this.level.tick(tickNum++);
	}
	
	public boolean hasWon() {
		return this.level.hasWon();
	}
	
	public void setSwitchWinCondition(Boolean status) {
		this.level.setSwitchWinCondition(status);
	}
	
	private void setTreasureWinCondition(boolean b) {
		this.level.setTreasureWinCondition(b);
	}
	
	public String levelString() {
		return this.level.toString();
	}
	
	public String inventoryString() {
		return this.level.inventoryString();
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
		gs.placeEntity(new SwordUsableEntity(null), new Coord(4, 4));
		gs.placeEntity(new BoulderMobileEntity(null), new Coord(2, 3));
		gs.placeSwitch(new Coord(3, 3));
		gs.placeEntity(new TreasureEntity(null), new Coord(5,6));
		gs.placeEntity(new TreasureEntity(null), new Coord(10, 2));
		gs.placeWall(new Coord(4, 5));
		gs.placeEntity(new HoverPotion(null), new Coord(3, 4));
		gs.placeEntity(new KeyUsableEntity(null), new Coord(2,5));
		gs.placeEntity(new KeyUsableEntity(null), new Coord(7,8));
		gs.placeDoor(new Coord(4,1));
		gs.setSwitchWinCondition(true);
		gs.setTreasureWinCondition(true);
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
