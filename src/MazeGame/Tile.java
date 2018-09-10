package MazeGame;

import java.util.List;

//This parent class is an empty tile
public class Tile extends Entity{
	//List of entites/items on the tile
	private List<Entity> entities;
	private Coord coord;
	
	public Tile(Coord coord) {
		this.coord = coord;
	}
	
	public void addEntity(Entity entity);
	public void removeEntity(Entity entity);

}
