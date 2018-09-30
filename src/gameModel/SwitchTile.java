package gameModel;

public class SwitchTile extends EmptyTile {
	
	private WinCondition switchCondition;
	
	public SwitchTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition, EntityMover entityMover) {
		super(coord, enemyCondition, entityMover);
		this.switchCondition = winCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.getItem().equals((new BoulderMobileEntity(this.getCoord())))) {
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
