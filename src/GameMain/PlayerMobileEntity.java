package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {

	private ArrayList<Usable> inventory;
	// private PlayerState playerState;
	private Integer treasure;
	
	public boolean useItem(Usable u) {
		if (inventory.contains(u)) {
			u.use();
			return true;
		}
		return false;
	}

	
}
