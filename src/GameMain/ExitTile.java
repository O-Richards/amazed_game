package GameMain;

public class ExitTile extends Tile {
	
	private WinCondition enemyCondition;
	private WinCondition winCondition;

	public ExitTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord);
		this.winCondition = winCondition;
		this.enemyCondition = enemyCondition;
	}

	@Override
	protected void updateWinCondition() {
		// TODO Auto-generated method stub
		if (this.containsEntity(new PlayerMobileEntity(this.getCoord()))) {
			this.winCondition.setType(WinType.WIN);
		} else {
			this.winCondition.setType(WinType.EXIT);
		}
		if (this.containsEntity(new EnemyMobileEntity(this.getCoord()))) {
			this.enemyCondition.setType(WinType.ENEMY);
		} else {
			this.enemyCondition.setType(WinType.WIN);
		}
	}
	
	public String getSprite() {
		return "!";
	}
}
