package gameModel;

public class Tile {
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
			entityMover.moveEntity(enemy, nextCoord);
			enemy.setLastMoveTickNum(tickNum);
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
		if (this.item != null) {
			this.player.pickup(this.item);
			this.removeItem();
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
			retVal |= enemy.kill();
		}
		if (this.player != null) {
			retVal |= player.kill();
		}
		return retVal;
	}
}
