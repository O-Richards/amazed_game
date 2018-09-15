package GameMain;

public class TreasureEntity extends UsableEntity {
	
	TreasureEntity(Coord coord) {
		super(coord);
	}
	
	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
	}
	
	@Override
	public String getSprite() {
		return "$";
	}

	@Override
	public int noOfUsesLeft() {
		// TODO Auto-generated method stub
		return 0;
	}



}
