package GameMain;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = false;
	Direction direction = Direction.CENTRE;
	MobileEntity entity;
	Integer speed = 0;

	public EntityTrackingMovement(MobileEntity entity) {
		super();
		this.entity = entity;
	}
	
	public Coord nextCoord() {
		if (this.getSpeed() > 0) {
			return this.getCoord(this.getDirection());
		} else {
			return this.getCoord();
		}
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

	@Override
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public Integer getSpeed() {
		return this.speed;
	}

}
