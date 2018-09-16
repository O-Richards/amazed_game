package GameTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Coord;
import GameMain.Direction;
import GameMain.InvincibilityEntity;

public class InvincibilityEntityTest {

	InvincibilityEntity i;
	
	@Test
	public void testUse() {
		i = new InvincibilityEntity(new Coord(5, 8));
		assertFalse(i.use(Direction.UP));
	}

}
