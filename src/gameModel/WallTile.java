package gameModel;

public class WallTile extends Tile {

	public WallTile(Coord coord, WinCondition enemyCondition, EntityMover entityMover) {
		super(coord, enemyCondition, entityMover);
	}

	public String getSprite() {
		return "W";
	}
	
	@Override
	public boolean traversable() {
		return false;
	}

	@Override
	protected void updateWinCondition() {
		// TODO Auto-generated method stub
		
	}
		
}
