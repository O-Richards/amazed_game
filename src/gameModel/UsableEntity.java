package gameModel;

public abstract class UsableEntity extends Entity {
	protected WinCondition winCondition;
	
	UsableEntity(Coord coord) {
		super(coord);
	}
	
	/**
	 * @param direction Direction the item is to be used in
	 * @return True if the item has uses left, false else.
	 */
	public Boolean use(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Called when an item is picked up by the player. Allows effects to be applied to the player
	 * @param player
	 */
	public void applyToPlayer(PlayerMobileEntity player) {
		//By default does nothing
	}
	
	public void setWinCondition(WinCondition winCondition) {
		this.winCondition = winCondition;
	}
}
