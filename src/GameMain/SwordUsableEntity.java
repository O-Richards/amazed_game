package GameMain;


public class SwordUsableEntity extends UsableEntity {
	private int noOfUses = 5; 

	SwordUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction direction) {
		// Coordinates of current location: 
		this.entityMover.killEnemyEntities(this.getCoord(direction));
		noOfUses --; 
		return noOfUses > 0;
	}
		

	public String getSprite() {
		return "S";
	}
}
