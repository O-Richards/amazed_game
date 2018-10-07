package gameModel;

public class Tile {
	private final boolean DEBUG = true;

	private Coord coord;
	private Entity item = null;
	private MobileEntity mobile = null;
	private EntityMover entityMover;	

	public Tile(Coord coord, EntityMover entityMover) {
		this.coord = coord;
		this.entityMover = entityMover;
	}

	public void tick(int tickNum) {
		if (item != null) item.tick(tickNum);
		if (mobile != null) mobile.tick(tickNum);
		//move the enemy
		if (mobile != null && mobile.lastMoveTickNum() != tickNum) {
			Coord nextCoord = mobile.nextCoord();
			mobile.setLastMoveTickNum(tickNum);
			entityMover.moveMobileEntity(mobile, nextCoord);
		}
	}
	/**
	 * Adds a usable item to the tile: 
	 * @param item
	 * @throws EntityPlacementException
	 */
	public void addItem(Entity item) throws EntityPlacementException {
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
	public void addMobileEntity(MobileEntity newEntity) throws EntityPlacementException {
		MobileEntity oldEntity = this.mobile;
		boolean placeNewEntity = false;
		if (oldEntity == null) {
			placeNewEntity = true;
		} else {
			if (oldEntity.kill(newEntity.getKillAction())) {
				placeNewEntity = true;
				this.mobile = null;
			} 
			if (newEntity.kill(oldEntity.getKillAction())) {
				placeNewEntity = false;
			}
			if (oldEntity.pushable()) {
				if (this.entityMover.moveMobileEntity(oldEntity, newEntity.getDirection())) {
					placeNewEntity = true;
				} else {
					placeNewEntity = false;
				}
			} else {
				placeNewEntity = false;
			}
		}
		
		if (placeNewEntity) {
			newEntity.setCoord(this.getCoord());
			this.mobile = newEntity;
			if (this.item != null) {
				if (newEntity.pickup(this.item)) {
					this.item = null;
				}
			}
		}
		
	}

	public void removeItem() {
		this.item = null;
	}

	public MobileEntity getMobile() {
		return this.mobile;

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
		if (this.mobile != null) {
			return this.mobile.getSprite();
		} else if (this.item != null) {
			return this.item.getSprite();
		} else {
			return " ";
		}
	}

	public boolean traversable() {
		return this.mobile == null;
	}

	public boolean kill(KillAction action) {
		boolean retVal = false;
		if (this.mobile != null) {
			boolean enemyKilled = mobile.kill(action);
			if (enemyKilled) this.mobile = null;
			retVal |= enemyKilled;
		}
		return retVal;
	}

	/**
	 * @param e The entity to be removed from this tile
	 */
	public void removeMobileEntity(MobileEntity e) {
		if (e != this.mobile) {
			if (DEBUG) {
				System.out.println("Tile.removeEnemy: Warning: trying to remove entity not on this tile");
			}
		} else {
			this.mobile = null;
		}
	}
}
