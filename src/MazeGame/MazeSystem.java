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
	private Tile[][] maze;
	
	public void newMaze(int numRows, int numCols) {
		maze = new Tile[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numRows; j++) {
				Coord coord = new Coord(i, j);
				maze[i][j] = new Tile(coord);
			}
		}
	}
	public boolean placeEntity(Coord coord, Entity entity) {
		throw new UnsupportedOperationException("Not yet implemented");
		//return true;
		}
	public void movePlayer(int direction) {
		throw new UnsupportedOperationException("Not yet implemented");

	}
	//Perform one game tick
	public void tick() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	
	private void showMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
		System.out.print("\n");
		}
	}
	
	public static void main(String [] args) {
		MazeSystem game = new MazeSystem();
		game.newMaze(5, 5);
		game.showMaze();
		
	}
	
}
