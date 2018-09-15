package GameMain;

public class ExitTile extends Tile {
	
	//private WinCondition winCondition;<-??
	private boolean hitTile; 

	public ExitTile(Coord coord) {
		super(coord);
		hitTile = false; 
		// TODO Auto-generated constructor stub
	}

	
	public Collision collide(MobileEntity hitter) {
		hitTile = true; 
		return Collision.MOVE; 
	}
	
	public String getSprite() {
		return "!";
	}
}
