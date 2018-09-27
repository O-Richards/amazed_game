package gameModel;

public class ArrowUsableEntity extends UsableEntity{
	public ArrowUsableEntity(Coord coord) {
		super(coord);
	}
	@Override
	public Boolean use(Direction direction) {
		//Keeps looping until it kills something: 
		Coord target = this.getCoord();
		this.entityMover.placeEntity(new ArrowMobileEntity(target, direction), target);
		return false;
	}

	public String getSprite() {
		return ">";
	}
}
