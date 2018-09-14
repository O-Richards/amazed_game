package GameMain;

public abstract class Entity implements Collidable {

	//Tile it is on. Note, if it is in the players inventory, will be null.
	private Tile tile;
	
	Entity(Tile tile) {
		this.setTile(tile);
	}
	
	public Coord getCoord() {
		//TODO: Should really check if the tile is null in some nice way
		//For now lets leave it raising an exception.
		return this.getTile().getCoord();
	}
	
	/**
	 * @param dir the direction from the entity we want a Coord of.
	 * @return the Coord of the tile in the direction dir from the entity
	 */
	public Coord getCoord(Direction dir) {
		return this.getCoord().add(dir);
	}
	
	public void tick() {
	
	}

	public void removeFromTile() {
		if (this.getTile() != null) {
			this.getTile().removeEntity(this);
		}
		this.setTile(null);
	}

	public String getSprite() {
		return "E";
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}

}
