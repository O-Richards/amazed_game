package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.aStarSearch.AStarSearch;
import gameModel.entity.Entity;
import gameModel.usable.Usable;

import java.util.List;

import gameModel.*;

public abstract class EnemyMovement implements Movement {
	private Entity entity;
	private MobileEntity player;
	private EntityMover entityMover;
	private Direction direction = Direction.CENTRE;
	private double randMoveRate;
	
	public EnemyMovement(double randMoveRate, Entity baseEntity, MobileEntity player, EntityMover entityMover) {
		this.entity = baseEntity;
		this.player = player;
		this.entityMover = entityMover;
		this.randMoveRate = randMoveRate;
	}

	private Coord getRandomCoord() {
		if (this.randMoveRate > Math.random()) {
			return this.entity.getCoord();
		}
		return null;
//		if (this.randMoveRate < Math.random()) {
//			//Take a random move
//			double randNum = Math.random();
//			Direction nextDir = Direction.CENTRE;
//			if (randNum < 0.25) nextDir = Direction.UP;
//			else if (randNum < 0.5) nextDir = Direction.LEFT;
//			else if (randNum < 0.75) nextDir = Direction.DOWN;
//			else nextDir = Direction.RIGHT;
//			return this.entity.getCoord(nextDir);
//		} else {
//			return null;
//		}
	}
	
	private void setDirection(Coord nextCoord) {
		if (nextCoord == this.entity.getCoord()) {
			this.direction = Direction.CENTRE;
		} else {
			this.direction = nextCoord.minusX(this.entity.getCoord());
			if (this.direction == Direction.CENTRE) {
				this.direction = nextCoord.minusY(this.entity.getCoord());
			}
		}
	}
	
	protected Coord getPlayerCoord() {
		return player.getCoord();
	}
	
	protected Direction getPlayerDirection() {
		return player.getDirection();
	}
	
	protected Coord getStartCoord() {
		return entity.getCoord();
	}
	
	protected boolean shouldFlee() {
		return !this.player.canDie();
	}
	
	abstract protected Coord getTargetCoord();
	
	@Override
	public Coord nextCoord() {
		//Check if we should do a random move
		Coord randomCoord = this.getRandomCoord();
		if (randomCoord != null) return randomCoord;
		AStarSearch search = new AStarSearch(entity.getCoord(), this.getTargetCoord(), this.shouldFlee(), entityMover);
		Coord next = search.getNextCoord();
		this.setDirection(next);
		return next;
	}

	@Override
	public boolean kill(KillAction action) {
		return true;
	}

	@Override
	public boolean pickup(Usable item) {
		return false;
	}
	
	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public void setDirection(Direction dir) {
		this.direction = dir;
	}
	
	@Override 
	public boolean canDie() {
		return true;
	}
	
	@Override
	public KillAction getKillAction() {
		return KillAction.ENEMY;
	}

}
