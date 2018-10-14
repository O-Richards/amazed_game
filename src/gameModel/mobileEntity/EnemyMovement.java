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

	@Override
	public Coord nextCoord() {
		//Check if we should do a random move
		if (this.randMoveRate < Math.random()) {
			//Take a random move
			double randNum = Math.random();
			Direction nextDir = Direction.CENTRE;
			if (randNum < 0.25) nextDir = Direction.UP;
			else if (randNum < 0.5) nextDir = Direction.LEFT;
			else if (randNum < 0.75) nextDir = Direction.DOWN;
			else nextDir = Direction.RIGHT;
			
			return this.entity.getCoord(nextDir);
		}
		
		AStarSearch search = new AStarSearch(entity.getCoord(), playerTarget.getCoord(), entityMover);
		List<Coord> path = search.findPath();
		if (path == null) {
			this.direction = Direction.CENTRE;
			System.out.println("EnemyMovement.nextCoord entity coord is: " + this.entity.getCoord());
			return this.entity.getCoord();
		} else {
			this.direction = path.get(0).minusX(this.entity.getCoord());
			if (this.direction == Direction.CENTRE) {
				this.direction = path.get(0).minusY(this.entity.getCoord());
			}
			return path.get(0);
		}
	}

	@Override
	public boolean kill(KillAction action) {
		return false;
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
