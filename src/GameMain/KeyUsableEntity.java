package GameMain;

public class KeyUsableEntity extends UsableEntity {

private static int keyCodeGenerator = 1;
	
	private static synchronized int generateKeyCode() {
		return keyCodeGenerator++;
	}
	
	private int keyCode;

	KeyUsableEntity(Coord coord) {
		super(coord);
		this.keyCode = KeyUsableEntity.generateKeyCode();
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
	public String getSprite() {
		return "K";
	}
}
