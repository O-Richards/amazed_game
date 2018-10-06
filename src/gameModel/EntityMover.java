package gameModel;

/**
 * @author Oli
 * Interface to allow tiles to be fetched from the map
 */
public interface EntityMover {
	
	/**
	 * @param e the enemy to be moved
	 * @param nextCoord the coord to move the enemy to
	 * @return true if the entity is moved to the new tile. false else
	 * @precondition e != null
	 * @precondition nextCoord is in the map 
	 */
	boolean moveMobileEntity(MobileEntity e, Coord nextCoord);
	boolean moveMobileEntity(MobileEntity e, Direction dir);
	
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
