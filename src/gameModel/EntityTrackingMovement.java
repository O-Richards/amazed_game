package gameModel;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = false;
	private Direction direction = Direction.CENTRE;
	private Entity entity;

	public EntityTrackingMovement(Entity e) {
		this.entity = e;
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
	public boolean kill() {
		if (this.canDie()) {
			this.entity.setAlive(false);
			return true;
		} else {
			return false;
		}
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
	public boolean pickup(Entity e) {
		return false;
	}

}
