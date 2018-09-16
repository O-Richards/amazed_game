package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import GameMain.ArrowUsableEntity;
import GameMain.Coord;
import GameMain.Direction;

class ArrowUsableEntityTest {

	Coord c;
	Direction d;
	ArrowUsableEntity a;
	
	@Before
	public void setUp() throws Exception {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		a = new ArrowUsableEntity(c);
	}
	
	@Test
	void testUse() {
		assertFalse(a.use(d));
	}
}
