package GameMain;

public class EdgeTile extends Tile {
	public EdgeTile(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.NOMOVE;
	}

	public String getSprite() {
		return "|";
	}
	
	
}
