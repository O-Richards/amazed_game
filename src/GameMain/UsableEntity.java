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
	 * @param adjTiles The surrounding tiles
	 * @return True if the item has uses left, false else.
	 */
	public Boolean use(Direction playerDirection, Tile[][] adjTiles) {
		return true;
	}
}
