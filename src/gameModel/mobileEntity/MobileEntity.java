package gameModel.mobileEntity;

import java.util.ArrayList;
import java.util.List;

import gameModel.Coord;
import gameModel.KillAction;
import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.usable.Usable;

public class MobileEntity implements Movement, Entity {
	private static final boolean DEBUG = false;
	private Movement movement;
	private Entity baseEntity;
	private Integer lastTick = -1;
	private int lastMoveTickNum = -1;
	private final boolean isPlayer;
	private final KillAction killAction;
	private final List<KillAction> killedBy;
	private boolean pushable = false;
	private boolean killedByAnything = false;
	private boolean canTriggerSwitches = false;
	private boolean canPushEntities;
	private boolean moving;
	
	
	MobileEntity(MobileEntityBuilder builder) {
		this.baseEntity = builder.baseEntity;
		this.movement = builder.movement;
		this.isPlayer = builder.isPlayer;
		this.killAction = builder.killAction;
		this.killedBy = builder.killedBy;
		this.killedByAnything = builder.killedByAnything;
		this.pushable = builder.pushable;
		this.canTriggerSwitches = builder.canTriggerSwitches;
		this.canPushEntities = builder.canPush;
		this.moving = builder.isMoving;
		
		this.movement.setMobileEntity(this);
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

	@Override
	public Direction getDirection() {
		return this.movement.getDirection();
	}	
	
	@Override
	public void setDirection(Direction dir) {
		this.movement.setDirection(dir);
		if (DEBUG) System.out.println("Setting MobileEntity " + this.getVisType() + "Direction to " + this.getDirection());
	}
	
	//TODO: Pull this into movement interface
	@Override
	public boolean pickup(Usable item) {
		return this.movement.pickup(item);
	}
	
	@Override
	public boolean kill(KillAction action) {
		if (!this.canDie()) return false;
		if (this.killedByAnything || this.killedBy.contains(action)) {
			if (this.movement.kill(action)) {
				this.setAlive(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean pushable() {
		return this.pushable;
	}
	
	public KillAction getKillAction() {
		return this.killAction;
	}
	
	@Override
	public boolean canFly() {
		return this.movement.canFly();
	}	

	public boolean canTriggerSwitches() {
		return this.canTriggerSwitches;
	}
	
	public void setKeyCode(int keyCode) {
		keyCode = -1;
	}
	
	public int getKeyCode() {
		return -1;
	}

	@Override
	public Coord nextCoord() {
		return this.movement.nextCoord();
	}
	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public boolean canDie() {
		return this.movement.canDie();
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
	
	public boolean canPush() {
		return this.canPushEntities;
	}
	
	public boolean isMoving() {
		return this.moving;
	}
	
	public void setMoving(boolean isMoving) {
		this.moving = isMoving;
	}
	
	@Override
	public VisType getVisType() {
		return baseEntity.getVisType();
	}
	
	@Override
	public Usable getUsable() {
		return this.baseEntity.getUsable();
	}

	@Override
	public void setCoord(Coord coord) {
		baseEntity.setCoord(coord);
	}

	@Override
	public Coord getCoord() {
		return baseEntity.getCoord();
	}

	@Override
	public Coord getCoord(Direction dir) {
		return baseEntity.getCoord(dir);
	}
	
	public static class MobileEntityBuilder {
		public boolean isMoving = false;
		public boolean canPush = false;
		public boolean killedByAnything;
		private Movement movement;
		private boolean isPlayer = false;
		private Entity baseEntity;
		private KillAction killAction = KillAction.NO_KILL;
		private List<KillAction> killedBy = new ArrayList<KillAction>();
		private boolean pushable = false;
		private boolean canTriggerSwitches = false;
		
		public MobileEntityBuilder(Entity baseEntity) {
			this.baseEntity = baseEntity;
			this.movement = new EntityTrackingMovement();
			this.killedBy.add(KillAction.SUPER_KILL);
		}

		public MobileEntityBuilder withMovement(Movement movement) {
			this.movement = movement;
			return this;
		}
		
		public MobileEntityBuilder withKilledBy(KillAction action) {
			this.killedBy.add(action);
			return this;
		}
		
		public MobileEntityBuilder withIsMoving(boolean isMoving) {
			this.isMoving = isMoving;
			return this;
		}
		
		public MobileEntityBuilder withCanPush(boolean pushes) {
			this.canPush = pushes;
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
		
		public MobileEntityBuilder withKilledByAnything(boolean killedByAnything) {
			this.killedByAnything = killedByAnything;
			return this;
		}
		
		public MobileEntityBuilder withPushable(boolean pushable) {
			this.pushable = pushable;
			return this;
		}
		
		public MobileEntityBuilder withCanTriggerSwitches(boolean canTriggerSwitches) {
			this.canTriggerSwitches = canTriggerSwitches;
			return this;
		}
		
		public MobileEntity build() {
			return new MobileEntity(this);
		}
	}


}
