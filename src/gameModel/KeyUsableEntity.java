package gameModel;

public class KeyUsableEntity extends UsableEntity {

private static int keyCodeGenerator = 1;
	
	private static synchronized int generateKeyCode() {
		return keyCodeGenerator++;
	}
	
	private int keyCode;

	public KeyUsableEntity(Coord coord) {
		super(coord);
		this.keyCode = KeyUsableEntity.generateKeyCode();
	}
	
	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		player.setKeyCode(keyCode);
	}

	@Override
	public String getSprite() {
		return "K";
	}
}
