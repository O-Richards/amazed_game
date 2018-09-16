package GameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import GameMain.Coord;
import GameMain.EmptyTile;
import GameMain.Tile;
import GameMain.WinSystem;
import GameMain.WinType;
import junit.framework.TestCase;

class TileTest extends TestCase{

	@Override
	protected void setUp() throws Exception {
		WinSystem winSystem = new WinSystem();
		Tile emptyTile = new EmptyTile(new Coord(1, 1), winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
		Tile wallTile = new EmptyTile(new Coord(1, 1), winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
		Tile exitTile = new EmptyTile(new Coord(1, 1), winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
		Tile switchTile = new EmptyTile(new Coord(1, 1), winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
		List<Tile> tiles = new ArrayList<>();
		tiles.add(emptyTile);
		tiles.add(wallTile);
		tiles.add(exitTile);
		tiles.add(switchTile);

	}
	
	@Test
	void testAdd() {
		//test adding
	}

}
