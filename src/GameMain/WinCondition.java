package GameMain;

public interface WinCondition {

	public boolean hasWon();

	public void setUnsatisfied();

	public void setSatisfied();
	
	public void tick();
}
