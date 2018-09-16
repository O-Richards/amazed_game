package GameMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Tile implements Collidable {
	private static final boolean DEBUG = false;
	private Coord coord;
	private ArrayList<Entity> entities;
	private Collision defaultCol;
	
	public Tile(Coord coord) {
		this.coord = coord;
		this.entities = new ArrayList<Entity>();
	}
	
	public void tick(int tickNum) {
		List<Entity> listCopy = new ArrayList<>(this.entities);
		for (Entity e : listCopy) {
			e.tick(tickNum);
		}
	}
	
	public boolean containsEntity(Entity e) {
		return entities.contains(e);
	}
	
	/**
	 * @param entity The entity to add to this tile
	 * @return True if a new entity can be placed here. False else e.g. placing an item on a wall
	 */
	public boolean addEntity(Entity entity) {
		entities.add(entity);
		entity.setCoord(this.getCoord());
		updateWinCondition();
		return true;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		updateWinCondition();
	}

	/**
	 * Collide the hitter with the Tile. MobileHitters have a bunch of methods
	 * that can be called e.g. canFly(), pickupUsable() etc.
	 * The general idea is that the mobile entities will collide with a tile
	 * when they try to move onto it, which will then cause them to collide with
	 * each entity on the tile. (Think Composition Pattern)
	 * @param hitter The mobile entity that is walking into the collidable object
	 * @return MOVE if the movement is possible, NOMOVE if the movement is blocked
	 */
	
	@Override
	public Collision collide(MobileEntity hitter) {			
		Collision col = Collision.MOVE;
		List<Entity> entities = this.getEntities();
		for (Entity e : entities) {
			//Prevent self collisions
			if (e != hitter) {
				Collision tmpCol = e.collide(hitter);
				if (tmpCol != Collision.MOVE) {
					col = tmpCol;
				}
			}
		}
		if (defaultCol == Collision.NOMOVE) {
			col = defaultCol;
		}
		return collideExt(hitter, col);
	}

	public Collision collideExt(MobileEntity hitter, Collision col) {
		return col;
	}
	
	protected abstract void updateWinCondition();
	
	public Coord getCoord() {
		return this.coord;
	}
	
	public String getSprite() {
		if (this.entities.isEmpty()) {
			return " ";
		} else {
			//TODO: Don't ignore entities after the 1st one
			Iterator<Entity> entityIt = this.entities.iterator();
			return entityIt.next().getSprite();
		}
	}

	private List<Entity> getEntities() {
		return new ArrayList<>(entities);
	}	
	
}
