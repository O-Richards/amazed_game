package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.ArrowMobileEntity;
import gameModel.Coord;
import gameModel.Direction;
import gameModel.PlayerMobileEntity;
import gameModel.TreasureEntity;
import gameModel.UsableEntity;

public class TreasureEntityTest {
	Coord c;
	Direction d;
	PlayerMobileEntity p;
	TreasureEntity t;
	UsableEntity u;
	@Test 
	public void intialConditions() {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		p = new PlayerMobileEntity(c);
		t = new TreasureEntity(c);
	}
	@Test
	public void testGetSprite() {
		intialConditions();
		assertEquals(t.getSprite(), "$");
	}

	@Test
	public void testUse() {
		intialConditions();
		assertTrue(t.use(Direction.DOWN));
	}
}
