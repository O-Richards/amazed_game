package GameMain;

public abstract class MovementBonus implements Movement {
	private Movement baseMovement;
	
	public MovementBonus(Movement baseMovement) {
		this.baseMovement = baseMovement;
	}

	@Override
	public Coord nextCoord() {
		return this.baseMovement.nextCoord();
	}

	@Override
	public Direction getDirection() {
		return this.baseMovement.getDirection();
	}

	@Override
	public void setDirection(Direction dir) {
		this.baseMovement.setDirection(dir);
	}

	@Override
	public Coord getCoord() {
		return this.baseMovement.getCoord();
	}
	
	@Override
	public Coord getCoord(Direction dir) {
		return this.baseMovement.getCoord(dir);
	}
}
