package gameModel;


public class SwordUsableEntity extends UsableEntity {
	private int noOfUses = 5; 

	SwordUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction direction) {
		// Kills enemies in the direction of given: 
		this.entityMover.killEnemyEntities(this.getCoord(direction));
		noOfUses --; 
		return noOfUses > 0;
	}
		

	public String getSprite() {
		return "S";
	}
}
