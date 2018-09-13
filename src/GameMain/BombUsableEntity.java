package GameMain;

public class BombUsableEntity extends Entity implements Usable{

	BombUsableEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Tile[][] adjTiles) {
		// TODO Auto-generated method stub
		
	}

}
