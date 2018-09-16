//package GameTests;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import GameMain.Coord;
//import GameMain.EmptyTile;
//import GameMain.Entity;
//import GameMain.ExitTile;
//import GameMain.HoverPotion;
//import GameMain.SwitchTile;
//import GameMain.Tile;
//import GameMain.WallTile;
//import GameMain.WinSystem;
//import GameMain.WinType;
//import junit.framework.TestCase;
//
//public class testTile extends TestCase {
//	List<Tile> tiles;
//	Tile switchTile;
//	Tile exitTile;
//	Tile wallTile;
//	Tile emptyTile;
//
//	Coord tileCoord;
//	@Before
//	public void setUp() throws Exception {
//		WinSystem winSystem = new WinSystem();
//		tileCoord = new Coord(1, 1);
//		emptyTile = new EmptyTile(tileCoord, winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
//		wallTile = new WallTile(tileCoord, winSystem.newWinCondition(WinType.WIN));
//		exitTile = new ExitTile(tileCoord, winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
//		switchTile = new SwitchTile(tileCoord, winSystem.newWinCondition(WinType.WIN), winSystem.newWinCondition(WinType.WIN));
//		tiles = new ArrayList<>();
//		tiles.add(emptyTile);
//		tiles.add(wallTile);
//		tiles.add(exitTile);
//		tiles.add(switchTile);
//	}
//
//	@Test
//	public void testTick() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testContainsEntity() {
//		Entity entity = new HoverPotion(tileCoord)l
//		for (Tile t : tiles) {
//			t.addEntity(entity);
//			assertTrue(t.containsEntity(entity));
//		}
//	}
//
//
//
//	@Test
//	public void testRemoveEntity() {
//		Entity entity = new HoverPotion(tileCoord)l
//		for (Tile t : tiles) {
//			t.addEntity(entity);
//			assertTrue(t.containsEntity(entity));
//			t.removeEntity(entity);
//			assertFalse(t.containsEntity(entity));
//
//		}	
//	}
///*
//	@Test
//	public void testCollide() {
//		fail("Not yet implemented");
//	}
//*/
///*
//	@Test
//	public void testCollideExt() {
//		fail("Not yet implemented");
//	}
//*/
//	@Test
//	public void testUpdateEnemyCondition() {
//		Entity entity = new EnemyMobileEntity(tileCoord)l
//
//	}
//
//	@Test
//	public void testUpdateWinCondition() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCoord() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSprite() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testKillEnemyEntities() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testAddEntity() {
//		Entity entity = new HoverPotion(tileCoord)l
//		for (Tile t : tiles) {
//			t.addEntity(entity);
//			assertTrue(t.containsEntity(entity));
//	}
//
//}
