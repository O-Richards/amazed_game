/**
 * 
 */
package MazeGame;

/**
 * @author z5157383
 *
 */
public abstract class Entity{
	private Obstacle obstacle;
	private int row;
	private int col;
	
	public Entity(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Entity [row=" + row + ", col=" + col + "]";
	}
	
}
