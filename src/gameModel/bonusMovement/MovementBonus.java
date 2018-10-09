package gameModel.bonusMovement;

import gameModel.Coord;
import gameModel.KillAction;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.Movement;
import gameModel.usable.Usable;

public abstract class MovementBonus implements Movement {
	private Movement baseMovement;
	/**
	 * 
	 * @param baseMovement
	 */
	public MovementBonus(Movement baseMovement) {
		this.baseMovement = baseMovement;
	}

	@Override
	public boolean canFly() {
		return this.baseMovement.canFly();
	}
	
	@Override
	public boolean pushEntity() {
		return this.baseMovement.pushEntity();
	}
	

	@Override
	public boolean canDie() {
		return this.baseMovement.canDie();
	}

	@Override
	public Coord nextCoord() {
		return this.baseMovement.nextCoord();
	}

	@Override
	public Direction getDirection() {
		return this.baseMovement.getDirection();
	}

	@Override
	public void setDirection(Direction dir) {
		this.baseMovement.setDirection(dir);
	}

	@Override
	public Coord getCoord() {
		return this.baseMovement.getCoord();
	}
	
	@Override
	public Coord getCoord(Direction dir) {
		return this.baseMovement.getCoord(dir);
	}
	
	@Override
	public boolean kill(KillAction action) {
		return this.baseMovement.kill(action);
	}
	
	@Override
	public boolean pickup(Usable item) {
		return this.baseMovement.pickup(item);
	}
}
