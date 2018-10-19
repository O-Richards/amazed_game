package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.Entity;

public class StrategistEnemyMovement extends EnemyMovement {
	
	public StrategistEnemyMovement(double randMoveRate, Entity baseEntity, MobileEntity player, EntityMover entityMover) {
		super(randMoveRate, baseEntity, player, entityMover);
	}
	
	@Override
	protected Coord getTargetCoord() {
		return super.getPlayerCoord().add(super.getPlayerDirection());
	}
}
