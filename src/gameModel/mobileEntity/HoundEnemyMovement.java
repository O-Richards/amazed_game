package gameModel.mobileEntity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.Entity;

public class HoundEnemyMovement extends EnemyMovement {

	private MobileEntity hunter;
	
	public HoundEnemyMovement(double randMoveRate, Entity baseEntity, MobileEntity player, MobileEntity hunter, EntityMover entityMover) {
		super(randMoveRate, baseEntity, player, entityMover);
		this.hunter = hunter;
	}

	
	@Override
	protected Coord getTargetCoord() {
		Coord player = super.getPlayerCoord();
		Direction hunterDir = hunter.getDirection();
		return player.add(hunterDir);
	}
}
