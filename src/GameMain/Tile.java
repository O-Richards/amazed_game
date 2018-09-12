package GameMain;

import java.util.ArrayList;

public class Tile implements Collidable {
	private char sprite;
	private Coord coord;
	private ArrayList<Entity> entities;
	
	public Tile(Coord coord) {
		this.coord = coord;
		this.entities = new ArrayList<Entity>();
	}
	
	
	/**
	 * @param entity The entity to add to this tile
	 * @return True if successfully added
	 */
	public boolean addEntity(Entity entity) {
		this.entities.add(entity);
		//If this were any other tile e.g. WallTile, you would not add the entity, then return false
		return true;
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}

	@Override
	public boolean collide(MobileEntity hitter) {
		boolean collision = false;
		for (Entity e : this.entities) {
			collision |= e.collide(hitter);
		}
		return collision;
	}

	public Coord getCoord() {
		return this.coord;
	}
	
}
