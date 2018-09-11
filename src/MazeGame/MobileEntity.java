package MazeGame;

public abstract class MobileEntity extends Entity{
	
	public boolean moveTo(Coord coord);
	public Tile getTile() {
		return tile;
	}
	//Methods needed for collisions
	public abstract boolean pickupUsable(Usable item);
	public abstract boolean navigateObstacle();
	public abstract boolean die();
	

}
