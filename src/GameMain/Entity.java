package GameMain;

public abstract class Entity implements Collidable {

	//Tile it is on. Note, if it is in the players inventory, will be null.
	private Tile tile;
	
	Entity(Tile tile) {
		this.tile = tile;
	}
	
	public Coord getCoord() {
		//TODO: Should really check if the tile is null in some nice way
		//For now lets leave it raising an exception.
		return this.tile.getCoord();
	}
	
	public void use() {
		
	}
	
	public void update() {
	
	}

	public void removeFromTile() {
		if (this.tile != null) {
			this.tile.removeEntity(this);
		}
		this.tile = null;
	}
}
