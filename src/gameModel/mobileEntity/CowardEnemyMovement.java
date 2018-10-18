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
	protected Coord getTargetCoord() {
		Coord player = super.getPlayerCoord();
		Coord start = super.getStartCoord();
		Integer xDist = start.getX() - player.getX();
		Integer yDist = start.getY() - player.getY();
		Integer totalDist = Math.abs(xDist) + Math.abs(yDist);
		Integer targetX = player.getX();
		Integer targetY = player.getY();
		if (totalDist > FLEE_DISTANCE) fleeTimer = FLEE_DURATION;
		if (fleeTimer > 0) {
			fleeTimer--;
			//        start x      +   (direction opposing player)     (increase distance by FLEE_DISTANCE) 
			targetX = start.getX() + (   (xDist/Math.abs(xDist))    *    (Math.abs(xDist) + FLEE_DISTANCE)  );
			targetY = start.getY() + (   (yDist/Math.abs(yDist))    *    (Math.abs(yDist) + FLEE_DISTANCE)  );
		}
		return new Coord(targetX, targetY);
	}
}
