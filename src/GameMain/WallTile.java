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

	public String getSprite() {
		return "W";
	}
	/**
	 * Checks the type of tile:
	 * @return True if the tile is the same type: 
	 */	
	@Override
	 public boolean equals(Object obj) {
			if (obj == null) return false;
			if (obj == this) return true;
			if (obj.getClass() != this.getClass()) return false;
			Tile t = (WallTile)obj;
			if (t.getCoord().equals(this.getCoord())) return true;
			return false;
	 }
		
	
	
}
