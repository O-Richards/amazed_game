package gameModel;

public class EntityMaker {
	public BasicEntity makeBasicEntity(EntityType type) {
		
	}
	
	public BasicEntity makeKey(Coord c, EntityMover entityMover) {
		return new BasicEntity.BasicEntityBuilder("K")
				.withEntityMover(entityMover)
				.withCoord(c)
				.withUsage(new KeyUsable())
				.build();
	}
}
