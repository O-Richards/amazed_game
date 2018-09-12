package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<Usable> inventory;
	// private PlayerState playerState;
	private Integer treasure;
	
	public PlayerMobileEntity(Tile tile) {
		super(tile);
	}
	
	public boolean useItem(Usable u) {
		if (inventory.contains(u)) {
			u.use();
			return true;
		}
		return false;
	}



	@Override
	public boolean collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
