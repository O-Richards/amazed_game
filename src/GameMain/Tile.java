package GameMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Tile implements Collidable {
	private static final boolean DEBUG = false;
	private Coord coord;
	private ArrayList<Entity> entities;
	
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
		this.entities.add(entity);
		entity.setCoord(this.getCoord());
		//If this were any other tile e.g. WallTile, you would not add the entity, then return false
		return true;
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
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
		if (DEBUG) System.out.println("Checking collisions on Tile: " + this.getCoord());
		Collision col = Collision.MOVE;
		List<Entity> entitiesCopy = new ArrayList<>(this.entities);
		for (Entity e : entitiesCopy) {
			//Prevent self collisions
			if (e != hitter) {
				if (DEBUG) System.out.println("Checking collisions with " + e.getSprite() + " by " + hitter.getSprite());
				Collision tmpCol = e.collide(hitter);
				if (tmpCol != Collision.MOVE) {
					col = tmpCol;
				}
			}
		}
		return col;
	}

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

	/**
	 * Remove all enemy entities from the tile
	 * Use this for bombs, swords etc.
	 * @Return true if an item is removed from tile false otherwise: 
	 */
	public boolean killEnemyEntities() {
		Iterator<Entity> it = this.entities.iterator();
		System.out.println(getCoord()+ "Current Coordinates");
		while (it.hasNext()) {
			Entity e = it.next();
			//Removes the enemy: 
			if (e.equals(new EnemyMobileEntity(this.getCoord()))) {
				it.remove();
				return true; 
			}

		}
		return false;
	}
	
	
	
}
