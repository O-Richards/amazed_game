package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameMain.ArrowMobileEntity;
import GameMain.BombUsableEntity;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.EnemyMobileEntity;
import GameMain.PlayerMobileEntity;

class BombUsableEntityTest {

	BombUsableEntity b;
	
	@before
	public void setUp() throws Exception {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		p = new PlayerMobileEntity(c);
		e = new EnemyMobileEntity(c);
		a = new ArrowMobileEntity(c, d);
	}
	
	@Test
	void testTick() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSprite() {
		fail("Not yet implemented");
	}

	@Test
	void testUse() {
		fail("Not yet implemented");
	}

	@Test
	void testBombUsableEntity() {
		fail("Not yet implemented");
	}

}
