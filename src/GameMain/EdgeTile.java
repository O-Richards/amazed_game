package GameMain;

public class EdgeTile extends Tile {
	//Placed on run, prevent the player from moving off: 
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
	 public boolean equals(Object obj) {
			if (obj == null) return false;
			if (obj == this) return true;
			if (obj.getClass() != this.getClass()) return false;
			Tile t = (EdgeTile)obj;
			if (t.getCoord().equals(this.getCoord())) return true;
			return false;
	 }
}
