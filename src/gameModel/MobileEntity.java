package gameModel;

import java.util.ArrayList;
import java.util.List;

public class MobileEntity implements Movement, Entity {
	private static final boolean DEBUG = false;
	private Movement movement;
	private Entity baseEntity;
	private Integer lastTick = -1;
	private int lastMoveTickNum = -1;
	private final boolean isPlayer;
	private final KillAction killAction;
	private final List<KillAction> killedBy;
	
	
	MobileEntity(MobileEntityBuilder builder) {
		this.baseEntity = builder.getBaseEntity();
		this.movement = builder.getMovement();
		this.isPlayer = builder.getIsPlayer();
		this.killAction = builder.getKillAction();
		this.killedBy = builder.getKilledBy();
	}
	
	@Override
	public void tick(Integer tickNum) {
		//Make sure to only move once per tick
		if (this.lastTick != tickNum) {
			this.lastTick = tickNum;
		}
	}
	
	public void setAlive(boolean alive) {
		this.baseEntity.setAlive(alive);
	}
	
	public boolean isAlive() {
		return this.baseEntity.isAlive();
	}
	
	public Direction getDirection() {
		return this.movement.getDirection();
	}	
	
	public void setDirection(Direction dir) {
		this.movement.setDirection(dir);
		if (DEBUG) System.out.println("Setting MobileEntity " + this.getSprite() + "Direction to " + this.getDirection());
	}
	
	//TODO: Pull this into movement interface
	@Override
	public boolean pickup(Entity item) {
		return this.movement.pickup(item);
	}
	
	@Override
	public boolean kill(KillAction action) {
		if (this.killedBy.contains(action)) {
			return this.movement.kill(action);
		} else {
			return false;
		}
	}
	
	public KillAction getKillAction() {
		return this.killAction;
	}
	
	public boolean canFly() {
		return this.movement.canFly();
	}	
	
	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	public boolean pushEntity() {
		return this.movement.pushEntity();
	}
	
	public void setKeyCode(int keyCode) {
		keyCode = -1;
	}
	
	public int getKeyCode() {
		return -1;
	}

	public Coord nextCoord() {
		return this.movement.nextCoord();
	}
	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	public Movement getMovement() {
		return this.movement;
	}

	public int lastMoveTickNum() {
		return this.lastMoveTickNum;
	}

	public void setLastMoveTickNum(int tickNum) {
		this.lastMoveTickNum  = tickNum;
	}
	
	/**
	 * @return true if the MobileEntity is a Player. false else
	 * Why isPlayer not isNPC? We have boulders, arrows as well as enemies that we want to group togeher
	 */
	public boolean isPlayer() {
		return this.isPlayer;
	}
	
	@Override
	public String getSprite() {
		return baseEntity.getSprite();
	}

	@Override
	public void setCoord(Coord coord) {
		baseEntity.setCoord(coord);
	}

	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		this.baseEntity.applyToPlayer(player);
	}
	
	@Override
	public Coord getCoord() {
		return baseEntity.getCoord();
	}

	@Override
	public Coord getCoord(Direction dir) {
		return baseEntity.getCoord(dir);
	}
	
	@Override
	public boolean use(Action action) {
		return this.baseEntity.use(action);
	}
	
	public static class MobileEntityBuilder {
		private Movement movement;
		private boolean isPlayer = false;
		private Entity baseEntity;
		private KillAction killAction = KillAction.NO_KILL;
		private List<KillAction> killedBy = new ArrayList<KillAction>();
		
		public MobileEntityBuilder(Entity baseEntity) {
			this.baseEntity = baseEntity;
			this.movement = new EntityTrackingMovement(baseEntity);
			this.killedBy.add(KillAction.ALL);
		}
		
		private Movement getMovement() {
			return this.movement;
		}
		
		private boolean getIsPlayer() {
			return this.isPlayer;
		}
		
		private Entity getBaseEntity() {
			return this.baseEntity;
		}
		
		private KillAction getKillAction() {
			return this.killAction;
		}
		
		private List<KillAction> getKilledBy() {
			return this.killedBy;
		}

		public MobileEntityBuilder withMovement(Movement movement) {
			this.movement = movement;
			return this;
		}
		
		public MobileEntityBuilder withKilledBy(KillAction action) {
			this.killedBy.add(action);
			return this;
		}
		
		public MobileEntityBuilder withKillAction(KillAction action) {
			this.killAction = action;
			return this;
		}
		
		public MobileEntityBuilder withIsPlayer(boolean isPlayer) {
			this.isPlayer = isPlayer;
			return this;
		}
		
		public MobileEntity build() {
			return new MobileEntity(this);
		}
	}

}
