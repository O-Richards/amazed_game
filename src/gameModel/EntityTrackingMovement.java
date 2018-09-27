package gameModel;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = false;
	private Direction direction = Direction.CENTRE;
	private MobileEntity entity;

	public EntityTrackingMovement(MobileEntity entity) {
		super();
		this.entity = entity;
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

	@Override
	public Coord getCoord() {
		return this.entity.getCoord();
	}

	@Override
	public Coord getCoord(Direction dir) {
		return this.entity.getCoord(dir);
	}

}
