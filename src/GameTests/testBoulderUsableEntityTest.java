package GameTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.Test;

import GameMain.BoulderMobileEntity;
import GameMain.Collision;
import GameMain.Coord;
import GameMain.Direction;
import GameMain.Entity;
import GameMain.EntityMover;
import GameMain.MobileEntity;
import GameMain.PlayerMobileEntity;

class testBoulderUsableEntityTest {

	@Test
	void testBoulderNoHit() {
		EntityMover mover = new TestingMover() {
			//For this test we do not need to override any methods
		};
		
		BoulderMobileEntity boulder = BoulderMobileEntity(new Coord(1, 1));
		boulder.setEntityMover(mover);
		boulder.tick(0);
	}
	
	@Test
	void testBoulderHitByPlayer() {
		EntityMover mover = new TestingMover() {
			@Override
			public Collision moveEntity(MobileEntity e, Direction dir) {
				moveEntity(e, e.getCoord(dir));
			}
			
			public Collision moveEntity(MobileEntity e, Coord nextCoord) {
			}
		};
		BoulderMobileEntity boulder = new BoulderMobileEntity(new Coord(2, 1));
		PlayerMobileEntity player = new PlayerMobileEntity(new Coord(3, 1));
		player.setDirection(Direction.DOWN);
		boulder.setEntityMover(mover);
		boulder.collide(player);
		boulder.tick(0);
		verify(mover).moveEntity(boulder, new Coord(1, 1));
	}

}
