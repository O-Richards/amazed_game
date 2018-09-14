package GameMain;

public class InvincibilityEntity extends Entity {

	InvincibilityEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		removeFromTile();
		hitter.setMovement(new InvincibilityBonusAction(hitter.getMovement()));
		return Collision.MOVE;
	}

	@Override
	public String getSprite() {
		return "I";
	}
	
	

}
