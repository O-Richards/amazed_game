package GameMain;

public class DoorTile extends Tile {
	
	private WinCondition enemyCondition;
	private static int doorCodeGenerator = 1;
	private int doorCode;
	private boolean open = false;

	public DoorTile(Coord coord, WinCondition enemyCondition) {
		super(coord);
		this.enemyCondition = enemyCondition;
		this.doorCode = DoorTile.generateDoorCode();
	}
	
	private static synchronized int generateDoorCode() {
		return doorCodeGenerator++;
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
	public Collision collideExt(MobileEntity hitter, Collision col) {
		if (hitter.getKeyCode() == doorCode || open == true) {
			open = true;
			return col;
		} else {
			return Collision.NOMOVE;
		}
	}

	@Override
	public String getSprite() {
		return "D";
	}
}
