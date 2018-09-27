package gameModel;

public abstract class Tile {
	private Coord coord;
	private WinCondition enemyCondition;
	private UsableEntity item = null;
	private MobileEntity enemy = null;
	private PlayerMobileEntity player = null;
	
	public Tile(Coord coord, WinCondition enemyCondition) {
		this.coord = coord;
		this.enemyCondition = enemyCondition;
	}
	
	public void tick(int tickNum) {
		item.tick(tickNum);
		enemy.tick(tickNum);
		player.tick(tickNum);
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
	
	public void addEnemy(MobileEntity enemy) throws EntityPlacementException {
		if(this.enemy != null) {
			throw new EntityPlacementException("Enemy on tile");
		}
		this.enemy = enemy; 
	}
	
	public void addPlayer(PlayerMobileEntity player) throws EntityPlacementException {
		if(this.player != null) {
			throw new EntityPlacementException("Player on tile");
		}
		this.player = player; 
	}
	
	public MobileEntity getEnemy() {
		return this.enemy;
		
	}
	public Entity getItem() {
		return this.item;
	}

	/**
	 * Collide the hitter with the Tile. MobileHitters have a bunch of methods
	 * that can be called e.g. canFly(), pickupUsable() etc.
	 * The general idea is that the mobile entities will collide with a tile
	 * when they try to move onto it, which will then cause them to collide with
	 * each entity on the tile. (Think Composition Pattern)
	 * @param hitter The mobile entity that is walking into the collidable object
	 * @return MOVE if the movement is possible, NOMOVE if the movement is blocked
	 */
	protected void updateEnemyCondition() {
		if (this.enemy == null) {
			this.enemyCondition.setType(WinType.WIN);
		} else {
			this.enemyCondition.setType(WinType.ENEMY);
		}
	}
	
	protected abstract void updateWinCondition();
	
	public Coord getCoord() {
		return this.coord;
	}
	
	public String getSprite() {
		if (this.enemy != null) {
			return this.enemy.getSprite();
		} else if (this.item != null) {
			return this.item.getSprite();
		} else {
			return " ";
		}
	}

	/**
	 * Remove all enemy entities from the tile
	 * Use this for bombs, swords etc.
	 * @Return true if an item is removed from tile false otherwise: 
	 */
	public void killEnemyEntity() {
		this.enemy = null;
	}
}

class EntityPlacementException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8634948987228608288L;

	public EntityPlacementException(String msg) {
		super(msg);
	}
}
