package GameMain;

public class EnemyMobileEntity extends MobileEntity {

	EnemyMobileEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public Collision collide(MobileEntity hitter) {
		hitter.killPlayer();
		// TODO Auto-generated method stub
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

}
