package GameMain;

public class BoulderMovement extends EntityTrackingMovement {
	public BoulderMovement(MobileEntity entity) {
		super(entity);
	}

	@Override
	public Coord nextCoord() {
		return this.getCoord();
	}
}
