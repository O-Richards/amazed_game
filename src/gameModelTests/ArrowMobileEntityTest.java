package gameModelTests;
import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.*;

public class ArrowMobileEntityTest {
	Coord c;
	Direction d;
	PlayerMobileEntity p;
	ArrowMobileEntity a;
	UsableEntity u;
	
	public void setUp() {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		p = new PlayerMobileEntity(c);
		a = new ArrowMobileEntity(c, d);
	}
	
	@Test
	public void testPickup() {
		setUp();
		assertFalse(a.pickup(u));
	}

	@Test
	public void testKillPlayer() {
		setUp();
		assertFalse(a.killEnemy());
	}

	@Test
	public void testKillEnemy() {
		setUp();
		assertFalse(a.killPlayer());
	}

}
