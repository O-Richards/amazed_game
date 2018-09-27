package gameModel;

public abstract class UsableEntity extends Entity {

	UsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Collision collide(MobileEntity hitter, boolean recall) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
		}
		return Collision.MOVE;
	}
	
	/**
	 * @param direction Direction the item is to be used in
	 * @return True if the item has uses left, false else.
	 */
	public Boolean use(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}
}
