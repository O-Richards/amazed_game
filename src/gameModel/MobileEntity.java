package gameModel;

public abstract class MobileEntity extends Entity implements Movement {
	public static final boolean DEBUG = false;
	private Movement movement;
	private Integer lastTick = -1;
	private int lastMoveTickNum = -1;
	private boolean alive = true;
	
	
	MobileEntity(Coord coord) {
		super(coord);
		this.movement = new EntityTrackingMovement(this);
	}

	MobileEntity(Coord coord, Movement movement) {
		super(coord);
		this.movement = movement;
	}
	
	@Override
	public void tick(Integer tickNum) {
		//Make sure to only move once per tick
		if (this.lastTick != tickNum) {
			this.lastTick = tickNum;
		}
	}
	
	public boolean kill() {
		if (this.canDie()) {
			//this.entityMover.removeEntity(this, this.getCoord());
			this.alive = false;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAlive() {
		return alive;
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
	
	public void setKeyCode(int keyCode) {
		keyCode = -1;
	}
	
	public int getKeyCode() {
		return -1;
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

	public int lastMoveTickNum() {
		return this.lastMoveTickNum;
	}

	public void setLastMoveTickNum(int tickNum) {
		this.lastMoveTickNum  = tickNum;
	}
}
