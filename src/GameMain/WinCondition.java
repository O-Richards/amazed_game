package GameMain;

public interface WinCondition {
	/**
	 * Returns true if the player has won, False otherwise
	 * @return
	 */
	public boolean hasWon();
	/**
	 * 
	 */
	public void setUnsatisfied();

	public void setSatisfied();
	/**
	 * 
	 * @param tickNum
	 */
	public void tick(Integer tickNum);
}
