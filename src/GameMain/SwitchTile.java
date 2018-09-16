package GameMain;

public class SwitchTile extends Tile {
	
	private WinCondition switchCondition;
	
	public SwitchTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord, enemyCondition);
		this.switchCondition = winCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.containsEntity(new BoulderMobileEntity(this.getCoord()))) {
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
