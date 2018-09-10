package MazeGame;

public class WallTile extends Tile {

	public WallTile(Coord coord) {
		super(coord);
	}
	
	@Override
	public void collide(MobileEntity hitter) {
		hitter.navigateObstacle();
	}

}
