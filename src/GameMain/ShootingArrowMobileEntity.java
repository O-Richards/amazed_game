package GameMain;

public class ShootingArrowMobileEntity extends MobileEntity {

	ShootingArrowMobileEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter, boolean recall) {
		hitter.killEnemy();
		this.entityMover.removeEntity(this, this.getCoord());
		if (recall) hitter.collide(this, false);
		return Collision.MOVE;
	}

	@Override
	public boolean pickup(UsableEntity item) {
		return false;
	}
	
	@Override
	public boolean killPlayer() {
		return false;
	}

	@Override
	public boolean killEnemy() {
		return false;
	}


	@Override
	public String getSprite() {
		return "*";
	}


}
