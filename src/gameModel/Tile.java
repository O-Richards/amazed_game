package gameModel;

public class Tile {
	private final boolean DEBUG = false;
	
	private Coord coord;
	private UsableEntity item = null;
	private MobileEntity enemy = null;
	private PlayerMobileEntity player = null;
	private EntityMover entityMover;	
	
	public Tile(Coord coord, EntityMover entityMover) {
		this.coord = coord;
		this.entityMover = entityMover;
	}
		
	public void tick(int tickNum) {
		if (item != null) item.tick(tickNum);
		if (enemy != null) enemy.tick(tickNum);
		if (player != null) player.tick(tickNum);
		//move the enemy
		if (enemy != null && enemy.lastMoveTickNum() != tickNum) {
			Coord nextCoord = enemy.nextCoord();
			enemy.setLastMoveTickNum(tickNum);
			entityMover.moveEnemy(enemy, nextCoord);
		}
		if (player != null && player.lastMoveTickNum() != tickNum) {
			Coord nextCoord = player.nextCoord();
			player.setLastMoveTickNum(tickNum);
			entityMover.movePlayer(player, nextCoord);
		}
	}
	/**
	 * Adds a usable item to the tile: 
	 * @param item
	 * @throws EntityPlacementException
	 */
	public void addItem(UsableEntity item) throws EntityPlacementException {
		if (this.item != null) {
			throw new EntityPlacementException("Item on tile");
		}
		this.item = item; 
	}
	
	/**
	 * Add an enemy to a tile
	 * @param enemy
	 * @throws EntityPlacementException Thrown if there is an error in placing the enemy e.g. walking onto a closed door.
	 */
	public void addEnemy(MobileEntity enemy) throws EntityPlacementException {
		if(this.enemy != null) {
			throw new EntityPlacementException("Enemy on tile");
		}
		if (player != null) player.kill();
		this.enemy = enemy; 
		enemy.setCoord(this.getCoord());
	}
	
	/**
	 * @param player The player to be added to the tile
	 * @throws EntityPlacementException if there is an error placing the player e.g. walking onto a wall or locked door
	 */
	public void addPlayer(PlayerMobileEntity player) throws EntityPlacementException {
		if (this.player != null) {
			throw new EntityPlacementException("Player on tile");
		}
		this.player = player; 
		player.setCoord(this.getCoord());
		if (this.item != null) {
			this.player.pickup(this.item);
			this.removeItem();
		}
		if (this.enemy != null) {
			if (this.player.kill()) {
				this.player = null;
			}
		}
	}

	public void removeItem() {
		this.item = null;
	}

	public PlayerMobileEntity getPlayer() {
		return this.player;
		
	}
	
	public MobileEntity getEnemy() {
		return this.enemy;
		
	}
	public Entity getItem() {
		return this.item;
	}

	protected void updateWinCondition() {
		
	}
		
	public Coord getCoord() {
		return this.coord;
	}
	
	/**
	 * @return A simple char to represent the tile (for debugging)
	 */
	public String getSprite() {
		if (this.player != null) {
			return this.player.getSprite();
		} else if (this.enemy != null) {
			return this.enemy.getSprite();
		} else if (this.item != null) {
			return this.item.getSprite();
		} else {
			return " ";
		}
	}

	public boolean traversable() {
		return this.enemy == null;
	}
	
	public boolean kill() {
		boolean retVal = false;
		if (this.enemy != null) {
			boolean enemyKilled = enemy.kill();
			if (enemyKilled) this.enemy = null;
			retVal |= enemyKilled;
		}
		if (this.player != null) {
			boolean playerKilled = player.kill();
			if (playerKilled) this.player = null;
			retVal |= playerKilled;
		}
		return retVal;
	}

	/**
	 * @param e The entity to be removed from this tile
	 */
	public void removeEnemy(MobileEntity e) {
		if (e != this.enemy) {
			if (DEBUG) {
				System.out.println("Tile.removeEnemy: Warning: trying to remove enemy not on this tile");
			}
		} else {
			this.enemy = null;
		}
	}

	public void removePlayer(PlayerMobileEntity e) {
		if (e != this.player) {
			if (DEBUG) {
				System.out.println("Tile.removePlayer: Warning: trying to remove player not on this tile");
			}
		} else {
			this.player = null;
		}
	}
}
