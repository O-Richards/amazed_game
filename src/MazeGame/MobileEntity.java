package MazeGame;

public abstract class MobileEntity extends Entity{
	private Tile tile;
	
	public boolean moveTo(Coord coord);
	
	//Methods needed for collisions
	public abstract boolean pickupUsable(Usable item);
	public abstract boolean blockMovement(Usable item);
	public abstract boolean die(Usable item);


}
