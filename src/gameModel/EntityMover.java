package gameModel;

import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.MobileEntity;
import gameModel.tile.EntityPlacementException;

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
	 * @param c The coord to check
	 * @return	true if a mobile entity can move onto this tile without dying, false else.
	 */
	public boolean traversable(Coord c);

	/**
	 * @param c the coord to kill all (killable) entities on
	 * @param weapon 
	 */
	public boolean kill(Coord c, KillAction action);
	
	/**
	 * @param action the action to be called
	 * @param numTicksUntil the number of ticks from now to call at
	 */
	public void addDelayedAction(DelayedAction action, Integer numTicksUntil);
	
	/**
	 * Place a mobile entity at a coord
	 * @param entity the entity to be placed
	 * @precondition the entity must have a valid coord
	 */
	void placeMobileEntity(MobileEntity entity) throws EntityPlacementException;
}
