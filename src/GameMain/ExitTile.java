package GameMain;

public class ExitTile extends Tile implements WinCondition{
	
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


	@Override
	public boolean hasWon() {
		return hitTile;
	}


	@Override
	public void setUnsatisfied() {
		
	}


	@Override
	public void setSatisfied() {
		
	}

	@Override
	public void tick(Integer tickNum) {
		// TODO Auto-generated method stub
		
	}
}
