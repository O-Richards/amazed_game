package gameModel;

import java.time.LocalTime;

public class InvincibilityBonusAction extends MovementBonus {
	
	private LocalTime time;

	public InvincibilityBonusAction(Movement baseMovement) {
		super(baseMovement);
		this.time = LocalTime.now();
	}
	
	@Override
	public boolean canDie() {
		LocalTime timeCounter = time.plusSeconds(30);
		if (LocalTime.now().compareTo(timeCounter) > 0) {
			return super.canDie();
		} else {
			return false;
		}
	}
	
}
