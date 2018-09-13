package GameMain;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile implements Collidable {
	private Coord coord;
	private ArrayList<Entity> entities;
	
	public Tile(Coord coord) {
		this.coord = coord;
		this.entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		for (Entity e : this.entities) {
			e.tick();
		}
	}
	
	public boolean containsEntity(Entity e) {
		return entities.contains(e);
	}
	
	/**
	 * @param entity The entity to add to this tile
	 * @return True if successfully added
	 */
	public boolean addEntity(Entity entity) {
		this.entities.add(entity);
		entity.setTile(this);
		//If this were any other tile e.g. WallTile, you would not add the entity, then return false
		return true;
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		Collision col = Collision.MOVE;
		for (Entity e : this.entities) {
			Collision tmpCol = e.collide(hitter);
			if (tmpCol != Collision.MOVE) {
				col = tmpCol;
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

	
}
