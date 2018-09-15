package GameMain;

public class ArrowUsableEntity extends UsableEntity{

	public ArrowUsableEntity(Coord coord) {
		super(coord);
	}
	@Override
	public Boolean use(Direction direction) {
		ShootingArrowMobileEntity shootingArrow = new ShootingArrowMobileEntity(getCoord());
		EntityTrackingMovement arrowMove = new EntityTrackingMovement(shootingArrow);
		arrowMove.setDirection(direction);
		shootingArrow.setMovement(arrowMove);
		return false;
	}

	public String getSprite() {
		return ">";
	}

	
}
