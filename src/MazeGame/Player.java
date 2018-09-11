package MazeGame;

import java.util.List;

public class Player extends MobileEntity {
	private List<Usable> inventory;

	@Override
	public void collide(MobileEntity hitter) {
		//Colliding with the player does nothing
	}

	@Override
	public boolean pickupUsable(Usable item) {
		//TODO: Check if you are allowed to add this item
		this.inventory.add(item);
		return true;
	}

	@Override
	public boolean navigateObstacle() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
		//return false;
	}

	@Override
	public boolean die() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
		//return false;
	}
}
