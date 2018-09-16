package GameMain;

public class EmptyTile extends Tile {
	
	private WinCondition enemyCondition;
	private WinCondition winCondition;
	
	public EmptyTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord);
		this.winCondition = winCondition;
		this.enemyCondition = enemyCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.containsEntity(new TreasureEntity(this.getCoord()))) {
			this.winCondition.setType(WinType.TREASURE);
		} else {
			this.winCondition.setType(WinType.WIN);
		}
		if (this.containsEntity(new EnemyMobileEntity(this.getCoord()))) {
			this.enemyCondition.setType(WinType.ENEMY);
		} else {
			this.enemyCondition.setType(WinType.WIN);
		}
	}
}
