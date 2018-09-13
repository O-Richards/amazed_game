package GameMain;

public class BoulderMobileEntity extends MobileEntity {

	BoulderMobileEntity(Tile tile) {
		super(tile);
		this.setDirection(Direction.CENTRE);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pushEntity()) {
			this.setDirection(hitter.getDirection());
		} else {
			this.setDirection(Direction.CENTRE);
		}
		//We return nomove, so the first keystroke pushes the boulder, the next the player
		return Collision.NOMOVE;
	}

	@Override
	public Coord nextCoord() {
		Coord next = this.getCoord(this.getDirection());
		this.setDirection(Direction.CENTRE);
		System.out.println("Boulder.nextCoord: returning " + next);
		return next;
	}

}
