package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {

	PlayerMobileEntity(Tile tile, Movement movement) {
		super(tile, movement);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Usable> inventory;
	// private PlayerState playerState;
	private Integer treasure;
	private boolean hover = false;
	
	public boolean useItem(Usable u) {
		if (inventory.contains(u)) {
			u.use();
			return true;
		}
		return false;
	}

	@Override
	public boolean canFly() {
		return hover;
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
