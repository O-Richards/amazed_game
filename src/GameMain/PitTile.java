package GameMain;

public class PitTile extends Tile {

	public PitTile(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	
	public Collision collide(MobileEntity hitter) {
		return Collision.MOVE; 
	}
	public String getSprite() {
		return "O";
	}

}
