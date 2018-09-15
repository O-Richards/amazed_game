package GameMain;

public class WinCondition {

	private WinType win;
	
	WinCondition() {
		win = WinType.FALSE;
	}

	public WinType getWin() {
		return win;
	}

	public void setWin(WinType win) {
		this.win = win;
	}
	
}
