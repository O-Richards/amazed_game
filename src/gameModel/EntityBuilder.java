package gameModel;

import gameModel.BasicEntity.BasicEntityBuilder;

public interface EntityBuilder {
		public EntityBuilder withCoord(Coord c);
		
		public EntityBuilder withEntityMover(EntityMover e);
		
		public Coord getCoord();

		public String getSprite();

		public EntityMover getEntityMover();
		
		public Entity build();

		public Usable getUse();

		public BasicEntityBuilder withWinCondition(WinCondition winCondition);

		public WinCondition getWinCondition();

		public boolean getAliveEntity();

		public BasicEntityBuilder withAlive(boolean isAlive);

		public BasicEntityBuilder withUsage(Usable use);

}
