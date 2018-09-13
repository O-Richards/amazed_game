package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	
	MobileEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	private Movement movement;

	public boolean canFly() {
		return false;
	}
	
	@Override
	public Coord nextCoord(PlayerMobileEntity target) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
