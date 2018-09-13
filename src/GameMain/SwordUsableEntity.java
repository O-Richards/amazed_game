package GameMain;

public class SwordUsableEntity extends Entity implements Usable {

	SwordUsableEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Direction direction, Tile[][] adjTiles) {
		Tile target;
		switch (direction) {
		case UP:
			target = adjTiles[1][0];
		case DOWN:
			target = adjTiles[1][2];
		case LEFT:
			target = adjTiles[0][1];
		case RIGHT:
			target = adjTiles[2][1];
		default:
			target = adjTiles[1][1];
		}
		target.addEntity(new KillerEntity(target, false, true));
	}
	
}
