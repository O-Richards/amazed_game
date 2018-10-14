package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.VisType;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinType;

public class SwitchTile extends Tile {
	
	private WinCondition switchCondition;
	
	public SwitchTile(Coord coord, WinCondition winCondition, EntityMover entityMover) {
		super(coord, entityMover);
		this.switchCondition = winCondition;
	}
	public SwitchTile(Tile oldParent, WinCondition winCondition) {
		super(oldParent);
		this.switchCondition = winCondition; 
	}
	

	public void updateWinCondition() {
		if (this.getMobile() != null && this.getMobile().canTriggerSwitches()) {
			this.switchCondition.setType(WinType.WIN);
		} else {
			this.switchCondition.setType(WinType.SWITCH);
		}
	}

	@Override
	public VisType getVisType() {
		return VisType.SWITCH;
	}

}
