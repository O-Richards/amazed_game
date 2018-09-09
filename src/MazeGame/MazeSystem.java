/**
 * 
 */
package MazeGame;

/**
 * @author z5157383
 *
 */
public class MazeSystem {
	private Player player;
	private Entity[][] maze;
	
	public void newMaze(int numRows, int numCols) {
		maze = new Entity[numRows][numCols];
	}
	public boolean placeEntity(int row, int col, Entity entity);
	public void movePlayer(int direction);
	//Perform one game tick
	public void tick();
	
	
	
}
