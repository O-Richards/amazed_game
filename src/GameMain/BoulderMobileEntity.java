package GameMain;

public class BoulderMobileEntity extends MobileEntity {

	BoulderMobileEntity(Coord coord) {
		super(coord);
		this.setMovement(new BoulderMovement(this));
		this.setDirection(Direction.CENTRE);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter == this) return Collision.NOMOVE;
		if (hitter.pushEntity()) {
			this.move(this.getCoord(hitter.getDirection()));
		} else {
		}
		//We return nomove, so the first keystroke pushes the boulder, the next the player
		return Collision.NOMOVE;
	}
	
	@Override
	public String getSprite() {
		return "B";
	}

	@Override
	public boolean pickup(UsableEntity item) {
		return false;
	}

	@Override
	public void setKeyCode(int keyCode) {
		keyCode = -1;
	}

	@Override
	public int getKeyCode() {
		return -1;
	}
	
	

}
