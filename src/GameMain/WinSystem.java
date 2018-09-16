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
	public WinType getType() {
		boolean win = true;
		// for an enabled win type
		for (WinType type : this.winEnabled) {
			win = true;
			// check to see if it does not exist (not in win state) amongst conditions
			for (WinCondition cond : this.winConditions) {
				// if it exists then no win
				if (type == cond.getType()) {
					// System.out.println(type);
					win = false;
					break;
				}
			}
			// if an enabled win type is not in win state amongst all win conditions, the game is won
			if (win) return WinType.WIN;
		}
		return WinType.FALSE;
	}

	@Override
	public void setType(WinType winType) {
		
	}
	
	/**
	 * @return A new win condition that must be satisfied for this win condition to be satisfied
	 * This can then be given to a switch tile etc. to notify the WinSystem if it is satisfied
	 */
	public WinCondition newWinCondition(WinType type) {
		WinCondition newCond = new SingleWinCondition(type);
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
