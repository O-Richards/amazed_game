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
		
	/**
 	 * @precondition c is valid i.e. on the map
	 * @param entity The entity to move
	 * @param c The Coord to place remove entity from
	 */
	public void removeEntity(Entity entity, Coord c);

	public Collision moveEntity(MobileEntity e, Coord nextCoord);
	
	/**
	 * @param entity The entity to be placed on the map
	 * @param c The coord to place the entity
	 */
	public void placeEntity(Entity entity, Coord c);
	
	/**
	 * @param c The coord to check
	 * @return	true if a mobile entity can move onto this tile without dying, false else.
	 */
	public boolean canMoveTo(Coord c);

}
