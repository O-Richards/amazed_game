package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.Entity;

public class HunterEnemyMovement extends EnemyMovement {
	
	public HunterEnemyMovement(double randMoveRate, Entity baseEntity, MobileEntity playerTarget, EntityMover entityMover) {
		super(randMoveRate, baseEntity, playerTarget, entityMover);
	}
	
	@Override
	protected Coord getTargetCoord() {
		return super.getPlayerCoord();
	}
}
