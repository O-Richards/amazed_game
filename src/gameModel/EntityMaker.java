package gameModel;

public class EntityMaker {
	private WinSystem winSystem;
	private EntityMover entityMover;
	
	public EntityMaker (WinSystem winSystem, EntityMover entityMover) {
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
	
}
