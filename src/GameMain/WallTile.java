package GameMain;

import java.util.List;

public class WallTile extends Tile {

	public WallTile(Coord coord) {
		super(coord);
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.NOMOVE;
	}

	public String getSprite() {
		return "W";
	}

	@Override
	protected void updateWinCondition() {
			
	}
}
