package GameTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Coord;
import GameMain.EnemyMobileEntity;
import GameMain.KeyUsableEntity;
import GameMain.UsableEntity;

public class EnemyMobileEntityTest {

	Coord c;
	EnemyMobileEntity e;
	UsableEntity u;
	
	public void setUp() {
		c = new Coord(5, 8);
		e = new EnemyMobileEntity(c);
		u = new KeyUsableEntity(c);
	}

	@Test
	public void testPickup() {
		setUp();
		assertFalse(e.pickup(u));
	}

	@Test
	public void testKillPlayer() {
		setUp();
		assertFalse(e.killPlayer());
	}

}
