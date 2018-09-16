package GameMain;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import GameMain.*;



class junitTestMap {
	GameController gc = new GameController();
	Level l = new Level();
	//Generates a map and check if all items are placed: 
	@Test
	void generateMap() {
		System.out.println("Generating map");
		//A list of Entities on map: 
		ArrayList<entities> allEntities = new ArrayList<>();
		allEntities.add(new entities(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4)));
		allEntities.add(new entities(new BoulderMobileEntity(new Coord(2, 3)), new Coord(2, 3)));
		allEntities.add(new entities(new UnlitBombUsableEntity(new Coord(1, 5)), new Coord(1, 5)));
		allEntities.add(new entities(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4)));
		allEntities.add(new entities(new SwordUsableEntity(new Coord(4, 4)), new Coord(4, 4)));
		allEntities.add(new entities(new BoulderMobileEntity(new Coord(2, 3)), new Coord(2, 3)));
		allEntities.add(new entities(new TreasureEntity(new Coord(5, 6)), new Coord(5, 6)));
	    allEntities.add(new entities(new TreasureEntity(new Coord(5,6)), new Coord(5,6)));
	    allEntities.add(new entities(new TreasureEntity(new Coord(10, 2)), new Coord(10, 2)));
	    allEntities.add(new entities(new HoverPotion(new Coord(3, 4)), new Coord(3, 4)));
	    allEntities.add(new entities(new InvincibilityEntity(new Coord(1,5)), new Coord(1,5)));
	    allEntities.add(new entities(new EnemyMobileEntity(new Coord(7, 7)), new Coord(7, 7)));
	    allEntities.add(new entities(new KeyUsableEntity(null), new Coord(2,5)));
	    allEntities.add(new entities(new KeyUsableEntity(null), new Coord(7,8)));
	    allEntities.add(new entities(new ArrowUsableEntity(null), new Coord(5,8)));
	    
		//Generates some Tiles(i.e. switch, wall exists and pits) 
		l.placeSwitch(new Coord(3, 3));
		l.placeWall(new Coord(4, 5));
		l.placeExit(new Coord(6, 1));
		l.placePit(new Coord(9,9));
		
		
		//Placing items on map: 
		for (int i = 0; i < allEntities.size(); i++) {
			l.placeEntity(allEntities.get(i).getEntity(), allEntities.get(i).getCoord());
		}
		//Ensures that all items are on the map:
		//Not sure how to test this: 
		//Checks that all objects are placed on that coordinate: 
		//assertTrue(); 
		assertTrue(allEntities.get(0).getEntity().equals(new SwordUsableEntity(allEntities.get(0).getCoord())));
		assertTrue(allEntities.get(1).getEntity().equals(new BoulderMobileEntity(allEntities.get(1).getCoord())));
		assertTrue(allEntities.get(2).getEntity().equals(new UnlitBombUsableEntity(allEntities.get(2).getCoord())));
		assertTrue(allEntities.get(3).getEntity().equals(new SwordUsableEntity(allEntities.get(3).getCoord())));
		assertTrue(allEntities.get(4).getEntity().equals(new SwordUsableEntity(allEntities.get(4).getCoord())));
		assertTrue(allEntities.get(5).getEntity().equals(new BoulderMobileEntity(allEntities.get(5).getCoord())));
		assertTrue(allEntities.get(6).getEntity().equals(new TreasureEntity(allEntities.get(6).getCoord())));
		assertTrue(allEntities.get(7).getEntity().equals(new TreasureEntity(allEntities.get(7).getCoord())));
		assertTrue(allEntities.get(8).getEntity().equals(new TreasureEntity(allEntities.get(8).getCoord())));
		assertTrue(allEntities.get(9).getEntity().equals(new HoverPotion(allEntities.get(9).getCoord())));
		assertTrue(allEntities.get(10).getEntity().equals(new InvincibilityEntity(allEntities.get(10).getCoord())));
		assertTrue(allEntities.get(11).getEntity().equals(new EnemyMobileEntity(allEntities.get(11).getCoord())));
		assertTrue(allEntities.get(12).getEntity().equals(new KeyUsableEntity(allEntities.get(12).getCoord())));
		assertTrue(allEntities.get(13).getEntity().equals(new KeyUsableEntity(allEntities.get(13).getCoord())));
		assertTrue(allEntities.get(14).getEntity().equals(new ArrowUsableEntity(allEntities.get(14).getCoord())));
		System.out.println(" Testing Map completed ");
	}

}
class entities {
	private Entity entity;
	private Coord coord;
	public entities(Entity entity , Coord c) {
		this.entity = entity;
		this.coord = c; 
	}
	public Entity getEntity() {
		return entity; 
	}
	public Coord getCoord() {
		return coord; 
	}
}