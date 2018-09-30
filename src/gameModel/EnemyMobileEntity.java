package gameModel;

public class EnemyMobileEntity extends MobileEntity {

	private Level level;
	public EnemyMobileEntity(Coord coord) {
		super(coord);
	}
	EnemyMobileEntity(Coord coord, Level level) {
		super(coord);
		this.level = level;

	}

	@Override
	public boolean pickup(UsableEntity item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSprite() {
		return "E";
	}
	
	/* Left here for reference, should be replaced by this.nextCoord()
	@Override
	public void move() {
		Coord goal = this.level.getPlayer().getCoord();
		AStarSearch astar = new AStarSearch(this.level.getMap(),this,goal);
		//hmm? pop first coord from list?
		Coord nextCoord = astar.findPath().get(0);
		this.entityMover.moveEntity(this, nextCoord);
	}
	*/
}
