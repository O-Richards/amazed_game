package gameModel;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = false;
	private Direction direction = Direction.CENTRE;
	private MobileEntity entity;

	public EntityTrackingMovement() {
	}
	
	public Coord nextCoord() {
		return this.getCoord(this.getDirection());
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction dir) {
		if (DEBUG) System.out.println("Setting direction to " + dir);
		this.direction = dir;
	}
	
	/**
	 * @return true if the entity is killed, false else.
	 */
	@Override
	public boolean kill(KillAction action) {
		if (this.canDie()) {
			return true;
		}
		return false;
	}

	@Override
	public Coord getCoord() {
		return this.entity.getCoord();
	}

	@Override
	public Coord getCoord(Direction dir) {
		return this.entity.getCoord(dir);
	}

	@Override
	public boolean pickup(Usable e) {
		return false;
	}

	@Override
	public void setMobileEntity(MobileEntity mobileEntity) {
		this.entity = mobileEntity;
	}

}
