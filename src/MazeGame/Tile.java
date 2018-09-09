package MazeGame;

import java.util.List;

//This parent class is an empty tile
public class Tile extends Entity{
	public Tile(int row, int col) {
		super(row, col);
	}

	//List of entites/items on the tile
	private List<Entity> entities;
}
