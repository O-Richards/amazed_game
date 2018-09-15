package GameMain;

public class TreasureEntity extends UsableEntity {
	
	TreasureEntity(Coord coord) {
		super(coord);
	}
	
	@Override
	public Boolean use(Direction playerDirection, Tile[][] adjTiles) {
		return false;
	}
	
	@Override
	public String getSprite() {
		return "$";
	}
}
