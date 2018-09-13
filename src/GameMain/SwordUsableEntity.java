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
	public void use(Tile[][] adjTiles) {
		// TODO Auto-generated method stub
		
	}

}
