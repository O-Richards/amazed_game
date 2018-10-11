package gameModel.entity;

import gameModel.Coord;
import gameModel.mobileEntity.Direction;
import gameModel.usable.Usable;

public interface Entity {
	public Coord getCoord();
	
	/**
	 * @param dir the direction from the entity we want a Coord of.
	 * @return the Coord of the tile in the direction dir from the entity
	 */
	public Coord getCoord(Direction dir);
	
	// only used by mobileEntities to get movement rate
	public void tick(Integer tickNum);

	public String getSprite();

	public void setCoord(Coord coord);

	@Override
	public boolean equals(Object obj);

	public void setAlive(boolean b);

	public boolean isAlive();
	
	/**
	 * @param item the item to pickup
	 * @return true if picked up
	 */
	public boolean pickup(Usable item);

	public Usable getUsable();
}	
