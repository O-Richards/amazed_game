package GameMain;

import java.util.List;

public class EdgeTile extends Tile {
	
	public EdgeTile(Coord coord) {
		super(coord);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.NOMOVE;
	}
	
	public String getSprite() {
		return "|";
	}

	@Override
	protected void updateWinCondition() {
		
	}
	
	
}
