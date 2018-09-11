package MazeGame;

public class Sword extends Entity implements Usable {

	@Override
	public void collide(MobileEntity hitter) {
		//On collide, pickup the item
		if (hitter.pickupUsable(this)) {
			this.removeFromTile();
		}
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not yet implemented");
		//return 0;
	}
	
	public void useItem(Tile tile) {
		//spawn a killing entity on the tile that expires on the next tick
		//The problem with this approach is we have to make sure movable entites are updated last
		tile.addEntity(new ExpiringKillerEntity());
	}

}
