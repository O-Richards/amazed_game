package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameModel.*;


public class HoverPotionTest {
	Coord c;
	PlayerMobileEntity p;
	HoverPotion h; 
 	UsableEntity u;
 	EmptyTile tile; 
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
	public void testGetSprite() {
		setUp();
		assertEquals(h.getSprite(), new String("H"));
	}
	
	@Test
	public void testUse() {
		setUp();
		assertFalse(h.use(d));
	}

	@Test
	public void testHoverPotion() {
		setUp();
		assertTrue(p.pickup(h));
		p.setMovement(new HoverBonusMovement(p.getMovement()));
		assertTrue(p.canFly());
	}

}
