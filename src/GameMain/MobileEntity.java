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
	
	public abstract Coord nextCoord();


}
