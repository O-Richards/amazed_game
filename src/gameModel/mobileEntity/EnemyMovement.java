package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.aStarSearch.AStarSearch;
import gameModel.entity.Entity;
import gameModel.usable.Usable;

import java.util.List;

import gameModel.*;

public class EnemyMovement implements Movement {
	private Entity entity;
	private Entity playerTarget;
	private EntityMover entityMover;
	private Direction direction;
	private double randMoveRate;
	
	public EnemyMovement(double randMoveRate, Entity baseEntity, Entity playerTarget, EntityMover entityMover) {
		this.entity = baseEntity;
		this.playerTarget = playerTarget;
		this.entityMover = entityMover;
		this.randMoveRate = randMoveRate;
	}

	private Coord getRandomCoord() {
		if (this.randMoveRate < Math.random()) {
			//Take a random move
			double randNum = Math.random();
			Direction nextDir = Direction.CENTRE;
			if (randNum < 0.25) nextDir = Direction.UP;
			else if (randNum < 0.5) nextDir = Direction.LEFT;
			else if (randNum < 0.75) nextDir = Direction.DOWN;
			else nextDir = Direction.RIGHT;
			return this.entity.getCoord(nextDir);
		} else {
			return null;
		}
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
	
	protected Coord getTargetCoord() {
		return playerTarget.getCoord();
	}
	
	@Override
	public Coord nextCoord() {
		//Check if we should do a random move
		Coord randomCoord = this.getRandomCoord();
		if (randomCoord != null) return randomCoord;
		AStarSearch search = new AStarSearch(entity.getCoord(), this.getTargetCoord(), entityMover);
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

}
