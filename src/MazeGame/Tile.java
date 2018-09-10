package MazeGame;

import java.util.List;

//This parent class is an empty tile
public class Tile implements Collidable {
	//List of entites/items on the tile
	private List<Entity> entities;
	private Coord coord;
	
	public Tile(Coord coord) {
		this.coord = coord;
	}
	
	public void addEntity(Entity entity);
	public void removeEntity(Entity entity);

	@Override
	public void collide(MobileEntity hitter) {
		for (Entity item : entities) {
			item.collide(hitter);
		}
	}

}
