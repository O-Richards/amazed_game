package GameMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import com.sun.org.apache.bcel.internal.generic.NEW;

public abstract class Tile implements Collidable {
	private static final boolean DEBUG = false;
	private Coord coord;
	private ArrayList<Entity> entities;
	private WinCondition enemyCondition;
	
	public Tile(Coord coord, WinCondition enemyCondition) {
		this.coord = coord;
		this.enemyCondition = enemyCondition;
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
		updateEnemyCondition();
		return true;
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		updateWinCondition();
		updateEnemyCondition();
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
	public Collision collide(MobileEntity hitter, boolean recall) {			
		Collision col = Collision.MOVE;
		List<Entity> entities = this.getEntities();
		for (Entity e : entities) {
			//Prevent self collisions
			if (e != hitter) {
				Collision tmpCol = e.collide(hitter, recall);
				if (tmpCol != Collision.MOVE) {
					col = tmpCol;
				}
			}
		}
		return collideExt(hitter, col);
	}

	
	/**
	 * Allows to extend the regular collide behavior and is implemented by the relevant subclasses
	 * @param hitter
	 * @param col
	 * @return
	 */
	public Collision collideExt(MobileEntity hitter, Collision col) {
		return col;
	}
	
	protected void updateEnemyCondition() {
		if (this.containsEntity(new EnemyMobileEntity(this.getCoord()))) {
			this.enemyCondition.setType(WinType.ENEMY);
		} else {
			this.enemyCondition.setType(WinType.WIN);
		}
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
	
	private List<Entity> getEntities() {
		return new ArrayList<>(entities);
	}	
	
}
