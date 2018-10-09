package gameModel;

public class BombUsable implements Usable, DelayedAction {
	private final Integer NUM_TICKS_TO_EXPLODE = 5;

	private EntityMover entityMover;
	private Coord useLocation;
	private PlayerMobileEntity player;
	
	BombUsable(EntityMover entityMover) {
		this.entityMover = entityMover;
	}
	
	@Override
	public boolean use(UseAction action) {
		if (action == UseAction.BOMB) {
			this.entityMover.addDelayedAction(this, this.NUM_TICKS_TO_EXPLODE);
		}
		return false;
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.useLocation = player.getCoord();
		this.player = player;
	}

	@Override
	public void performDelayedAction() {
		this.entityMover.kill(useLocation.add(Direction.UP), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.DOWN), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.LEFT), KillAction.SUPER_KILL);
		this.entityMover.kill(useLocation.add(Direction.RIGHT), KillAction.SUPER_KILL);
	}

	@Override
	public UseAction getUseAction() {
		return UseAction.BOMB;
	}
}
