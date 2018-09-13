package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	
	MobileEntity(Tile tile) {
		super(tile);
	}
	
	public boolean canFly() {
		return false;
	}
	
	public abstract Coord nextCoord();


}
