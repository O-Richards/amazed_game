package GameMain;

public class WinCondition {

	private boolean won;
	
	WinCondition() {
		won = false;
	}

	public boolean hasWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
	
}
