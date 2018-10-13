package gameModel.entity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.mobileEntity.Direction;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.usable.Usable;
import gameModel.usable.UseAction;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinType;

public class BasicEntity implements Entity {

	//Coord the entity is on. Note if in inventory, this will be null
	private Coord coord;
	private VisType visType;
	protected EntityMover entityMover;
	private Usable usage;
	private WinCondition winCondition;
	private boolean alive;
	
	protected BasicEntity(BasicEntityBuilder builder) {
		this.coord = builder.coord;
		this.visType = builder.visType;
		this.entityMover = builder.entityMover;
		this.winCondition = builder.winCondition;
		this.alive = builder.aliveEntity;
		this.usage = builder.use;
	}
	
	@Override
	public Coord getCoord() {
		//TODO: Should really check if the tile is null in some nice way
		//For now lets leave it raising an exception.
		return this.coord;
	}
	
	/**
	 * @param dir the direction from the entity we want a Coord of.
	 * @return the Coord of the tile in the direction dir from the entity
	 */
	@Override
	public Coord getCoord(Direction dir) {
		return this.getCoord().add(dir);
	}
	
	// only used by mobileEntities to get movement rate
	@Override
	public void tick(Integer tickNum) {
	
	}
	
	@Override
	public VisType getVisType() {
		return this.visType;
	}

	@Override
	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		
		BasicEntity e = (BasicEntity)obj;
		if (e.getCoord().equals(this.getCoord())) return true;
		return false;
	}
	
	@Override
	public void setAlive(boolean b) {
		this.alive = b;
	}
	
	@Override
	public boolean isAlive() {
		return this.alive;
	}
	
	@Override
	public boolean pickup(Usable item) {
		return false;
	}

	@Override
	public Usable getUsable() {
		return this.usage;
	}
	
	/**
	 * @param satisfied sets the win condition as satisfied by winType
	 */
	public void setWinConditionType(WinType winType) {
		this.winCondition.setType(winType);
	}

	public static class BasicEntityBuilder implements EntityBuilder {
		private Coord coord;
		private VisType visType;
		private EntityMover entityMover;
		private Usable use;
		private WinCondition winCondition;
		private boolean aliveEntity = false;
		
		public BasicEntityBuilder(VisType visType, Coord c) {
			this.visType = visType;
			this.coord = c;
			
			Usable noAction = new Usable() {
				@Override
				public boolean use(UseAction action) {
					return false;
				}

				@Override
				public void applyToPlayer(PlayerMobileEntity player) {
					
				}

				@Override
				public UseAction getUseAction() {
					return UseAction.NONE;
				}
			};
			this.use = noAction;
			
			WinCondition noWinCondition = new WinCondition() {

				@Override
				public WinType getType() {
					return WinType.WIN;
				}

				@Override
				public void setType(WinType winType) {
				}
				
			};
			this.winCondition = noWinCondition;
		}
		
		@Override
		public BasicEntityBuilder withEntityMover(EntityMover e) {
			this.entityMover = e;
			return this;
		}
		
		@Override 
		public BasicEntityBuilder withAlive(boolean isAlive) {
			this.aliveEntity = isAlive;
			return this;
		}
		
		@Override
		public BasicEntityBuilder withWinCondition(WinCondition winCondition) {
			this.winCondition = winCondition;
			return this;
		}
		
		@Override
		public BasicEntityBuilder withUsage(Usable use) {
			this.use = use;
			return this;
		}
		
		@Override
		public BasicEntity build() {
			return new BasicEntity(this);
		}
	}






}
