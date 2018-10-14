package gameModel.usable;

import gameModel.mobileEntity.PlayerMobileEntity;

public interface Usable {

	/**
	 * @param action The action to perform
	 * @return true if used, false else
	 */
	public boolean use(UseAction action);
	
	public void applyToPlayer(PlayerMobileEntity player);

	public UseAction getUseAction();
	
	/**
	 * @param u the item already in the inventory
	 * @return true if this item can also be picked up, false else
	 */
	public boolean canBePickedUpWith(Usable u);
}
