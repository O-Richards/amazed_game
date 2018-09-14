package GameMain;

public class BoulderMobileEntity extends MobileEntity {

	BoulderMobileEntity(Tile tile) {
		super(tile);
		this.setMovement(new BoulderMovement(this));
		this.setDirection(Direction.CENTRE);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pushEntity()) {
			System.out.println("Setting direction" + hitter.getDirection());
			this.setDirection(hitter.getDirection());
		} else {
			this.setDirection(Direction.CENTRE);
		}
		//We return nomove, so the first keystroke pushes the boulder, the next the player
		return Collision.NOMOVE;
	}
	
	@Override
	public String getSprite() {
		return "B";
	}

}
