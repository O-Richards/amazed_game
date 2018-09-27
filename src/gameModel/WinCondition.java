package gameModel;

/**
 * Applying composite pattern to win conditions
 */
public interface WinCondition {
	/**
	 * @return True if the win condition has been satisfied. false else
	 */
	public WinType getType();
	
	/**
	 * @param satisfied sets the win condition as satisfied by winType
	 */
	public void setType(WinType winType);
}
