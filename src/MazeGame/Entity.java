/**
 * 
 */
package MazeGame;

/**
 * @author z5157383
 *
 */
public abstract class Entity implements Collidable {
	

	@Override
	public String toString() {
		return "Entity [row=" + row + ", col=" + col + "]";
	}
	
}
