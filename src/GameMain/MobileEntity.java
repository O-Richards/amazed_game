package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	
	MobileEntity(Tile tile, Movement movement) {
		super(tile);
		this.movement = movement;
	}

	private Movement movement;

	public boolean canFly() {
		return false;
	}
	
	public Coord nextCoord() {
		return this.movement.nextCoord();
	}


}
