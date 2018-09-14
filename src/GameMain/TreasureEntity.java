package GameMain;

public class TreasureEntity extends Entity implements Usable {
	
	TreasureEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.MOVE;
	}
	
	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String getSprite() {
		return "$";
	}


}
