package GameMain;

public class BoulderMovement extends EntityTrackingMovement {
	public BoulderMovement(Entity entity) {
		super(entity);
	}

	@Override
	public Coord nextCoord() {
		Coord next = this.getCoord(this.getDirection());
		this.setDirection(Direction.CENTRE);
		System.out.println("BoulderMovement.nextCoord returning" + next);
		return next;
	}
}
