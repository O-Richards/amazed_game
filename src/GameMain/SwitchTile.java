package GameMain;

public class SwitchTile extends Tile {
	private WinCondition winCondition;
	
	public SwitchTile(Coord coord, WinCondition winCondition) {
		super(coord);
		this.winCondition = winCondition;
	}
	
	@Override
	public void tick(int tickNum) {
		super.tick(tickNum);
		if (!this.containsEntity(new BoulderMobileEntity(this.getCoord()))) {
			winCondition.setUnsatisfied();
		} else {
			winCondition.setSatisfied();
		}
	}
	

	
	/**
	 * @param entity The entity to add to this tile
	 * @return True if a new entity can be placed here. False else e.g. placing an item on a wall
	 */
	@Override
	public boolean addEntity(Entity entity) {
		super.addEntity(entity);
		//Cannot have items on switches
		return false;
	}
	
	@Override
	public String getSprite() {
		return "@";
	}

}
