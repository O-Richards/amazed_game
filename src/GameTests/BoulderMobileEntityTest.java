package GameTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import GameMain.ArrowMobileEntity;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.PlayerMobileEntity;
import GameMain.UsableEntity;

public class BoulderMobileEntityTest {

	Coord c;
	Direction d;
	PlayerMobileEntity p;
	ArrowMobileEntity a;
	UsableEntity u;
	
	@Before
	public void setUp() throws Exception {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		p = new PlayerMobileEntity(c);
		a = new ArrowMobileEntity(c, d);
	}
	
	@Test
	public void testPickup() {
		assertFalse(a.pickup(u));
	}

	@Test
	public void testKillPlayer() {
		assertFalse(a.killEnemy());
	}

	@Test
	public void testKillEnemy() {
		assertFalse(a.killPlayer());
	}

}
