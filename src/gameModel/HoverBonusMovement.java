package gameModel;

public class HoverBonusMovement extends MovementBonus {

	public HoverBonusMovement(Movement baseMovement) {
		super(baseMovement);
	}
	
	@Override
	public boolean canFly() {
		return true;
	}



}
