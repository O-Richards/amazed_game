package gameModel;


public class SwordUsable implements Usable {
	private int noOfUses = 5; 
	private MobileEntity player;
	private EntityMover entityMover;

	public SwordUsable(EntityMover entityMover) {
	}

	@Override
	public boolean use(Action action) {
		if (action == Action.SWORD && this.noOfUses > 0 && this.player != null) {
			Coord currentCoord = player.getCoord(player.getDirection());
			this.entityMover.kill(currentCoord, KillAction.WEAPON);
			this.noOfUses--;
			return true;
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.player = player;
	}

}
