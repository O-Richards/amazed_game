package gameModel;

public interface Collidable {
	/**
	 * Collide the hitter with the object. MobileHitters have a bunch of methods
	 * that can be called e.g. canFly(), pickupUsable() etc.
	 * The general idea is that the mobile entities will collide with a tile
	 * when they try to move onto it, which will then cause them to collide with
	 * each entity on the tile. (Think Composition Pattern)
	 * @param hitter The mobile entity that is walking into the collidable object
	 * @return MOVE if the movement is possible, NOMOVE if the movement is blocked
	 */
	public Collision collide(MobileEntity hitter, boolean recall);
}
