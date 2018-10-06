package gameModel;

public class ArrowMobileEntity extends MobileEntity {
	
	public ArrowMobileEntity(Coord coord, Direction dir) {
		super(coord);
		this.setDirection(dir);
	}

	@Override
	public boolean pickup(UsableEntity item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSprite() {
		// TODO Auto-generated method stub
		return ">";
	}

	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

}
