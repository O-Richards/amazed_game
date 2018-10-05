package gameModel;

/**
 * @author Oli
 * Interface to allow tiles to be fetched from the map
 */
public interface EntityMover {
	
	/**
	 * @param e The entity to move
	 * @param dir The direction to move the entity in
	 * @return TODO
	 */
	public Collision moveEntity(MobileEntity e, Direction dir);

	public Collision moveEntity(MobileEntity e, Coord nextCoord);
	
	/**
	 * @param entity The entity to be placed on the map
	 * @param c The coord to place the entity
	 * THIS MUST BE REMOVED!!!!!!!!!!!!!
	 */
	public void placeEntity(Entity entity, Coord c);
	
	/**
	 * @param c The coord to check
	 * @return	true if a mobile entity can move onto this tile without dying, false else.
	 */
	public boolean traversable(Coord c);

	/**
	 * @param c the coord to kill all (killable) entities on
	 */
	public boolean kill(Coord c);


}
