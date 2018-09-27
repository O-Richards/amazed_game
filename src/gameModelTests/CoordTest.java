package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.Coord;
import gameModel.Direction;

public class CoordTest {
	
	Coord c;
	Integer x;
	Integer y;

	@Test
	public void testGetX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c.getX(), x);
	}

	@Test
	public void testSetX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		c.setX(y);
		assertEquals(c.getX(), y);
	}

	@Test
	public void testGetY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c.getY(), y);
	}

	@Test
	public void testSetY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		c.setY(x);
		assertEquals(c.getY(), x);
	}

	@Test
	public void testAdd() {
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
	public void testMinusX() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		Coord c2 = new Coord(0, 0);
		assertEquals(c.minusX(c2), Direction.DOWN);
	}

	@Test
	public void testMinusY() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		Coord c2 = new Coord(0, 0);
		assertEquals(c.minusY(c2), Direction.LEFT);
	}

	@Test
	public void testEqualsObject() {
		x = new Integer(5);
		y = new Integer(8);
		c = new Coord(x, y);
		assertEquals(c, c);
	}

}
