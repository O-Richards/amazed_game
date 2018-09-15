package GameMain;

public abstract class Entity implements Collidable {

	//Coord the entity is on. Note if in inventory, this will be null
	private Coord coord;
	protected EntityMover entityMover;
	
	Entity(Coord coord) {
		this.coord = coord;
	}
	
	public Coord getCoord() {
		//TODO: Should really check if the tile is null in some nice way
		//For now lets leave it raising an exception.
		return this.coord;
	}
	
	/**
	 * @param dir the direction from the entity we want a Coord of.
	 * @return the Coord of the tile in the direction dir from the entity
	 */
	public Coord getCoord(Direction dir) {
		return this.getCoord().add(dir);
	}
	
	public void tick(Integer tickNum) {
	
	}

	public void removeFromTile() {
		this.entityMover.removeEntity(this, this.getCoord());
	}

	public String getSprite() {
		return "E";
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		
		Entity e = (Entity)obj;
		if (e.getCoord().equals(this.getCoord())) return true;
		return false;
		
	}

	public void setEntityMover(EntityMover entityMover) {
		this.entityMover = entityMover;
	}

}
