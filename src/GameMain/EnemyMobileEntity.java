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
		Coord nextCoord = this.nextCoord();
		if (DEBUG) System.out.println("Moving sprite " + this.getSprite() + " to " + nextCoord);
		this.entityMover.moveEntity(this, nextCoord);
	}
}
