package GameMain;

public class KeyUsableEntity extends UsableEntity {
	
	private static int keyCode = 1;

	KeyUsableEntity(Coord coord) {
		super(coord);
		this.keyCode++;
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.getKeyCode() == -1) {
			if (hitter.pickup(this)) {
				hitter.setKeyCode(keyCode);
				this.removeFromTile();
			}
		}
		return Collision.MOVE;
	}

	@Override
	public void use(Direction direction, Tile[][] adjTiles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSprite() {
		return "K";
	}
	
	

}
