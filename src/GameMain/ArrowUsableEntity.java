package GameMain;

public class ArrowUsableEntity extends UsableEntity{
	private Coord currentTargetCoord; 
	public ArrowUsableEntity(Coord coord) {
		super(coord);
	}
	@Override
	public Boolean use(Direction direction) {
		/*
		ShootingArrowMobileEntity shootingArrow = new ShootingArrowMobileEntity(getCoord());
		EntityTrackingMovement arrowMove = new EntityTrackingMovement(shootingArrow);	
		arrowMove.setDirection(direction);
		shootingArrow.setMovement(arrowMove);*/
		//Keeps looping until it kills something: 
		currentTargetCoord = new Coord(this.getCoord().getX(), this.getCoord().getY()); 
		while(!this.entityMover.killEnemyEntities(currentTargetCoord)) {
			addCoord(direction);
		}
		//setting the coordinates so that the player doesn't fly: 
		return false;
	}
	public void addCoord(Direction d) {
		int x = this.currentTargetCoord.getX();
		int y = this.currentTargetCoord.getY(); 
		switch (d) {
		case UP:
			currentTargetCoord.setX(x+1);
			break;
		case DOWN:
			currentTargetCoord.setX(x-1);
			break;
		case LEFT:
			currentTargetCoord.setX(y-1);
			break;
		case RIGHT:
			currentTargetCoord.setX(y+1);
			break;
		default:
			break;
		}
	}
	public String getSprite() {
		return ">";
	}

	
}
