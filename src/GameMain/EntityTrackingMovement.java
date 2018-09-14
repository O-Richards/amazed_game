package GameMain;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = true;
	Direction direction;
	Entity entity;

	public EntityTrackingMovement(Entity entity) {
		super();
		this.entity = entity;
	}
	
	public Coord nextCoord() {
		return this.entity.getCoord(direction);
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction dir) {
		if (DEBUG) System.out.println("Setting direction to " + dir);
		this.direction = dir;
	}

	@Override
	public Coord getCoord() {
		return this.entity.getCoord();
	}

	@Override
	public Coord getCoord(Direction dir) {
		return this.entity.getCoord(dir);
	}
}
