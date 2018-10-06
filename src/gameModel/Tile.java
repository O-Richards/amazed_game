package gameModel;

public class Tile {
	private final boolean DEBUG = true;
	
	private Coord coord;
	private UsableEntity item = null;
	private MobileEntity enemy = null;
	private MobileEntity player = null;
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
			entityMover.moveMobileEntity(enemy, nextCoord);
		}
		if (player != null && player.lastMoveTickNum() != tickNum) {
			Coord nextCoord = player.nextCoord();
			player.setLastMoveTickNum(tickNum);
			entityMover.moveMobileEntity(player, nextCoord);
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
	
	public MobileEntity getPlayer() {
		return this.player;
	}
	
	/**
	 * Add an enemy to a tile
	 * @param enemy
	 * @throws EntityPlacementException Thrown if there is an error in placing the enemy e.g. walking onto a closed door.
	 */
	public void addMobileEntity(MobileEntity entity) throws EntityPlacementException {
		MobileEntity oldEntity = entity.isPlayer() ? this.player : this.getEnemy();
		if(oldEntity != null) {
			throw new EntityPlacementException("Tile is occupied");
		}
		entity.setCoord(this.getCoord());
		if (entity.isPlayer()) {
			if (DEBUG) System.out.println("Adding player to tile " + this.getCoord());
			this.player = entity;
		} else {
			this.enemy = entity;
			if (player != null) 
				if (player.kill()) {
					this.player = null;
			}
		}
		if (this.item != null) {
			if (entity.pickup(this.item)) {
				this.removeItem();
			}
		}
	}
	
	public void removeItem() {
		this.item = null;
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
	public void removeMobileEntity(MobileEntity e) {
		MobileEntity oldEntity = e.isPlayer() ? this.player : this.getEnemy();
		if (e != oldEntity) {
			if (DEBUG) {
				System.out.println("Tile.removeEnemy: Warning: trying to remove entity not on this tile");
			}
		} else {
			if (e.isPlayer()) {
				this.player = null;
			} else {
				this.enemy = null;
			}
		}
	}
}
