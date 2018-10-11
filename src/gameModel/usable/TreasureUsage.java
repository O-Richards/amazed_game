package gameModel.usable;

import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinType;

public class TreasureUsage implements Usable {
	private WinCondition winCondition;
	
	public TreasureUsage(WinCondition winCondition) {
		this.winCondition = winCondition;
	}
	
	@Override
	public boolean use(UseAction action) {
		return false;
	}
	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		player.incrementTreasureNo();
		this.winCondition.setType(WinType.WIN);
	}
	@Override
	public UseAction getUseAction() {
		return UseAction.TREASURE;
	}

}
