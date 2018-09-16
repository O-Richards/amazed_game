package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParsePosition;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import GameMain.*;


class HoverPotionTest {
	Coord c;
	PlayerMobileEntity p;
	HoverPotion h; 
 	UsableEntity u;
 	Tile tile; 
 	Direction d; 
	@Test
	public void setUp() {
		c = new Coord(5, 8);
		d = Direction.DOWN;
		p = new PlayerMobileEntity(c);
		h = new HoverPotion(c);
	}
	
	//Returns a sprite of the Hover potion
	@Test
	void testGetSprite() {
		setUp();
		assertEquals(h.getSprite(), new String("H"));
	}
	
	@Test
	void testUse() {
		setUp();
		assertFalse(h.use(d));
	}

	@Test
	void testHoverPotion() {
		setUp();
		assertTrue(p.pickup(h));
		p.setMovement(new HoverBonusMovement(p.getMovement()));
		assertTrue(p.canFly());
	}

}
