package GameMain;

public class WallTile extends Tile {

	public WallTile(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.NOMOVE;
	}
}
