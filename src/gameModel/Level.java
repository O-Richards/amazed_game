package gameModel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.border.TitledBorder;

import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.MobileEntity;
import gameModel.tile.DoorTile;
import gameModel.tile.EntityPlacementException;
import gameModel.tile.ExitTile;
import gameModel.tile.PitTile;
import gameModel.tile.SwitchTile;
import gameModel.tile.Tile;
import gameModel.tile.WallTile;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinSystem;
import gameModel.winCondition.WinType;

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

	public WinSystem getWinSystem() {
		return winSystem;
	}
	
	public EntityMover getEntityMover() {
		return this;
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
	
	/**
	 * Adds a usable item to the tile: 
	 * @param item The item to be placed
	 * @param coord the coord to place the item at
	 * @throws EntityPlacementException thrown if the placement is not allowed
	 */
	public void placeItem(Entity item) throws EntityPlacementException {
		Coord c = item.getCoord();
		Tile placementTile = getTile(c);
		placementTile.addItem(item);
		placementTile.notifyObservers();
	}
	
	/**
	 * Remove everything from a tile
	 * @param c The coord of the tile to be cleared
	 */
	public void clearTile(Coord c) {
		Tile t = this.getTile(c);
		t.clear();
	}
	
	/**
	 * Add an enemy to a tile
	 * @param enemy
	 * @param coord the coord to place the item at
	 * @throws EntityPlacementException Thrown if there is an error in placing the enemy e.g. walking onto a closed door.
	 */
	@Override
	public void placeMobileEntity(MobileEntity enemy) throws EntityPlacementException {
		Coord c = enemy.getCoord();
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

	/**
	 * @precondtion c is a valid coord i.e. on the map
	 * @param c The coord of the tile to fetch
	 * @return The tile at Coord c
	 */
	public Tile getTile(Coord c) {
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

	public VisType getVisType(Coord c) {
		Tile t = this.getTile(c);
		return t.getVisType();
	}

	public Integer getNumRows() {
		return this.map.length;
	}
	
	public Integer getNumCols() {
		return this.map[0].length;
	}

	/**
	 * @return True if the player has won the game, false else
	 */
	
	public boolean hasWon() {
		//We must update each win condition
		for (int row = this.map.length - 1; row >= 0; row--) {
			for (int col = 0; col < this.map[0].length; col++) {
				this.map[row][col].updateWinCondition();
			}
		}
		
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
		//SwitchTile newSwitch = new SwitchTile(coord, this.winSystem.newWinCondition(WinType.SWITCH), this);
		Tile oldTile = this.getTile(coord);
		SwitchTile newSwitch = new SwitchTile(oldTile, this.winSystem.newWinCondition(WinType.SWITCH)); 
		this.map[coord.getX()][coord.getY()] = newSwitch;
		return true;
	}

	/**
	 * @precondition The coord has an empty tile
	 * @precondition The coord is valid
	 * @param coord
	 */
	public void placeWall(Coord coord) {
		//Get's the coord of the current tile: 
		Tile oldTile = this.getTile(coord);
		WallTile newWall = new WallTile(oldTile); 
		//WallTile newWall = new WallTile(coord, this);
		this.map[coord.getX()][coord.getY()] = newWall;
	}

	public void placePit(Coord coord) {
		//PitTile newPit = new PitTile(coord, this);
		Tile oldTile = this.getTile(coord);
		PitTile newPit = new PitTile(oldTile); 
		this.map[coord.getX()][coord.getY()] = newPit;
	}

	public void placeExit(Coord coord) {
		//ExitTile newExit = new ExitTile(coord, this.winSystem.newWinCondition(WinType.EXIT), this);
		Tile oldTile = this.getTile(coord);
		ExitTile newExit = new ExitTile(oldTile,this.winSystem.newWinCondition(WinType.EXIT)); 
		this.map[coord.getX()][coord.getY()] = newExit;
	}
	
	public void placeDoor(Coord coord) {
		//DoorTile newDoor = new DoorTile(coord, this);
		Tile oldTile = this.getTile(coord);
		DoorTile newDoor = new DoorTile(oldTile); 
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
	
	public void disableWinCondition(WinType winType) {
		this.winSystem.disableWinCondition(winType);
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
		if (e.getCoord().equals(nextCoord)) {
			return true;
		}
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
	public void addDelayedAction(DelayedAction action, Integer numTicksUntil) {
		this.delayedActions.put(action, this.tickNum + numTicksUntil);
	}
}
