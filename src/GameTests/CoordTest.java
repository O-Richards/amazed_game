package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameMain.Coord;
import GameMain.Direction;

class CoordTest {
	
	Coord c;
	Integer x;
	Integer y;

	@Test
	void testGetX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c.getX(), x);
	}

	@Test
	void testSetX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		c.setX(y);
		assertEquals(c.getX(), y);
	}

	@Test
	void testGetY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c.getY(), y);
	}

	@Test
	void testSetY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		c.setY(x);
		assertEquals(c.getY(), x);
	}

	@Test
	void testAdd() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		Coord tmp = c.add(Direction.DOWN);
		assertEquals(tmp.getX(), new Integer(4));
		tmp = c.add(Direction.UP);
		assertEquals(tmp.getX(), new Integer(6));
		tmp = c.add(Direction.LEFT);
		assertEquals(tmp.getY(), new Integer(7));
		tmp = c.add(Direction.RIGHT);
		assertEquals(tmp.getY(), new Integer(9));
	}

	@Test
	void testMinusX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		Coord c2 = new Coord(0, 0);
		assertEquals(c.minusX(c2), Direction.DOWN);
	}

	@Test
	void testMinusY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		Coord c2 = new Coord(0, 0);
		assertEquals(c.minusY(c2), Direction.LEFT);
	}

	@Test
	void testEqualsObject() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c, c);
	}

}
