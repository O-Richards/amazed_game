package GameMain;

public class TreasureWinCondition implements WinCondition {
	
	private boolean allTreasureCollected;
	
	public TreasureWinCondition() {
		this.allTreasureCollected = false;
	}

	@Override
	public boolean hasWon() {
		return this.allTreasureCollected;
	}

	@Override
	public void setUnsatisfied() {
		this.allTreasureCollected = false;
		
	}

	@Override
	public void setSatisfied() {
		this.allTreasureCollected = true;
		
	}

	@Override
	public void tick() {
		this.allTreasureCollected = true;
		
	}

}
