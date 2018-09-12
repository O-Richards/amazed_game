package GameMain;

public abstract class MobileEntity extends Entity implements Movement {
	private Movement movement;
	
	public MobileEntity(Tile tile) {
		super(tile);
	}

	@Override
	public Coord nextCoord(PlayerMobileEntity target) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
