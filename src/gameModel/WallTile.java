package gameModel;

public class WallTile extends Tile {

	public WallTile(Coord coord, WinCondition enemyCondition) {
		super(coord, enemyCondition);
	}

	public String getSprite() {
		return "W";
	}
		
	@Override
	protected void updateWinCondition() {
			
	}
}
