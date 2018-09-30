package gameModel;

public class PitTile extends Tile {

	public PitTile(Coord coord, WinCondition enemyCondition, EntityMover entityMover) {
		super(coord, enemyCondition, entityMover);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collideExt(MobileEntity hitter, Collision col) {
		if (hitter.canFly()) {
			return col;
		} else {
			hitter.kill();
			return Collision.NOMOVE;
		} 
	}
	
	public String getSprite() {
		return "O";
	}

	@Override
	protected void updateWinCondition() {
		
	}

}
