package GameTests;

import static org.junit.Assert.*;

import org.junit.Test;

import GameMain.Collision;
import GameMain.Coord;
import GameMain.DoorTile;
import GameMain.PlayerMobileEntity;
import GameMain.WinSystem;
import GameMain.WinType;

public class DoorTileTest {
	//If we collide with a door tile without a key: 
	PlayerMobileEntity p;
	DoorTile d; 
	Coord c ;
	WinSystem winSystem;

	@Test
	public void initialConditions(){
		c = new Coord(2, 2);
		p = new PlayerMobileEntity(c);
		winSystem = new WinSystem();
		d = new DoorTile(c, this.winSystem.newWinCondition(WinType.WIN));
	}
	//If it collides without the right key: 
	@Test
	public void testCollideExt() {
		initialConditions();
		assertEquals(d.collideExt(p, Collision.NOMOVE), Collision.NOMOVE);
	}

	@Test
	public void testGetSprite() {
		initialConditions();
		assertEquals(d.getSprite(), "D");
	}
}
