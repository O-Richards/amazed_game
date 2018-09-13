package GameMain;

public class AutoMobileEntity extends MobileEntity {
	private Movement movement;
	
	AutoMobileEntity(Tile tile) {
		super(tile);
		throw new UnsupportedOperationException("Not supported yet.");
		//this.movement = new StaticMovement();
	}
	
	AutoMobileEntity(Tile tile, Movement movement) {
		super(tile);
		this.movement = movement;
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		throw new UnsupportedOperationException("Not supported yet.");
		//return null;
	}

	@Override
	public Coord nextCoord() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
		//return null;
	}

}
