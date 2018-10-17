package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.Entity;

public class HoundEnemyMovement extends EnemyMovement {

	private MobileEntity hunter;
	
	public HoundEnemyMovement(double randMoveRate, Entity baseEntity, Entity playerTarget, MobileEntity hunter, EntityMover entityMover) {
		super(randMoveRate, baseEntity, playerTarget, entityMover);
		this.hunter = hunter;
	}

	
	@Override
	protected Coord getTargetCoord() {
		Coord player = super.getTargetCoord();
		Direction hunterDir = hunter.getDirection();
		return player.add(hunterDir);
	}
}
