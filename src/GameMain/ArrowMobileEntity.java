package GameMain;

public class ArrowMobileEntity extends MobileEntity {
	
	public ArrowMobileEntity(Coord coord, Direction dir) {
		super(coord);
		this.setDirection(dir);
	}

	@Override
	public Collision collide(MobileEntity hitter, boolean recall) {
		hitter.killEnemy();
		this.removeFromTile();
		return Collision.MOVE;
	}

	@Override
	public boolean pickup(UsableEntity item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean killPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean killEnemy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSprite() {
		// TODO Auto-generated method stub
		return ">";
	}

}
