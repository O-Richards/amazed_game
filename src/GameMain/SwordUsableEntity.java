package GameMain;


public class SwordUsableEntity extends UsableEntity {
	private int noOfUses = 5; 

	SwordUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction direction) {
		// Coordinates of current location: 
		KillerEntity killEntity = new KillerEntity(this.getCoord(direction), false, true);
		this.entityMover.placeEntity(killEntity, this.getCoord(direction));
		noOfUses --; 
		return noOfUses > 0;
	}
		

	public String getSprite() {
		return "S";
	}
}
