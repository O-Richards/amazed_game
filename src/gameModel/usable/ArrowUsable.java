package gameModel.usable;

import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.BasicEntity;
import gameModel.entity.Entity;
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
			Entity baseEntity = new BasicEntity.BasicEntityBuilder(">", player.getCoord(player.getDirection()))
					.build();
			MobileEntity killingArrow = new MobileEntity.MobileEntityBuilder(baseEntity)
					.withKillAction(KillAction.WEAPON)
					.withKilledByAnything(true)
					.withMovement(new EntityTrackingMovement(player.getDirection()))
					.build();
			if (DEBUG) {
				System.out.println("ArrowUsable.use(): placing arrowMobile at " 
					+ killingArrow.getCoord() + " Player at "  + player.getCoord() 
					+ " player dir " + player.getDirection()
					); 
			}
			try {
				this.entityMover.placeMobileEntity(killingArrow);
			} catch (EntityPlacementException e) {
				System.out.println("ArrowUsable.use: ERROR: exception thrown when placing moving arrow" + e);
			}
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
	
}
