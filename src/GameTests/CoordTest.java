package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import GameMain.ArrowMobileEntity;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.PlayerMobileEntity;

class CoordTest {
	
	Coord c;
	Integer x;
	Integer y;
	
	@Before
	public void setUp() throws Exception {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
	}

	@Test
	void testGetX() {
		assertEquals(c.getX(), x);
	}

	@Test
	void testSetX() {
		c.setX(y);
		assertEquals(c.getX(), y);
	}

	@Test
	void testGetY() {
		assertEquals(c.getY(), y);
	}

	@Test
	void testSetY() {
		c.setY(x);
		assertEquals(c.getY(), x);
	}

	@Test
	void testAdd() {
		c.add(Direction.DOWN);
		assertEquals(c.getX(), new Integer(4));
		c.add(Direction.UP);
		assertEquals(c.getX(), new Integer(5));
		c.add(Direction.LEFT);
		assertEquals(c.getX(), new Integer(7));
		c.add(Direction.RIGHT);
		assertEquals(c.getX(), new Integer(8));
	}

	@Test
	void testMinusX() {
		fail("Not yet implemented");
	}

	@Test
	void testMinusY() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

}
