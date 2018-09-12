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
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
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
	
}
