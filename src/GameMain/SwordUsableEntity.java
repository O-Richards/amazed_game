package GameMain;


public class SwordUsableEntity extends UsableEntity {
	private int noOfUses = 5; 

	SwordUsableEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use(Direction direction, Tile[][] adjTiles) {
		Tile target;
		switch (direction) {
		case UP:
			target = adjTiles[1][0];
			break;
		case DOWN:
			target = adjTiles[1][2];
			break;
		case LEFT:
			target = adjTiles[0][1];
			break;
		case RIGHT:
			target = adjTiles[2][1];
			break;
		default:
			target = adjTiles[1][1];
		}
		target.addEntity(new KillerEntity(target, false, true));
		noOfUses --; 
	}
	
	public int noOfUsesLeft() {
		return noOfUses; 
	}
	public String getSprite() {
		return "S";
	}
	
}
