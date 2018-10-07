package gameModel;

public class BoulderMovement extends EntityTrackingMovement {
	public BoulderMovement(Entity entity) {
		super(entity);
	}

	@Override
	public Coord nextCoord() {
		return this.getCoord();
	}
}
