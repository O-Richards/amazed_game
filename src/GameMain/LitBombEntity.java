package GameMain;

public class LitBombEntity extends Entity {
	
	LitBombEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	
	public void explode(Tile[][] adjTiles)  {	
		Tile up = adjTiles[1][0];
		up.addEntity(new KillerEntity(this.getCoord(Direction.UP), true, true));
		Tile down = adjTiles[1][2];
		down.addEntity(new KillerEntity(this.getCoord(Direction.DOWN), true, true));
		Tile left = adjTiles[0][1];
		left.addEntity(new KillerEntity(this.getCoord(Direction.LEFT), true, true));
		Tile right = adjTiles[2][1];
		right.addEntity(new KillerEntity(this.getCoord(Direction.RIGHT), true, true));
		Tile centre = adjTiles[1][1];
		centre.addEntity(new KillerEntity(this.getCoord(Direction.CENTRE), true, true));
		
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.MOVE;
	}

	@Override
	public String getSprite() {
		return "X";
	}
	
	
	
	
	
	
}
