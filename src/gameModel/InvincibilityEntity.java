package gameModel;

public class InvincibilityEntity extends UsableEntity {

	public InvincibilityEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		player.setMovement(new InvincibilityBonusAction(player.getMovement()));
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
