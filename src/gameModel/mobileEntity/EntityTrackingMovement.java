package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.KillAction;
import gameModel.usable.Usable;

public class EntityTrackingMovement implements Movement {
	private static final boolean DEBUG = true;
	private Direction direction = Direction.UP;
	private MobileEntity entity;

	public EntityTrackingMovement() {
	}
	
	public EntityTrackingMovement(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Coord nextCoord() {
		Coord currCoord = this.entity.getCoord();
		Coord nextCoord = currCoord;
		if (entity.isMoving()) {
			nextCoord = currCoord.add(entity.getDirection());
		}
		if (DEBUG) System.out.println("EntityTrackingMovement.nextCoord: returning at " + currCoord + " Returning " + nextCoord);
		return nextCoord;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction dir) {
		if (DEBUG) System.out.println("Setting direction to " + dir);
		this.direction = dir;
	}
	
	@Override 
	public void setMobileEntity(MobileEntity mobileEntity) {
		this.entity = mobileEntity;
	}
	
	/**
	 * @return true if the entity is killed, false else.
	 */
	@Override
	public boolean kill(KillAction action) {
		if (this.canDie()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean pickup(Usable e) {
		return false;
	}

	@Override 
	public boolean canDie() {
		return true;
	}
}
