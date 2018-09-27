package gameModelTests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import gameModel.Collision;
import gameModel.Coord;
import gameModel.DoorTile;
import gameModel.PlayerMobileEntity;
import gameModel.WallTile;
import gameModel.WinSystem;
import gameModel.WinType;

public class WallTileTest {
	PlayerMobileEntity p;
	WallTile w; 
	WinSystem winSystem;
	Coord c ;
	@Test
	public void initialConditions() {
		c = new Coord(2, 2);
		p = new PlayerMobileEntity(c);
		winSystem = new WinSystem();
		w = new WallTile(c, this.winSystem.newWinCondition(WinType.WIN));		
	}
	@Test
	public void testCollideExt() {
		initialConditions();
		assertEquals(w.collideExt(p, Collision.NOMOVE), Collision.NOMOVE);
	}

	@Test
	public void testGetSprite() {
		initialConditions();
		assertEquals(w.getSprite(),"W");
	}


	@Test
	public void testEqualsObject() {
		initialConditions();
		assertTrue(w.equals(new WallTile(c, this.winSystem.newWinCondition(WinType.WIN))));
	}

}
