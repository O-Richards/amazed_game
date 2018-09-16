package GameTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.BombUsableEntity;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.EnemyMobileEntity;
import GameMain.EntityTrackingMovement;
import GameMain.UsableEntity;

public class MobileEntityTest {

	EnemyMobileEntity e;
	UsableEntity u;
	EntityTrackingMovement m;
	
	public void setup() {
		Coord c = new Coord(5, 8);
		e = new EnemyMobileEntity(c);
		u = new BombUsableEntity(c);
		m = new EntityTrackingMovement(e);
	}
	
	@Test
	public void testSetGetDirection() {
		setup();
		e.setDirection(Direction.DOWN);
		assertEquals(e.getDirection(), Direction.DOWN);
	}

	@Test
	public void testPickup() {
		setup();
		assertFalse(e.pickup(u));
	}

	@Test
	public void testCanFly() {
		setup();
		assertFalse(e.canFly());
	}

	@Test
	public void testSetGetMovement() {
		setup();
		e.setMovement(m);
		assertEquals(m, e.getMovement());
	}

}
