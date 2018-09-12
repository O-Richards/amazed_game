package GameMain;

public class Level {
	
	private Tile[][] map;
	private WinCondition winCondition;
	private PlayerMobileEntity player;
	
	public Level(int nRows, int nCols, PlayerMobileEntity player) {
		this.player = player;
		this.map = new Tile[nRows][nCols];
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				this.map[row][col] = new Tile(new Coord(row, col));
			}
		}
	}
	
	public void moveMobileEntity(MobileEntity entity, Coord c) {
		Tile newTile = this.map[c.getX()][c.getY()];
		//Trigger any/all collisions
		if (newTile.collide(entity) == Collision.MOVE) {
			entity.removeFromTile();
			newTile.addEntity(entity);
		}
	}
	
	public void addEntity(Coord c) {
		
	}
	
	public void tick() {
		
	}

	public Coord getPlayerCoord() {
		// TODO Auto-generated method stub
		return player.getCoord();
	}
	
}
