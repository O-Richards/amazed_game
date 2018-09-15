package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	public static final boolean DEBUG = false;
	private Movement movement;
	
	MobileEntity(Tile tile) {
		super(tile);
		this.movement = new EntityTrackingMovement(this);
	}


	MobileEntity(Tile tile, Movement movement) {
		super(tile);
		this.movement = movement;
	}
	
	public Direction getDirection() {
		return this.movement.getDirection();
	}	
	
	public void setDirection(Direction dir) {
		this.movement.setDirection(dir);
		if (DEBUG) System.out.println("Setting MobileEntity " + this.getSprite() + "Direction to " + this.getDirection());
	}
	
	//TODO: Pull this into movement interface
	public abstract boolean pickup(UsableEntity item);

	public boolean canFly() {
		return this.movement.canFly();
	}	
	
	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	public boolean pushEntity() {
		return this.movement.pushEntity();
	}

	public Coord nextCoord() {
		return this.movement.nextCoord();
	}
	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	public Movement getMovement() {
		return this.movement;
	}
	public boolean canDie() {
		return true; 
	}
}
