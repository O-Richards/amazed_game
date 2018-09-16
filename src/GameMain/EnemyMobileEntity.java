package GameMain;

public class EnemyMobileEntity extends MobileEntity {

	EnemyMobileEntity(Coord coord) {
		super(coord);
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

}
