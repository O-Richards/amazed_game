package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GameMain.Collision;
import GameMain.Coord;
import GameMain.DoorTile;
import GameMain.PlayerMobileEntity;
import GameMain.WinSystem;
import GameMain.WinType;

class DoorTileTest {
	//If we collide with a door tile without a key: 
	PlayerMobileEntity p;
	DoorTile d; 
	Coord c ;
	WinSystem winSystem;

	@Test
	void initialConditions(){
		c = new Coord(2, 2);
		p = new PlayerMobileEntity(c);
		winSystem = new WinSystem();
		d = new DoorTile(c, this.winSystem.newWinCondition(WinType.WIN));
	}
	//If it collides without the right key: 
	@Test
	void testCollideExt() {
		initialConditions();
		assertEquals(d.collideExt(p, Collision.NOMOVE), Collision.NOMOVE);
	}
	
	
	
	@Test
	void testUpdateWinCondition() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSprite() {
		fail("Not yet implemented");
	}

	@Test
	void testDoorTile() {
		fail("Not yet implemented");
	}

}
