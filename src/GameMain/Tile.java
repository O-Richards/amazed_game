package GameMain;

import java.util.ArrayList;

public class Tile implements Collidable {
	private char sprite;
	private Coord coord;
	private ArrayList<Entity> entities;
	private Collision tileCollision = Collision.MOVE;;
	
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
	public Collision collide(MobileEntity hitter) {
		Collision col = tileCollision;
		for (Entity e : this.entities) {
			if (e.collide(hitter) == Collision.NOMOVE) {
				col = Collision.NOMOVE;
			}
		}
		return col;
	}

	public Coord getCoord() {
		return this.coord;
	}
	
}
