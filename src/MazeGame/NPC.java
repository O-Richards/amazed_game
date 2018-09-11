package MazeGame;

public class NPC extends MobileEntity {
	private Movement movement;

	@Override
	public void collide(MobileEntity hitter) {
		hitter.die();
	}

	@Override
	public boolean pickupUsable(Usable item) {
		//cannot pickup items
		return false;
	}

	@Override
	public boolean navigateObstacle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean die() {
		this.removeFromTile();
		return true;
	}
}
