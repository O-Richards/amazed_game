package GameMain;

public class DoorTile extends Tile {
	
	private WinCondition enemyCondition;

	public DoorTile(Coord coord, WinCondition enemyCondition) {
		super(coord);
		this.enemyCondition = enemyCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.containsEntity(new EnemyMobileEntity(this.getCoord()))) {
			this.enemyCondition.setType(WinType.ENEMY);
		} else {
			this.enemyCondition.setType(WinType.WIN);
		}
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		// IMPLEMENT
		return Collision.NOMOVE;
	}
	
	@Override
	public String getSprite() {
		return "D";
	}

}
