package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.Entity;

public class CowardEnemyMovement extends EnemyMovement {

	private Integer fleeTimer = 0;
	private final Integer FLEE_DISTANCE = 4;
	private final Integer FLEE_DURATION = 4; 
	
	public CowardEnemyMovement(double randMoveRate, Entity baseEntity, MobileEntity player, EntityMover entityMover) {
		super(randMoveRate, baseEntity, player, entityMover);
	}

	@Override 
	protected boolean shouldFlee() {
		Coord start = super.getStartCoord();
		Coord player = super.getPlayerCoord();
		Integer xDist = start.getX() - player.getX();
		Integer yDist = start.getY() - player.getY();
		Integer totalDist = Math.abs(xDist) + Math.abs(yDist);
		if (totalDist <= FLEE_DISTANCE) fleeTimer = FLEE_DURATION;
		if (fleeTimer > 0) {
			fleeTimer--;
			return true;
		} else {
			return super.shouldFlee();
		}
	}
	
	@Override
	protected Coord getTargetCoord() {
		return super.getPlayerCoord();
	}
}
