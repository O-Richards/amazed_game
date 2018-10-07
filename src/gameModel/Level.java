package gameModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oli
 * @invariant map[][] always has at least 1 row and 1 column
 */
/**
 * @author Alexander
 *
 */
public class Level implements EntityMover {
	private static final boolean DEBUG = true;
	//Some constants
	private static final int DEFAULT_NROWS = 15;
	private static final int DEFAULT_NCOLS = 15;

	//The map for the game, composed of Tiles.
	//NOTE: Tile[0][0] is the bottom left tile
	private Tile[][] map;

	private Integer tickNum = 0;
	private WinSystem winSystem;
	private Map<DelayedAction, Integer> delayedActions = new HashMap<>();

	public Level() {
		this(DEFAULT_NROWS, DEFAULT_NCOLS);
	}

	public Level(int nRows, int nCols) {
		this.winSystem = new WinSystem();
		//Adds a border of wall tiles to the map.
		this.map = new Tile[nRows + 2][nCols + 2];
		for (int row = 1; row < nRows + 1; row++) {
			for (int col = 1; col < nCols + 1; col++) {
				this.map[row][col] = new Tile(new Coord(row, col), this);
			}
		}
		//Add bordering walls
		for (int row = 0; row < nRows + 2; row++) {
			this.map[row][0] = new WallTile(new Coord(row, 0), this);
			this.map[row][nRows + 1] = new WallTile(new Coord(row, nRows + 1), this);
		}
		for (int col = 0; col < nCols + 2; col++) {
			this.map[0][col] = new WallTile(new Coord(0, col), this);
			this.map[nRows + 1][col] = new WallTile(new Coord(nRows + 1, col), this);
		}
		
	}

	public PlayerMobileEntity addPlayer(Coord c) throws EntityPlacementException {
		Tile placementTile = this.getTile(c);
		PlayerMobileEntity player = PlayerMobileEntity.build();
		player.setCoord(c);
		placementTile.addMobileEntity(player);
		return player;
	}
	
	/**
	 * Adds a usable item to the tile: 
	 * @param item The item to be placed
	 * @param coord the coord to place the item at
	 * @throws EntityPlacementException thrown if the placement is not allowed
	 */
	public void addItem(EntityBuilder itemBuilder, Coord c) throws EntityPlacementException {
		Entity item = itemBuilder.withCoord(c)
			.withEntityMover(this)
			.build();
		Tile placementTile = getTile(c);
		placementTile.addItem(item);
	}
	
	/**
	 * Add an enemy to a tile
	 * @param enemy
	 * @param coord the coord to place the item at
	 * @throws EntityPlacementException Thrown if there is an error in placing the enemy e.g. walking onto a closed door.
	 */
	public void addEnemy(MobileEntity enemy, Coord c) throws EntityPlacementException {
		Tile placementTile = getTile(c);
		placementTile.addMobileEntity(enemy);
	}
	
	public void tick() {
		//update callbacks
		for (Map.Entry<DelayedAction, Integer> entry : this.delayedActions.entrySet()) {
			if (entry.getValue() == tickNum) {
				entry.getKey().performDelayedAction();
			}
		}
		
		for (int row = 0; row < this.map.length; row++) {
			for (int col = 0; col < this.map[0].length; col++) {
				this.map[row][col].tick(tickNum);
			}
		}
		this.tickNum++;
	}
	
	public WinCondition newWinCondition(WinType type) {
		return this.winSystem.newWinCondition(type);
	}
	
	public EntityMover getEntityMover() {
		return this;
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


	/**
	 * @return True if the player has won the game, false else
	 */
	/*
	 * 
	//TODO: Remove this
	public void playerDo(Action act,Direction dir) {
		//TODO: Add error checking
		if (DEBUG) System.out.println("Setting up action in  " + dir + "direction using a " + act);
		//this.player.useItem(act, dir);
		//Sets the direction:
		this.player.setDirection(dir);
		//Calls the appropriate action:
		if(act == Action.SWORD) {
			this.player.useItem(new SwordUsableEntity(this.player.getCoord()));
		}else if(act == Action.ARROW){
			this.player.useItem(new ArrowUsableEntity(this.player.getCoord()));
		}else if(act == Action.BOMB) {
			this.player.useItem(new BombUsableEntity(this.player.getCoord()));
		}else {
			//Function to consume item?
		}
		if (DEBUG) System.out.println("System set player action: " + this.player.getDirection());
	}*/
	
	public boolean hasWon() {
		return this.winSystem.getType() == WinType.WIN;
	}

	/**
	 * @precondition The coord has an empty tile
	 * @precondition The coord is valid
	 * @param coord
	 * @return
	 */
	public boolean placeSwitch(Coord coord) {
		//TODO: add error checking
		SwitchTile newSwitch = new SwitchTile(coord, this.winSystem.newWinCondition(WinType.SWITCH), this);
		this.map[coord.getX()][coord.getY()] = newSwitch;
		return true;
	}

	/**
	 * @precondition The coord has an empty tile
	 * @precondition The coord is valid
	 * @param coord
	 */
	public void placeWall(Coord coord) {
		WallTile newWall = new WallTile(coord, this);
		this.map[coord.getX()][coord.getY()] = newWall;
	}

	public void placePit(Coord coord) {
		PitTile newPit = new PitTile(coord, this);
		this.map[coord.getX()][coord.getY()] = newPit;
	}

	public void placeExit(Coord coord) {
		ExitTile newExit = new ExitTile(coord, this.winSystem.newWinCondition(WinType.EXIT), this);
		this.map[coord.getX()][coord.getY()] = newExit;
	}
	
	public void placeDoor(Coord coord) {
		DoorTile newDoor = new DoorTile(coord, this);
		this.map[coord.getX()][coord.getY()] = newDoor;
	}
	
	/**
	 * @precondition To enable SWITCH : must be switch on map
	 * @precondition To enable EXIT : must be exit on map
	 * @precondition To enable ENEMY : must be ENEMY on map
	 * @precondition To enable TREASURE : must be treasure on map
	 * @param winType
	 */
	public void enableWinCondition(WinType winType) {
		this.winSystem.enableWinCondition(winType);
	}

	/* (non-Javadoc)
	 * @see gameModel.EntityMover#moveEntity(gameModel.MobileEntity, gameModel.Direction)
	 */
	@Override
	public boolean moveMobileEntity(MobileEntity e, Direction dir) {
		Tile newTile = this.getTile(e.getCoord(), dir);
		if (newTile == null) {
			System.out.println("Level.moveEntity: warning moving Entity " + e.toString() + "To an invalid coord");
			return false;
		}
		return this.moveMobileEntity(e, newTile.getCoord());
	}

	/**
	 * @param e the enemy to be moved
	 * @param nextCoord the coord to move the enemy to
	 * @return true if the entity is moved to the new tile. false else
	 * @precondition e != null
	 * @precondition nextCoord is in the map 
	 */
	@Override
	public boolean moveMobileEntity(MobileEntity e, Coord nextCoord) {
		Tile placementTile = this.getTile(nextCoord);
		Tile originalTile = this.getTile(e.getCoord());
		try {
			placementTile.addMobileEntity(e);
			originalTile.removeMobileEntity(e);
			
		} catch (EntityPlacementException exp){
			//The entity cannot be placed here for some reason
			if (DEBUG) {
				System.out.println("Level.moveEnemy: Placing enemy received exception: " + exp.toString());
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean traversable(Coord c) {
		Tile movementTile = this.getTile(c);
		return movementTile.traversable();
	}

	@Override
	public boolean kill(Coord c, KillAction action) {
		Tile tile = this.getTile(c);
		return tile.kill(action);
	}

	@Override
	public void placeEntity(Entity entity, Coord c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDelayedAction(DelayedAction action, Integer numTicksUntil) {
		this.delayedActions.put(action, this.tickNum + numTicksUntil);
	}
}
