package gameModel;

public class EntityMaker {
	private WinSystem winSystem;
	private EntityMover entityMover;
	
	public EntityMaker(WinSystem winSystem, EntityMover entityMover) {
		this.winSystem = winSystem;
		this.entityMover = entityMover;
	}
	
	public BasicEntity makeKey(Coord c, EntityMover entityMover) {
		return new BasicEntity.BasicEntityBuilder("K")
				.withEntityMover(entityMover)
				.withCoord(c)
				.withUsage(new KeyUsable())
				.build();
	}
	
	public BasicEntity makeArrow(Coord c) {
		return new BasicEntity.BasicEntityBuilder("<")
			.withCoord(c)
			.withUsage(new ArrowUsable(entityMover))
			.build();
	}
	
	public Entity makeTreasure(Coord c, EntityMover entityMover) {
		Usable treasureUsage = new Usable() {

			@Override
			public boolean use(Action action) {
				return false;
			}

			@Override
			public void applyToPlayer(PlayerMobileEntity player) {
				player.incrementTreasureNo();
			}
		};
		
		return new BasicEntity.BasicEntityBuilder("$")
				.withEntityMover(entityMover)
				.withCoord(c)
				.withWinCondition(this.winSystem.newWinCondition(WinType.TREASURE))
				.withUsage(treasureUsage)
				.build();
	}
	
	public Entity makeInvincibilityEntity(Coord c) {
		Usable invincibilityUse = new Usable() {
			@Override
			public void applyToPlayer(PlayerMobileEntity player) {
				player.setMovement(new InvincibilityBonusAction(player.getMovement()));
			}

			@Override
			public boolean use(Action action) {
				return false;
			}
			
		};
		
		return new BasicEntity.BasicEntityBuilder("I")
				.withCoord(c)
				.withUsage(invincibilityUse)
				.withEntityMover(entityMover)
				.build();
	}
}
