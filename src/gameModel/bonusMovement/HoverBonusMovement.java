package gameModel.bonusMovement;

import gameModel.mobileEntity.Movement;

public class HoverBonusMovement extends MovementBonus {

	public HoverBonusMovement(Movement baseMovement) {
		super(baseMovement);
	}
	
	@Override
	public boolean canFly() {
		return true;
	}

}
