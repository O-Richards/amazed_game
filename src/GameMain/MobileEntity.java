package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	public static final boolean DEBUG = false;
	private Movement movement;
	private Integer lastTick = 0;
	
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
			this.move();
		}
	}
	
	public void move() {
		Coord nextCoord = this.nextCoord();
		if (DEBUG) System.out.println("Moving sprite " + this.getSprite() + " to " + nextCoord);
		this.entityMover.moveEntity(this, nextCoord);
	}
	
	/**
	 * Move to a specified coord
	 * @param c The coord to move to
	 */
	public void move(Coord c) {
		this.entityMover.moveEntity(this, c);
	}
	
	@Override
	public Direction getDirection() {
		return this.movement.getDirection();
	}	
	
	@Override
	public void setDirection(Direction dir) {
		this.movement.setDirection(dir);
		if (DEBUG) System.out.println("Setting MobileEntity " + this.getSprite() + "Direction to " + this.getDirection());
	}
	
	//TODO: Pull this into movement interface
	public abstract boolean pickup(UsableEntity item);
	
	@Override
	public boolean canFly() {
		return this.movement.canFly();
	}	
	
	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	@Override
	public boolean pushEntity() {
		return this.movement.pushEntity();
	}
	
	@Override
	public Coord nextCoord() {
		return this.movement.nextCoord();
	}
	
	@Override
	public void setSpeed(Integer speed) {
		this.movement.setSpeed(speed);
	}

	@Override
	public Integer getSpeed() {
		return this.movement.getSpeed();
	}

	
	public void setKeyCode(int keyCode) {
		keyCode = -1;
	}
	
	public int getKeyCode() {
		return -1;
	}
	

	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	public Movement getMovement() {
		return this.movement;
	}
	
	public abstract boolean killPlayer();
	
	public abstract boolean killEnemy();
}
