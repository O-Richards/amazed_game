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
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numRows; j++) {
				maze[i][j] = new Tile(i, j);
			}
		}
	}
	public boolean placeEntity(int row, int col, Entity entity) {return true;}
	public void movePlayer(int direction) {}
	//Perform one game tick
	public void tick() {}
	
	
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
