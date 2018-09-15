package GameMain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oli
 * @invariant map[][] always has at least 1 row and 1 column
 */
public class Level implements EntityMover {
	private static final boolean DEBUG = true;
	//Some constants
	private static final int DEFAULT_NROWS = 15;
	private static final int DEFAULT_NCOLS = 15;

	//The map for the game, composed of Tiles.
	//NOTE: Tile[0][0] is the bottom left tile
	private Tile[][] map;
	private PlayerMobileEntity player;
	private Integer tickNum = 0;

	public Level() {
		this(DEFAULT_NROWS, DEFAULT_NCOLS);
	}

	public Level(int nRows, int nCols) {

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
		Coord playerCoord = new Coord(1, 1);
		this.player = new PlayerMobileEntity(new Coord(1, 1));
		this.addEntity(player, playerCoord);
	}

	/**
	 * @param e The entity to be added
	 * @param c The coord to add the entity to
	 * @return True if successful, false otherwise (e.g. tried to add an item on a wall)
	 */
	public boolean addEntity(Entity e, Coord c) {
		Tile placementTile = getTile(c);
		e.setEntityMover(this);
		return placementTile.addEntity(e);
	}

	
	public void tick() {
		this.tickNum++;
		for (int row = 0; row < this.map.length; row++) {
			for (int col = 0; col < this.map[0].length; col++) {
				this.map[row][col].tick(tickNum);
			}
		}
	}

	public PlayerMobileEntity getPlayer() {
		return player;
	}
	
	/**
	 * @precondtion c is a valid coord i.e. on the map
	 * @param c The coord of the tile to fetch
	 * @return The tile at Coord c
	 */
	private Tile getTile(Coord c) {
		return this.map[c.getX()][c.getY()];
	}
	
	/**
	 * @precondtion c is a valid coord i.e. on the map
	 * @param c The coord of the tile to fetch
	 * @param dirFromC The direction of the tile wanted from c
	 * @return The tile at dirFromC from c. Returns null if the tile is off the map
	 */
	private Tile getTile(Coord c, Direction dirFromC) {
		//Check if we are going off the end of the map.
		Coord wantedCoord = c.add(dirFromC);
		if (wantedCoord.getX() >= this.map.length || wantedCoord.getY() >= this.map[0].length) {
			return null;
		} else {
			return this.getTile(wantedCoord);
		}
	}
	
	@Override
	public String toString() {
		//Could change this to be a string builder to avoid O(n^2)
		//The map should be quite small so it wont make a big difference
		String ret = new String("");
		for (int row = this.map.length - 1; row >= 0; row--) {
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
		if (DEBUG) {
			System.out.print("Level.hasWon() called: Player has " + player.noTreasure());
			System.out.print(" treasure, treasure win condition is " + enableTreasureWinCondition);
			System.out.print("Total treasure is " + this.noTreasure + "\n");
		}
		boolean ret = false;
		if (this.enableSwitchWinCondition)  {
			ret |= this.switchWinCondition.hasWon();
		}
		if (this.enableTreasureWinCondition) {
			ret |= this.treasureWinCondition.hasWon();
		}
		//Doesn't need to have switches or treasure? 
		if(this.enableExitsWinCondition) {
			ret |= this.exitsWinCondition.hasWon();
		}
		return ret;
	}
	
	public boolean hasLost() {
		return !this.player.isAlive();
	}
	

	/**
	 * @precondition The coord has an empty tile
	 * @precondition The coord is valid
	 * @param coord
	 * @return
	 */
	public boolean placeSwitch(Coord coord) {
		//TODO: add error checking
		SwitchTile newSwitch = new SwitchTile(coord, this.switchWinCondition);
		this.map[coord.getX()][coord.getY()] = newSwitch;
		return true;
	}

	public String inventoryString() {
		return this.player.inventoryString();
	}


	/**
	 * @precondition The coord has an empty tile
	 * @precondition The coord is valid
	 * @param coord
	 */
	public void placeWall(Coord coord) {
		WallTile newWall = new WallTile(coord);
		this.map[coord.getX()][coord.getY()] = newWall;
	}

	public void placePit(Coord coord) {
		PitTile newPit = new PitTile(coord);
		this.map[coord.getX()][coord.getY()] = newPit;
	}

	public void placeExit(Coord coord) {
		ExitTile newExit = new ExitTile(coord);
		this.map[coord.getX()][coord.getY()] = newExit;
	}
	
	@Override
	public Collision moveEntity(MobileEntity e, Direction dir) {
		Tile nextTile = this.getTile(e.getCoord(), dir);
		if (nextTile != null) {
			if (nextTile.collide(e) == Collision.MOVE) {
				e.removeFromTile();
				nextTile.addEntity(e);
				return Collision.MOVE;
			}
		}
		return Collision.NOMOVE;	
	}

	@Override
	public void removeEntity(Entity e, Coord c) {
		Tile currentTile = this.getTile(c);
		currentTile.removeEntity(e);
	}

	
	/* (non-Javadoc)
	 * @see GameMain.EntityMover#moveEntity(GameMain.MobileEntity, GameMain.Coord)
	 * Move the entity one tile closer to the nextCoord
	 */
	
	@Override
	public Collision moveEntity(MobileEntity e, Coord nextCoord) {
		if (DEBUG) System.out.println("Level.moveEntity moving " + e.getSprite());
		Collision result = Collision.NOMOVE;
		Direction xDir = e.getCoord().minusX(nextCoord);
		if (xDir != Direction.CENTRE) {
			result = this.moveEntity(e, xDir);
			if (result == Collision.MOVE) return result;
		}
		Direction yDir = e.getCoord().minusY(nextCoord);
		if (yDir != Direction.CENTRE) {
			result = this.moveEntity(e, yDir);
			if (result == Collision.MOVE) return result;
		}
		return result;
	}

	public void setEnableSwitchWinCondition(boolean enableSwitchWinCondition) {
		this.enableSwitchWinCondition = enableSwitchWinCondition;
	}

	public void setEnableTreasureWinCondition(boolean enableTreasureWinCondition) {
		this.enableTreasureWinCondition = enableTreasureWinCondition;
	}

	public void setEnableExitsWinCondition(boolean enableExitsWinCondition) {
		this.enableExitsWinCondition = enableExitsWinCondition;
	}
	
	

}
