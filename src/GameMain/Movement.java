package GameMain;

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
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	default public boolean pushEntity() {
		return false;
	}
	
	default public boolean canDie() {
		return true;
	}
	
	/**
	 * @return The current direction of movement
	 */
	public Direction getDirection();	
	
	/**
	 * @param dir The direction of movement to be set
	 */
	public void setDirection(Direction dir);
	
	/**
	 * @precondition the speed must be 0 or 1
	 * @param speed The speed of the movement in tiles/tick
	 */
	public void setSpeed(Integer speed);
	
	/**
	 * @return The current speed of movement
	 */
	public Integer getSpeed();
	
	public Coord getCoord();
	
	public Coord getCoord(Direction dir);
}
