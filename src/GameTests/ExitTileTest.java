package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import GameMain.*;

class ExitTileTest {
	PlayerMobileEntity p;
	ExitTile E; 
	Coord c ;
	WinSystem winSystem;
	//Initial conditions:
	@Test
	void initialConditions(){
		c = new Coord(2, 2);
		p = new PlayerMobileEntity(c);
		winSystem = new WinSystem();
		E = new ExitTile(c, this.winSystem.newWinCondition(WinType.WIN), this.winSystem.newWinCondition(WinType.EXIT));
	}
	//Checks that the player wins upon exit: 
	@Test
	void testUpdateWinCondition() {
		
	}

	@Test
	void testGetSprite() {
		initialConditions();
		assertEquals(E.getSprite(), "!");
	}

	@Test
	void testExitTile() {
	}

}
