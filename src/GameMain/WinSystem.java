package GameMain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinSystem implements WinCondition {
	private List<WinCondition> winConditions;
	// enable win condition
	private Set<WinType> winEnabled;
	
	WinSystem() {
		this.winConditions = new ArrayList<WinCondition>();
		this.winEnabled = new HashSet<WinType>();
	}

	@Override
	public WinType hasWon() {
		for (WinCondition cond : this.winConditions) {
			WinType condStatus = cond.hasWon();
			if (condStatus != WinType.FALSE && this.winEnabled.contains(condStatus)) {
				return condStatus;
			}
		}
		return WinType.FALSE;
	}

	@Override
	public void setWin(WinType winType) {
	}
	
	/**
	 * @return A new win condition that must be satisfied for this win condition to be satisfied
	 * This can then be given to a switch tile etc. to notify the WinSystem if it is satisfied
	 */
	public WinCondition newWinCondition() {
		WinCondition newCond = new SingleWinCondition();
		this.addWinCondition(newCond);
		return newCond;
	}
	
	/**
	 * @param winCondition The WinCondition to be added
	 */
	private void addWinCondition(WinCondition winCondition) {
		this.winConditions.add(winCondition);
	}
	
	/**
	 * @param winType The win type to be enabled
	 */
	public void enableWinCondition(WinType winType) {
		this.winEnabled.add(winType);
	}
	
	/**
	 * @param winType The win type to be removed
	 */
	public void disableWinCondition(WinType winType) {
		this.winEnabled.remove(winType);
	}

}
