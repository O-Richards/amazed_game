package GameMain;

public class KeyUsableEntity extends UsableEntity {

	KeyUsableEntity(Tile tile) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int noOfUsesLeft() {
		// TODO Auto-generated method stub
		return 0;//<- does a key have no of uses? 
	}

}
