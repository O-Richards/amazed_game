package GameMain;

public class InvincibilityBonusAction extends MovementBonus {

	public InvincibilityBonusAction(Movement baseMovement) {
		super(baseMovement);
	}

	@Override
	public boolean canDie() {
		//if (after certain number of ticks) {
		// returns false else returns true
		return false;
	}
	
	

}
