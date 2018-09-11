/**
 * 
 */
package MazeGame;

/**
 * @author z5157383
 *
 */
public abstract class Entity implements Collidable {
	private Tile tile;
	
	Entity() {
		this.tile = null;
	}
	
	Entity(Tile tile) {
		this.tile = tile;
	}
	
	//By default, entities don't do anything
	public void tick() {
		
	}
	
	public boolean canTriggerSwitch() {
		return false;
	}
	
	public boolean removeFromTile() {
		if (this.tile == null) {
			return false;
		}
		this.tile.removeEntity(this);
		this.tile = null;
		return false;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	@Override
	public String toString() {
		return "Entity [row=" + row + ", col=" + col + "]";
	}
	
}
