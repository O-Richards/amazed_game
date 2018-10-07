package gameModel;

public class BoulderMovement extends EntityTrackingMovement {
	public BoulderMovement() {
		super();
	}

	@Override
	public Coord nextCoord() {
		return this.getCoord();
	}
}
