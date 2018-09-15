package GameMain;

public abstract class UsableEntity extends Entity {

	UsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
		}
		return Collision.MOVE;
	}
	
	public abstract void use(Direction playerDirection, Tile[][] adjTiles);
}
