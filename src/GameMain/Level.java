package GameMain;

import java.util.ArrayList;
import java.util.List;

public class Level {
	private static final boolean DEBUG = false;
	//Some constants
	private static final int DEFAULT_NROWS = 30;
	private static final int DEFAULT_NCOLS = 30;
	
	//The map for the game, composed of Tiles.
	//NOTE: Tile[0][0] is the top left tile
	private Tile[][] map;
	private WinCondition switchWinCondition;
	private boolean hasSwitchWinCondition;
	private PlayerMobileEntity player;
	private List<MobileEntity> mobileEntities;
	
	public Level() {
		this(DEFAULT_NROWS, DEFAULT_NCOLS);
	}
	
	public Level(int nRows, int nCols) {
		this.switchWinCondition = new SwitchWinCondition();
		this.mobileEntities = new ArrayList<>();
		this.hasSwitchWinCondition = false;
		
		//Adds a border of wall tiles to the map.
		this.map = new Tile[nRows + 2][nCols + 2];
		for (int row = 1; row < nRows + 1; row++) {
			for (int col = 1; col < nCols + 1; col++) {
				this.map[row][col] = new Tile(new Coord(row, col));
			}
		}
		//Add bordering walls
		for (int row = 0; row < nRows + 2; row++) {
			this.map[row][0] = new EdgeTile(new Coord(row, 0));
			this.map[row][nRows + 1] = new EdgeTile(new Coord(row, nRows + 1));
		}
		for (int col = 0; col < nCols + 2; col++) {
			this.map[0][col] = new EdgeTile(new Coord(0, col));
			this.map[nRows + 1][col] = new EdgeTile(new Coord(nRows + 1, col));
		}
		//Create the player and place them on the map
		this.player = new PlayerMobileEntity(this.map[1][1]);
		moveMobileEntity(this.player, new Coord(1, 1));
	}
	
	public void moveMobileEntity(MobileEntity entity, Coord c) {
		Tile newTile = this.map[c.getX()][c.getY()];
		//Trigger any/all collisions
		if (DEBUG) System.out.println("Moving Mobile Entity " + entity.getSprite() + " to " + c);
		if (newTile.collide(entity) == Collision.MOVE) {
			entity.removeFromTile();
			newTile.addEntity(entity);
		}
	}
	
	/**
	 * @param e The entity to be added
	 * @param c The coord to add the entity to
	 * @return True if successful, false otherwise (e.g. tried to add an item on a wall)
	 */
	public boolean addEntity(Entity e, Coord c) {
		Tile placementTile = getTile(c);
		//TODO: Make this not crap (will need a refactor)
		if (e instanceof MobileEntity) {
			this.mobileEntities.add((MobileEntity)e);
		}
		return placementTile.addEntity(e);
	}
	
	public void tick() {
		this.switchWinCondition.tick();
		for (int row = 0; row < this.map.length; row++) {
			for (int col = 0; col < this.map[0].length; col++) {
				this.map[row][col].tick();
			}
		}
		//Move mobile entities
		moveMobileEntity(this.player, this.player.nextCoord());
		for (MobileEntity e: this.mobileEntities) {
			moveMobileEntity(e, e.nextCoord());
		}
	}

	public PlayerMobileEntity getPlayer() {
		return player;
	}
	
	private Tile getTile(Coord c) {
		return this.map[c.getX()][c.getY()];
	}
	
	@Override
	public String toString() {
		//Could change this to be a string builder to avoid O(n^2)
		//The map should be quite small so it wont make a big difference
		String ret = new String("");
		for (int row = 0; row < this.map.length; row++) {
			for (int col = 0; col < this.map[0].length; col++) {
				ret += this.map[row][col].getSprite();
			}
			ret += "\n";
		}
		return ret;
	}

	public void movePlayer(Direction dir) {
		//TODO: Add error checking
		if (DEBUG) System.out.println("System setting player dir: " + dir);
		this.player.setDirection(dir);
		if (DEBUG) System.out.println("System set player dir: " + this.player.getDirection());

	}
	
	public boolean hasWon() {
		boolean ret = false;
		if (this.hasSwitchWinCondition)  {
			ret |= this.switchWinCondition.hasWon();
		}
		return ret;
	}

	public void setSwitchWinCondition(Boolean status) {
		this.hasSwitchWinCondition = status;
	}

	public boolean placeSwitch(Coord coord) {
		//TODO: add error checking
		SwitchTile newSwitch = new SwitchTile(coord, this.switchWinCondition);
		this.map[coord.getX()][coord.getY()] = newSwitch;
		return true;
	}
	
	public String inventoryString() {
		return this.player.inventoryString();
	}
	
}
