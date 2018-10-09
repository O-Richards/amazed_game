package gameModel;


public class SwordUsable implements Usable {
	private int noOfUses = 5; 
	private MobileEntity player;
	private EntityMover entityMover;

	public SwordUsable(EntityMover entityMover) {
	}

	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.SWORD && this.noOfUses > 0 && this.player != null) {
			Coord currentCoord = player.getCoord(player.getDirection());
			this.entityMover.kill(currentCoord, KillAction.WEAPON);
			this.noOfUses--;
			return false;
		}
		return true;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.player = player;
	}

	@Override
	public UseAction getUseAction() {
		return UseAction.SWORD;
	}

}
