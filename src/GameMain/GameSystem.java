package GameMain;

import java.io.IOException;

public class GameSystem {
	//Consists of a map/ all entities coordinates etc.
	private Level level;

	private Integer tickNum = 0;
	
	public GameSystem() {
		//Setup level with default size
		this.level = new Level();
		System.out.println("aMMMMMMMMMMMazing times starting...");
	}

	//Places an entity in the coordinate specified:
	public boolean placeEntity(Entity entity, Coord coord) {
		return level.addEntity(entity, coord);
	}
	
	public boolean placeSwitch(Coord coord) {
		return this.level.placeSwitch(coord);
	}
	
	public void placeWall(Coord coord) {
		this.level.placeWall(coord);
	}

	public void movePlayer(Direction dir) {
		this.level.movePlayer(dir);
	}
	
	public PlayerMobileEntity getPlayer() {
		return level.getPlayer();
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
	

	public void setTreasureWinCondition(Boolean b) {
		this.level.setTreasureWinCondition(b);
	}
	

	public String levelString() {
		return this.level.toString();
	}
	

	public String inventoryString() {
		return this.level.inventoryString();
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
	

	


	
	//Get action: 
	public void performAction(String input) {
		//Check if the player has made an actual movement: 
		Direction playerDir = this.isAction(input);
		if(playerDir != null) {
			//Get the action the player has made: 
			Action playerAction = this.action(input);		
			doAction(playerAction, playerDir);
		}
	}
	
	//Gets the action: i.e. represented by the last char: 
	public Action action(String s) {
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
	public Direction isAction(String s) {
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
	//Does the actual action: 
	public void doAction(Action act,Direction dir) {
		throw new UnsupportedOperationException("Player actions net yet supported");
		//this.level.playerDo();
	}
}
