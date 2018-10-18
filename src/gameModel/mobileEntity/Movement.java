package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.KillAction;
import gameModel.usable.Usable;

public interface Movement {
	//So what I have done is pull all the methods out of MobileEntity and put them in here
	//Now we can apply decorator pattern to have the potion bonus's wrap around the player's base movements
	//One thing I got stuck on is how do we have the bonus expire
	//So what we do is record the time when the bonus movement is constructed,
	//then something like: 
	// if ((currentTime - startTime) > ACTIVE_TIME) return this.movement.functionX()
	//That way we don't have to remove the decorator, it just stops doing anything but passing along messages
	
	//In the constructor, consider passing in the target(s) entitie(s)
	public Coord nextCoord();
	
	default public boolean canFly() {
		return false;
	}	
	
	/**
	 * Attempt to kill the moveable
	 * @return true if killed, false else
	 */
	public boolean kill(KillAction action);
	
	/**
	 * Attempt to pickup an item
	 * @return true if picked up, false else
	 */
	public boolean pickup(Usable item);
	
	public boolean canDie();
	
	public Direction getDirection();	
	
	public void setDirection(Direction dir);

	default public void setMobileEntity(MobileEntity mobileEntity) {
		
	}
}
