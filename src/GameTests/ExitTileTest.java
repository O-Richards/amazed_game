package GameTests;

import static org.junit.Assert.*;

import org.junit.Test;


import GameMain.*;

public class ExitTileTest {
	PlayerMobileEntity p;
	ExitTile E; 
	Coord c ;
	WinSystem winSystem;
	//Initial conditions:
	@Test
	public void initialConditions(){
		c = new Coord(2, 2);
		p = new PlayerMobileEntity(c);
		winSystem = new WinSystem();
		E = new ExitTile(c, this.winSystem.newWinCondition(WinType.WIN), this.winSystem.newWinCondition(WinType.EXIT));
	}
	//Checks that the player wins upon exit: 
	@Test
	public void testUpdateWinCondition() {
		
	}

	@Test
	public void testGetSprite() {
		initialConditions();
		assertEquals(E.getSprite(), "!");
	}

	@Test
	public void testExitTile() {
	}

}
