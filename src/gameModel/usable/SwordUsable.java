package gameModel.usable;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;

public class SwordUsable implements Usable {
	private int noOfUses = 5; 
	private MobileEntity player;
	private EntityMover entityMover;

	public SwordUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}

	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.SWORD && this.noOfUses > 0 && this.player != null) {
			Coord currentCoord = player.getCoord(player.getDirection());
			this.entityMover.kill(currentCoord, KillAction.WEAPON);
			this.noOfUses--;
			return noOfUses == 0 ? true : false;
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.player = player;
	}

	@Override
	public UseAction getUseAction() {
		return UseAction.SWORD;
	}
	
	@Override
	public boolean canBePickedUpWith(Usable u) {
		if (u.getUseAction() == this.getUseAction()) {
			return false;
		}
		return true;
	}
	

}
