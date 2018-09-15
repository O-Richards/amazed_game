package GameMain;

public class SwitchTile extends Tile {
	
	private WinCondition enemyCondition;
	private WinCondition winCondition;
	
	public SwitchTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord);
		this.winCondition = winCondition;
		this.enemyCondition = enemyCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.containsEntity(new BoulderMobileEntity(this.getCoord()))) {
			this.winCondition.setType(WinType.SWITCH);
		} else {
			this.winCondition.setType(WinType.WIN);
		}
		if (this.containsEntity(new EnemyMobileEntity(this.getCoord()))) {
			this.enemyCondition.setType(WinType.ENEMY);
		} else {
			this.enemyCondition.setType(WinType.WIN);
		}
	}
	
	@Override
	public String getSprite() {
		return "@";
	}

}
