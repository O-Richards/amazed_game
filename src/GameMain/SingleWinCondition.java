package GameMain;

public class SingleWinCondition implements WinCondition {
	private WinType win;
	
	SingleWinCondition() {
		win = WinType.FALSE;
	}

	public WinType hasWon() {
		return win;
	}

	public void setWin(WinType win) {
		this.win = win;
	}
	
}
