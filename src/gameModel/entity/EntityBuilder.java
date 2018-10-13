package gameModel.entity;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.BasicEntity.BasicEntityBuilder;
import gameModel.usable.Usable;
import gameModel.winCondition.WinCondition;

public interface EntityBuilder {
		
		public EntityBuilder withEntityMover(EntityMover e);
		
		public Entity build();

		public BasicEntityBuilder withWinCondition(WinCondition winCondition);

		public BasicEntityBuilder withAlive(boolean isAlive);

		public BasicEntityBuilder withUsage(Usable use);
}
