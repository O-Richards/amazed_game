package gameModel;

public abstract class UsableEntity extends Entity {

	UsableEntity(Coord coord) {
		super(coord);
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
