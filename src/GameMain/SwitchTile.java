package GameMain;

public class SwitchTile extends Tile {
	private WinCondition winCondition;
	
	public SwitchTile(Coord coord, WinCondition winCondition) {
		super(coord);
		this.winCondition = winCondition;
	}
	
	/**
	 * @param entity The entity to add to this tile
	 * @return True if a new entity can be placed here. False else e.g. placing an item on a wall
	 */
	@Override
	public boolean addEntity(Entity entity) {
		super.addEntity(entity);
		if (this.containsEntity(new BoulderMobileEntity(this.getCoord()))) {
			this.winCondition.setWon(true);
		}
		//Cannot have items on switches
		return false;
	}
	
	@Override
	public void removeEntity(Entity entity) {
		if (entity.equals(new BoulderMobileEntity(this.getCoord()))) {
			this.winCondition.setWon(false);
		}
		super.removeEntity(entity);
	}
	
	@Override
	public String getSprite() {
		return "@";
	}

}
