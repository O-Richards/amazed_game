package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.Coord;
import gameModel.EnemyMobileEntity;
import gameModel.KeyUsableEntity;
import gameModel.UsableEntity;

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
		//assertFalse(e.killPlayer());
	}

}
