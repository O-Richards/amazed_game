package GameMain;

public class SwitchWinCondition implements WinCondition {
	private boolean switchesFilled;
	
	public SwitchWinCondition() {
		this.switchesFilled = false;
	}
	
	@Override
	public boolean hasWon() {
		return this.switchesFilled;
	}

	@Override
	public void setUnsatisfied() {
		this.switchesFilled = false;
		
	}

	@Override
	public void setSatisfied() {
		//For switches it does nothing
	}

	@Override
	public void tick(Integer tickNum) {
		//Assume all switches are filled at the start of the tick, then see if anyone triggers it
		this.switchesFilled = true;
	}

}
