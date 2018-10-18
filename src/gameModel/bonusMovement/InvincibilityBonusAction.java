package gameModel.bonusMovement;

import gameModel.DelayedAction;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.mobileEntity.Movement;

public class InvincibilityBonusAction extends MovementBonus implements DelayedAction {
	private static final int numTicksInvincible = 30;
	private boolean active = true;
	public InvincibilityBonusAction(Movement baseMovement, EntityMover entityMover) {
		super(baseMovement);
		// entityMover.addDelayedAction(this, numTicksInvincible);
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
