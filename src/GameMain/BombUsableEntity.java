package GameMain;

public class BombUsableEntity extends UsableEntity {

	BombUsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Boolean use(Direction direction) {
		// Coordinates of current location: 
		KillerEntity killEntity1 = new KillerEntity(this.getCoord(), true, true);
		KillerEntity killEntity2 = new KillerEntity(this.getCoord(direction), true, true);
		KillerEntity killEntity3 = new KillerEntity(this.getCoord(direction), true, true);
		KillerEntity killEntity4 = new KillerEntity(this.getCoord(direction), true, true);
		this.entityMover.placeEntity(killEntity1, this.getCoord(direction));
		this.entityMover.placeEntity(killEntity2, this.getCoord(direction));
		this.entityMover.placeEntity(killEntity3, this.getCoord(direction));
		this.entityMover.placeEntity(killEntity4, this.getCoord(direction));
		return false;
	}

	@Override
	public String getSprite() {
		// TODO Auto-generated method stub
		return "b";
	}

}
