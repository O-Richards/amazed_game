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
		//Adds a border of wall tiles to the map.
		this.map = new Tile[nRows + 2][nCols + 2];
		for (int row = 1; row < nRows + 1; row++) {
			for (int col = 1; col < nCols + 1; col++) {
				this.map[row][col] = new Tile(new Coord(row, col));
			}
		}
		//Add bordering walls
		for (int row = 0; row < nRows + 2; row++) {
			this.map[row][0] = new WallTile(new Coord(row, 0));
			this.map[row][nRows + 1] = new WallTile(new Coord(row, nRows + 1));
		}
		for (int col = 0; col < nCols + 2; col++) {
			this.map[0][col] = new WallTile(new Coord(0, col));
			this.map[nRows + 1][col] = new WallTile(new Coord(nRows + 1, col));
		}
		this.player = new PlayerMobileEntity(this.map[1][1]);
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

	public PlayerMobileEntity getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
	private Tile getTile(Coord c) {
		return this.map[c.getX()][c.getY()];
	}
	
}
