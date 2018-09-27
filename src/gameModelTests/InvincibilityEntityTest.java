package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.Coord;
import gameModel.Direction;
import gameModel.InvincibilityEntity;

public class InvincibilityEntityTest {

	InvincibilityEntity i;
	
	@Test
	public void testUse() {
		i = new InvincibilityEntity(new Coord(5, 8));
		assertFalse(i.use(Direction.UP));
	}

}
