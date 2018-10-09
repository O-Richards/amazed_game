package gameModel;

public class ArrowUsable implements Usable {

	private EntityMover entityMover;
	private PlayerMobileEntity player;
	
	ArrowUsable(EntityMover entityMover) {
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
					.withMovement(new EntityTrackingMovement())
					.build();
			this.entityMover.placeEntity(killingArrow, killingArrow.getCoord());
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
