package MazeGame;

import java.util.List;

//This parent class is an empty tile
public class Tile implements Collidable {
	//List of entities/items on the tile
	private List<Entity> entities;
	private Coord coord;
	
	public Tile(Coord coord) {
		this.coord = coord;
	}
	
	public boolean addEntity(Entity entity) {
		//TODO: Check if we can add the entity
		this.entities.add(entity);
		entity.setTile(this);
		return true;
	}
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public void tick() {
		for (Entity item : entities) {
			item.tick();
		}
	}

	@Override
	public void collide(MobileEntity hitter) {
		for (Entity item : entities) {
			item.collide(hitter);
		}
	}

}
