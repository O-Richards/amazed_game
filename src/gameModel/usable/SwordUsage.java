package gameModel.usable;

import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;

public class SwordUsage implements Usable {
	private EntityMover entityMover;
	private MobileEntity user;
	private Integer numUses;
	
	public SwordUsage(EntityMover entityMover, Integer numUses) {
		this.entityMover = entityMover;
		this.numUses = numUses;
	}

	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.SWORD) {
			return entityMover.kill(user.getCoord(user.getDirection()), KillAction.WEAPON);
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.user = player;
		//Add all the extra uses we need to the player
		if (this.numUses > 1) {
			player.pickup(new SwordUsage(entityMover, this.numUses - 1));
		}
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
