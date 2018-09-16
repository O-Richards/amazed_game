package GameTests;

import static org.junit.jupiter.api.Assertions.fail;

import GameMain.Collision;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.Entity;
import GameMain.EntityMover;
import GameMain.MobileEntity;

/**
 * A EntityMover that will fail the test if any method is called, except checkSpecialTIle
 *
 */
public abstract class TestingMover implements EntityMover {

	@Override
	public Collision moveEntity(MobileEntity e, Direction dir) {
		moveEntity(e, e.getCoord(dir));
		fail("Should not move entities");
		return null;
	}

	@Override
	public void removeEntity(Entity entity, Coord c) {
		fail("Should not remove entities");
	}

	@Override
	public Collision moveEntity(MobileEntity e, Coord nextCoord) {
		fail("Should not move entities");
		return null;
	}

	@Override
	public void placeEntity(Entity entity, Coord c) {
		fail("Should not place entities");
		
	}

	@Override
	public boolean killEnemyEntities(Coord c) {
		fail("Should not kill entities");
		return false;
	}

}
