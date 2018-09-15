package GameMain;

public class TreasureEntity extends UsableEntity {
	
	TreasureEntity(Tile tile) {
		super(tile);
	}
	
	
	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
	}
	
	@Override
	public String getSprite() {
		return "$";
	}



}
