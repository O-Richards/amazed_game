package GameMain;

public class BombUsableEntity extends UsableEntity {

	BombUsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Direction direction, Tile[][] adjTiles) {
		// TODO Auto-generated method stub
	}

}
