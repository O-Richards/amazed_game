package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.Coord;
import gameModel.Direction;
import gameModel.Entity;
import gameModel.InvincibilityEntity;

public class EntityTest {

	private Entity e;
	private Coord c;
	
	public void setup() {
		c = new Coord(5, 8);
		e = new InvincibilityEntity(c);
	}

	@Test
	public void testGetCoord() {
		setup();
		assertEquals(c, e.getCoord());
	}

	@Test
	public void testGetCoordDirection() {
		setup();
		Coord tmp = c.add(Direction.DOWN);
		assertEquals(tmp, e.getCoord(Direction.DOWN));
	}

	@Test
	public void testSetCoord() {
		setup();
		Coord tmp = c.add(Direction.DOWN);
		e.setCoord(tmp);
		assertEquals(tmp, e.getCoord());
	}

	@Test
	public void testEqualsObject() {
		setup();
		assertEquals(e, new InvincibilityEntity(c));
	}
}
