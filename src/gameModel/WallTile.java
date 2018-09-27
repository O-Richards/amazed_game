package gameModel;

public class WallTile extends Tile {

	public WallTile(Coord coord, WinCondition enemyCondition) {
		super(coord, enemyCondition);
	}
	
	@Override
	public Collision collideExt(MobileEntity hitter, Collision col) {
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
		//TODO: Make hussein not see this
		//TODO: MAke this not shit
			if (obj == null) return false;
			if (obj == this) return true;
			if (obj.getClass() != this.getClass()) return false;
			Tile t = (WallTile)obj;
			if (t.getCoord().equals(this.getCoord())) return true;
			return false;
	 }
		
	@Override
	protected void updateWinCondition() {
			
	}
}
