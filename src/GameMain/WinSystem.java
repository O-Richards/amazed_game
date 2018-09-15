package GameMain;

import java.util.ArrayList;
import java.util.List;

public class WinSystem {

	private List<WinCondition> winConditions;
	// enable win condition
	private boolean enableSwitchWinCondition;
	private boolean enableTreasureWinCondition;
	private boolean enableExitsWinCondition;
	private boolean enableEnemiesWinCondition;
	
	WinSystem() {
		this.winConditions = new ArrayList<WinCondition>();
	}
	
	
}
