package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.mobileEntity.MobileEntity;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;


/**
 * @author Oli
 *
 */
/**
 * @author Oli
 *
 */
public class Tile {//extends Observable{
	private final boolean DEBUG = true;
	
	private ArrayList<Observer> jfxPanes;
	private Coord coord;
	private Entity item = null;
	private MobileEntity mobile = null;
	private EntityMover entityMover;	

	public Tile(Coord coord, EntityMover entityMover) {
		this.coord = coord;
		this.entityMover = entityMover;
		this.jfxPanes = new ArrayList<Observer>();
	}

	public void tick(int tickNum) {
		if (item != null) item.tick(tickNum);
		if (mobile != null) mobile.tick(tickNum);
		//move the enemy
		if (mobile != null && mobile.lastMoveTickNum() != tickNum) {
			Coord nextCoord = mobile.nextCoord();
			if (!nextCoord.equals(this.getCoord())) {
				mobile.setLastMoveTickNum(tickNum);
				entityMover.moveMobileEntity(mobile, nextCoord);
			}
		}
	// do we notify observers here? <<== TODO
//		notifyObservers();
	}
	/**
	 * Adds a usable item to the tile: 
	 * @param item
	 * @throws EntityPlacementException
	 */
	public void addItem(Entity item) throws EntityPlacementException {
		if (this.item != null) {
			throw new EntityPlacementException("Item on tile");
		}
		this.item = item;
		// Notify observer JFXPanel to update image <<== TODO
//		notifyObservers();
	}
	
	/**
	 * Clear the tile
	 */
	public void clear() {
		this.item = null;
		this.mobile = null;
	}

	/**
	 * Add an enemy to a tile
	 * @param enemy
	 * @throws EntityPlacementException Thrown if there is an error in placing the enemy e.g. walking onto a closed door.
	 */
	public void addMobileEntity(MobileEntity newEntity) throws EntityPlacementException {
		MobileEntity oldEntity = this.mobile;
		boolean placeNewEntity = false;
		boolean killedNew = false;
		if (oldEntity == null) {
			placeNewEntity = true;
		} else {
			if (oldEntity.pushable() && newEntity.canPush()) {
				//if pushable, push it
				if (this.entityMover.moveMobileEntity(oldEntity, newEntity.getDirection())) {
					placeNewEntity = true;
				}
			}
			if (oldEntity.kill(newEntity.getKillAction())) {
				//New entity tries to kill old entity
				placeNewEntity = true;
				this.mobile = null;
			} 
			if (newEntity.kill(oldEntity.getKillAction())) {
				//try to kill the new entity (by old)
				placeNewEntity = false;
				killedNew = true;
			}
		}
		
		if (placeNewEntity) {
			newEntity.setCoord(this.getCoord());
			this.mobile = newEntity;
			if (this.item != null) {
				if (newEntity.pickup(this.item.getUsable())) {
					this.item = null;
				}
			}
		} else if (!killedNew) {
			throw new EntityPlacementException("Tile is occupied");
		}
		// UPDATE OBSERVERS NOW PLOX <<== TODO
//		notifyObservers();
	}
	
	/**
	 * Observer method stuff
	 * addObserver adds observer to the list of objects observing the invoking object
	 */
//	@Override
	public void addObserver(Observer o) {
		jfxPanes.add(o);
	}
	
	/**
	 * removeObserver method will remove an observer from a Tiles ArrayList of observers
	 * @param o = observer to remove
	 */
	public void removeObserver(Observer o) {
		jfxPanes.remove(o);
	}
	
//	@Override
	public void notifyObservers() {
		/*
		for (Observer observer : jfxPanes) {
			observer.update(null, this.getVisType());
		}
		*/
		System.out.print("VisType of coord".concat(coord.toString()));
		System.out.println(this.getVisType().toString());
	}

	public void removeItem() {
		this.item = null;
	}

	public MobileEntity getMobile() {
		return this.mobile;

	}
	public Entity getItem() {
		return this.item;
	}

	//Some template method shit happening right here
	public void updateWinCondition() {
	}

	public Coord getCoord() {
		return this.coord;
	}

	/**
	 * @return A simple char to represent the tile (for debugging)
	 */
	public VisType getVisType() {
		if (this.mobile != null) {
			return this.mobile.getVisType();
		} else if (this.item != null) {
			return this.item.getVisType();
		} else {
			return VisType.EMPTY_TILE;
		}
	}

	public boolean traversable() {
		return this.mobile == null;
	}

	public boolean kill(KillAction action) {
		boolean retVal = false;
		if (this.mobile != null) {
			boolean enemyKilled = mobile.kill(action);
			if (enemyKilled) this.mobile = null;
			retVal |= enemyKilled;
		}
		return retVal;
	}

	/**
	 * @param e The entity to be removed from this tile
	 */
	public void removeMobileEntity(MobileEntity e) {
		if (e != this.mobile) {
			if (DEBUG) {
				System.out.println("Tile.removeEnemy: Warning: trying to remove entity not on this tile");
			}
		} else {
			this.mobile = null;
		}
	}
}
