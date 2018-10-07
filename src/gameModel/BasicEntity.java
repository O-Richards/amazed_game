package gameModel;

public class BasicEntity implements Entity, Usable {

	//Coord the entity is on. Note if in inventory, this will be null
	private Coord coord;
	private String sprite;
	protected EntityMover entityMover;
	private Usable usage;
	private WinCondition winCondition;
	private boolean alive;
	
	protected BasicEntity(EntityBuilder builder) {
		this.coord = builder.getCoord();
		this.sprite = builder.getSprite();
		this.entityMover = builder.getEntityMover();
		this.winCondition = builder.getWinCondition();
		this.alive = builder.getAliveEntity();
		this.usage = builder.getUse();
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
	public boolean use(Action action) {
		return this.usage.use(action);
	}

	@Override
	public String getSprite() {
		return this.sprite;
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

	public static class BasicEntityBuilder implements EntityBuilder {
		private Coord coord;
		private String sprite;
		private EntityMover entityMover;
		private Usable use;
		private WinCondition winCondition;
		private boolean aliveEntity = false;
		
		public BasicEntityBuilder(String sprite) {
			this.sprite = sprite;
			
			Usable noAction = new Usable() {
				@Override
				public boolean use(Action action) {
					return false;
				}

				@Override
				public void applyToPlayer(PlayerMobileEntity player) {
					
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
		public BasicEntityBuilder withCoord(Coord c) {
			this.coord = c;
			return this;
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
		public Coord getCoord() {
			return coord;
		}

		@Override
		public String getSprite() {
			return sprite;
		}
		
		@Override
		public Usable getUse() {
			return this.use;
		}

		@Override
		public EntityMover getEntityMover() {
			return entityMover;
		}
		
		@Override
		public WinCondition getWinCondition() {
			return this.winCondition;
		}
		
		@Override
		public BasicEntity build() {
			return new BasicEntity(this);
		}

		@Override
		public boolean getAliveEntity() {
			return this.aliveEntity;
		}
	}




}
