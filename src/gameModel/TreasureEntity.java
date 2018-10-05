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
	public void applyToPlayer(PlayerMobileEntity player) {
		this.winCondition.setType(WinType.WIN);
	}

	
	@Override
	public String getSprite() {
		return "$";
	}

}
