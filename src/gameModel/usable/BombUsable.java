package gameModel.usable;

import gameModel.Coord;
import gameModel.DelayedAction;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.*;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.tile.EntityPlacementException;

public class BombUsable implements Usable, DelayedAction {
	private final Integer NUM_TICKS_TO_EXPLODE = 5;

	private EntityMover entityMover;
	private Entity user;
	private Coord useLocation;
	private boolean isVisualised = false;
	
	public BombUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}
	
	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.BOMB) {
			useLocation = user.getCoord();
			this.entityMover.addDelayedAction(this, this.NUM_TICKS_TO_EXPLODE);
			
			Entity base = new BasicEntity.BasicEntityBuilder(VisType.LIT_BOMB, useLocation)
					.withEntityMover(entityMover)
					.build();
			
			try {
				entityMover.placeItem(base);
				isVisualised = true;
			} catch (EntityPlacementException e) {
				isVisualised = false;
			}
			
			return true;
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.user = player;
	}

	@Override
	public void performDelayedAction() {
		this.entityMover.kill(useLocation.add(Direction.UP), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.DOWN), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.LEFT), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.RIGHT), KillAction.SUPER_KILL);
		
		if (isVisualised) {
			entityMover.clearItem(useLocation);
		}
	}

	@Override
	public UseAction getUseAction() {
		return UseAction.BOMB;
	}

	@Override
	public boolean canBePickedUpWith(Usable u) {
		if (u.getUseAction() == this.getUseAction()) {
			return false;
		}
		return true;
	}
}
