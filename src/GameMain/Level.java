package GameMain;

public class Level {
	//Some constants
	private static final int DEFAULT_NROWS = 30;
	private static final int DEFAULT_NCOLS = 30;
	
	private Tile[][] map;
	private WinCondition winCondition;
	private PlayerMobileEntity player;
	
	public Level() {
		this(DEFAULT_NROWS, DEFAULT_NCOLS);
	}
	
	public Level(int nRows, int nCols) {
		this.map = new Tile[nRows][nCols];
		for (int row = 0; row < nRows; row++) {
			for (int col = 0; col < nCols; col++) {
				this.map[row][col] = new Tile(new Coord(row, col));
			}
		}
		this.player = new PlayerMobileEntity(this.map[0][0]);
	}
	
	public void moveMobileEntity(MobileEntity entity, Coord c) {
		Tile newTile = this.map[c.getX()][c.getY()];
		//Trigger any/all collisions
		if (newTile.collide(entity) == Collision.MOVE) {
			entity.removeFromTile();
			newTile.addEntity(entity);
		}
	}
	
	/**
	 * @param e The entity to be added
	 * @param c The coord to add the entity to
	 * @return True if successful, false otherwise (e.g. tried to add an item on a wall)
	 */
	public boolean addEntity(Entity e, Coord c) {
		Tile placementTile = getTile(c);
		return placementTile.addEntity(e);
	}
	
	public void tick() {
		
	}

	public Coord getPlayerCoord() {
		// TODO Auto-generated method stub
		return player.getCoord();
	}
	
	private Tile getTile(Coord c) {
		return this.map[c.getX()][c.getY()];
	}

	public PlayerMobileEntity getPlayer() {
		return this.player;
	}
	
}
