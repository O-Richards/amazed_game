package GameMain;

public abstract class Entity implements Collidable {

	private char sprite;
	//Tile it is on. Note, if it is in the players inventory, will be null.
	private Tile tile;
	
	Entity(Tile tile) {
		this.tile = tile;
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
