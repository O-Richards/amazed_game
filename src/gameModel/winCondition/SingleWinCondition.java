package gameModel.winCondition;

public class SingleWinCondition implements WinCondition {
	private WinType type;
	
	SingleWinCondition(WinType type) {
		this.type = type;
	}

	public WinType getType() {
		return type;
	}

	public void setType(WinType type) {
		this.type = type;
	}
	
}
