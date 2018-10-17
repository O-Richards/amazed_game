package gameModel.usable;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.BasicEntity;
import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.EntityTrackingMovement;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;

public class ArrowUsable implements Usable {
	private static final boolean DEBUG = true;
	
	private EntityMover entityMover;
	private PlayerMobileEntity player;
	
	public ArrowUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}
	
	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.ARROW) {
			Coord curr = player.getCoord(player.getDirection());
			Direction movDir = player.getDirection();
			while (entityMover.isEmpty(curr)) {
				entityMover.kill(curr, KillAction.WEAPON);
				curr = curr.add(movDir);
			}
			entityMover.kill(curr, KillAction.WEAPON);
			
			return true;
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {	
		this.player = player;
	}

	@Override
	public UseAction getUseAction() {
		return UseAction.ARROW;
	}

	@Override
	public boolean canBePickedUpWith(Usable u) {
		return true;
	}
	
}
