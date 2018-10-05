package gameModel;

public class BoulderMobileEntity extends MobileEntity {
	//Creating a boulder entity: 
	public BoulderMobileEntity(Coord coord) {
		super(coord);
		this.setMovement(new BoulderMovement(this));
		this.setDirection(Direction.CENTRE);
	}

//	//Handles Collisions between the boulder and what it hits: 
//	@Override
//	public Collision collide(MobileEntity hitter, boolean recall) {
//		if (hitter == this) return Collision.NOMOVE;
//		if (hitter.pushEntity()) {
//			this.move(this.getCoord(hitter.getDirection()));
//		} 
//		if (recall) hitter.collide(this, false);
//		return Collision.NOMOVE;
//	}
	
	@Override
	public String getSprite() {
		return "B";
	}

	@Override
	public boolean pickup(UsableEntity item) {
		return false;
	}

}
