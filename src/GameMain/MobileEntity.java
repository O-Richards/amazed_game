package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	
	MobileEntity(Tile tile) {
		super(tile);
	}

	private Direction direction;

	public boolean canFly() {
		return false;
	}
	
	public Direction getDirection() {
		return direction;
	}	
	
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	
	public abstract Coord nextCoord();

	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	public boolean pushEntity() {
		return false;
	}


}
