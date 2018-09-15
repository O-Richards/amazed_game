package GameMain;

import java.util.List;

public class PitTile extends Tile {

	public PitTile(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collideExt(MobileEntity hitter, Collision col) {
		if (hitter.canFly()) {
			return Collision.MOVE;
		} else {
			hitter.killEnemy();
			hitter.killPlayer();
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
