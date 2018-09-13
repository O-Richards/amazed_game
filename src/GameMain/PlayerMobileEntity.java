package GameMain;

public class PlayerMobileEntity extends MobileEntity {

	// private PlayerState playerState;
	private Integer treasure;
	private boolean hover = false;
	
	PlayerMobileEntity(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canFly() {
		return hover;
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
