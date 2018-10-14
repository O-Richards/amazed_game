package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.VisType;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinType;

public class ExitTile extends Tile {
	
	private WinCondition winCondition;

	public ExitTile(Coord coord, WinCondition winCondition, EntityMover entityMover) {
		super(coord, entityMover);
		this.winCondition = winCondition;
	}
	public ExitTile(Tile oldParent,WinCondition winCondition) {
		super(oldParent);
		this.winCondition = winCondition; 
	}

	@Override
	public void updateWinCondition() {
		// TODO Auto-generated method stub
		// LAW OF DEMETER VIOLATION
		if (this.getMobile() != null && this.getMobile().isPlayer()) {
			this.winCondition.setType(WinType.WIN);
		} else {
			this.winCondition.setType(WinType.EXIT);
		}
	}
	
	public String getSprite() {
		return "!";
	}
	@Override
	public VisType getVisType() {
		return VisType.EXIT;
	}

}
