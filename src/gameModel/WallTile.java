package gameModel;

public class WallTile extends Tile {

	public WallTile(Coord coord, WinCondition enemyCondition, EntityMover entityMover) {
		super(coord, enemyCondition, entityMover);
	}

	public String getSprite() {
		return "W";
	}
	
	@Override
	public boolean canMoveTo() {
		return false;
	}
		
}
