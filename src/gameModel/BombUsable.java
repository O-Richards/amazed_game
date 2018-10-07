package gameModel;

public class BombUsable implements Usable {

	private EntityMover entityMover;
	private PlayerMobileEntity player;
	
	BombUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}
	
	@Override
	public boolean use(Action action) {
		if (action == Action.BOMB) {
			Entity baseEntity = new BasicEntity.BasicEntityBuilder("B")
					.withCoord(player.getCoord())
					.build();
			MobileEntity killingBomb = new MobileEntity.MobileEntityBuilder(baseEntity)
					.withKillAction(KillAction.SUPER_KILL)
					.withKilledByAnything(true)
					// .withCallback thingo
					.build();
			this.entityMover.placeEntity(killingBomb, killingBomb.getCoord());
			return true;
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.player = player;
	}

}
