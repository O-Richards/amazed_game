package GameMain;

public class InvincibilityEntity extends UsableEntity {

	InvincibilityEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
			//Applying decorator pattern to the player's movements
			//i.e. we wrap the player's movement with a hover bonus
			hitter.setMovement(new InvincibilityBonusAction(hitter.getMovement()));
		}
		return Collision.MOVE;
	}
	

	@Override
	public String getSprite() {
		return "I";
	}

	@Override
	public Boolean use(Direction playerDirection) {
		return false;
	}
	

}
