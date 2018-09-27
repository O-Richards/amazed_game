package gameModel;

public class TreasureEntity extends UsableEntity {
	
	public TreasureEntity(Coord coord) {
		super(coord);
	}
	
	@Override
	public Boolean use(Direction playerDirection) {
		return true;
	}
	
	@Override
	public String getSprite() {
		return "$";
	}
}
