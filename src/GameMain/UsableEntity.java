package GameMain;

public abstract class UsableEntity extends Entity {

	UsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
		}
		return Collision.MOVE;
	}
	
	/**
	 * @param playerDirection Direction the player is facing
	 * @param Tile the current Tile the player is on
	 * @return True if the item has uses left, false else.
	 */
	public Boolean use(Direction playerDirection,Tile currentTile) {
		return true;
	}
}
