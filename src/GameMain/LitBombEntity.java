package GameMain;

public class LitBombEntity extends Entity {
	
	LitBombEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	
	public void explode(Tile[][] adjTiles)  {	
		Tile up = adjTiles[1][0];
		up.addEntity(new KillerEntity(up, true, true));
		Tile down = adjTiles[1][2];
		down.addEntity(new KillerEntity(down, true, true));
		Tile left = adjTiles[0][1];
		left.addEntity(new KillerEntity(left, true, true));
		Tile right = adjTiles[2][1];
		right.addEntity(new KillerEntity(right, true, true));
		Tile centre = adjTiles[1][1];
		centre.addEntity(new KillerEntity(centre, true, true));
		
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
