package GameMain;

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
	
	private void placeWall(Coord coord) {
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
	

	private void setTreasureWinCondition(boolean b) {
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
	 //Gets the action:  
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
	public static void main(String[] args) throws IOException {
		GameSystem gs = new GameSystem();
		//Setup template maze
		gs.placeEntity(new SwordUsableEntity(null), new Coord(4, 4));
		gs.placeEntity(new UnlitBombUsableEntity(null), new Coord(1, 5));
		gs.placeEntity(new BoulderMobileEntity(null), new Coord(2, 3));
		gs.placeSwitch(new Coord(3, 3));
		gs.placeEntity(new TreasureEntity(null), new Coord(5,6));
		gs.placeEntity(new TreasureEntity(null), new Coord(10, 2));
		gs.placeWall(new Coord(4, 5));
		gs.placeEntity(new HoverPotion(null), new Coord(3, 4));
		gs.placeEntity(new InvincibilityEntity(null), new Coord(1,5));
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
>>>>>>> master
		}
	}


	//Does the actual action: 
	public void doAction(Action act,Direction dir) {
		this.level.playerDo();
	}
}
