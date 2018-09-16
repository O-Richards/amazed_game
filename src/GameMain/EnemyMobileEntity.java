package GameMain;

public class EnemyMobileEntity extends MobileEntity {

	private Level level;
	EnemyMobileEntity(Coord coord) {
		super(coord);
	}
	EnemyMobileEntity(Coord coord, Level level) {
		super(coord);
		this.level = level;

	}
	@Override
	public Collision collide(MobileEntity hitter, boolean recall) {
		hitter.killPlayer();
		if (recall) hitter.collide(this, false);
		return Collision.MOVE;
	}

	@Override
	public boolean pickup(UsableEntity item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean killPlayer() {
		return false;
	}

	@Override
	public boolean killEnemy() {
		this.entityMover.removeEntity(this, this.getCoord());
		return true;
	}

	@Override
	public String getSprite() {
		return "E";
	}
	
	@Override
	public void move() {
		Coord goal = this.level.getPlayer().getCoord();
		AStarSearch astar = new AStarSearch(this.level.getMap(),this,goal);
		//hmm? pop first coord from list?
		Coord nextCoord = astar.findPath().get(0);
		this.entityMover.moveEntity(this, nextCoord);
	}
}
