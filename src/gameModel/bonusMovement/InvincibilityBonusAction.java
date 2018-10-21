package gameModel.bonusMovement;

import gameModel.DelayedAction;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.mobileEntity.Movement;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.usable.UseAction;

public class InvincibilityBonusAction extends MovementBonus implements DelayedAction {
	private static final int numTicksInvincible = 30;
	private boolean active = true;
	private PlayerMobileEntity player;
	
	public InvincibilityBonusAction(Movement baseMovement, PlayerMobileEntity player, EntityMover entityMover) {
		super(baseMovement);
		entityMover.addDelayedAction(this, numTicksInvincible);
		this.player = player;
	}
	
	@Override
	public boolean canDie() {
		if (this.active) {
			return false;
		} else {
			return super.canDie();
		}
	}

	@Override
	public void performDelayedAction() {
		this.active = false;
		//Remove from inventory
		this.player.use(UseAction.INVINCIBILITY);
	}
	
	@Override
	public KillAction getKillAction() {
		if (this.active) {
			return KillAction.INVINCIBLE;
		} else {
			return super.getKillAction();
		}
	}
}
