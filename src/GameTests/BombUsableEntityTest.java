package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import GameMain.BombUsableEntity;
import GameMain.Coord;
import GameMain.Direction;

class BombUsableEntityTest {

	BombUsableEntity b;
	Coord c;
	Direction d;
	
	@Before
	public void setUp() throws Exception {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		b = new BombUsableEntity(c);
	}

	@Test
	void testUse() {
		assertFalse(b.use(d));
	}
}
