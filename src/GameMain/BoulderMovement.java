package GameMain;

public class BoulderMovement extends EntityTrackingMovement {
	public BoulderMovement(MobileEntity entity) {
		super(entity);
	}

	@Override
	public Coord nextCoord() {
		Coord ret = super.nextCoord();
		this.setDirection(Direction.CENTRE);
		return ret;
	}
}
