package gameModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Tile implements Collidable {
	private static final boolean DEBUG = false;
	private Coord coord;
	private WinCondition enemyCondition;
	private UsableEntity item = null;
	private MobileEntity mobileEntity = null;
	
	public Tile(Coord coord, WinCondition enemyCondition) {
		this.coord = coord;
		this.enemyCondition = enemyCondition;
	}
	
	public void tick(int tickNum) {
		List<Entity> listCopy = new ArrayList<>(this.entities);
		for (Entity e : listCopy) {
			e.tick(tickNum);
		}
	}
	/**
	 * Adds a usable item to the tile: 
	 * @param item
	 * @throws EntityPlacementException
	 */
	public void addUsableEntity(UsableEntity item) throws EntityPlacementException {
		if (this.item != null) {
			throw new EntityPlacementException("Occupied tile");
		}
		this.item = item; 
	}
	
	public void addMoveableEntity(MobileEntity entity) throws EntityPlacementException {
		if(this.mobileEntity != null) {
			throw new EntityPlacementException("Mobile Entity on tile");
		}
		this.mobileEntity = entity; 
	}
	
	public MobileEntity getMoveableEntity() {
		return mobileEntity;
		
	}
	public Entity getItem() {
		
	}
	/**
	 * @param entity the entity on an adjacent tile to be moved onto this tile
	 */
	public void moveMobileEntityOnto(MobileEntity entity) {
		entity.collide(this, recall)
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
		/*for (Entity e : entities) {
			//Prevent self collisions
			if (e != hitter) {
				Collision tmpCol = e.collide(hitter, recall);
				if (tmpCol != Collision.MOVE) {
					col = tmpCol;
				}
			}
		}*/
		//If two mobile entities hit: 
		if(this.mobileEntity != hitter) {
			Collision tmpCol = mobileEntity.collide(hitter, recall);
			if (tmpCol != Collision.MOVE) {
				col = tmpCol;
			}
		}
		//If a mobile entity hits an item: 
		//if a player collides with a usable entity 
		
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
	

	
}

class EntityPlacementException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8634948987228608288L;

	public EntityPlacementException(String msg) {
		super(msg);
	}
}
