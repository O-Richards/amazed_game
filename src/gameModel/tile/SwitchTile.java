package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinType;

public class SwitchTile extends Tile {
	
	private WinCondition switchCondition;
	
	public SwitchTile(Coord coord, WinCondition winCondition, EntityMover entityMover) {
		super(coord, entityMover);
		this.switchCondition = winCondition;
	}

	protected void updateWinCondition() {
		if (this.getMobile().canTriggerSwitches()) {
			this.switchCondition.setType(WinType.WIN);
		} else {
			this.switchCondition.setType(WinType.SWITCH);
		}
	}

	@Override
	public String getSprite() {
		return "@";
	}

}
