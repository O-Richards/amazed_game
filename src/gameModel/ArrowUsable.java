package gameModel;

public class ArrowUsable implements Usable {

	private EntityMover entityMover;
	private PlayerMobileEntity player;
	
	ArrowUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}
	
	@Override
	public boolean use(Action action) {
		if (action == Action.ARROW) {
			Entity baseEntity = new BasicEntity.BasicEntityBuilder(">")
					.withCoord(player.getCoord(player.getDirection()))
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
	
}
