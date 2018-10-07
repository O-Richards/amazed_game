package gameModel;

public interface Usable {
	/**
	 * @param action The action to perform
	 * @return true if used, false else
	 */
	public boolean use(Action action);
	
	public void applyToPlayer(PlayerMobileEntity player);
}
