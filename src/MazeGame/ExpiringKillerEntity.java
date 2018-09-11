package MazeGame;

public class ExpiringKillerEntity extends Entity {

	@Override
	public void collide(MobileEntity hitter) {
		hitter.die();
	}
	
	@Override
	public void tick() {
		this.removeFromTile();
	}

}
