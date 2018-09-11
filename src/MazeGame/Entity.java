/**
 * 
 */
package MazeGame;

/**
 * @author z5157383
 *
 */
public abstract class Entity implements Collidable {
	
	public boolean canTriggerSwitch() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Entity [row=" + row + ", col=" + col + "]";
	}
	
}
