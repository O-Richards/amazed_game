package GameMain;

import java.util.List;

public class PlayerMobileEntity extends MobileEntity {
	private List<Usable> inventory;
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
		return Collision.MOVE;
	}

	@Override
	public Coord nextCoord() {
		return this.getCoord(this.getDirection());
	}
	
	@Override
	public boolean pushEntity() {
		return true;
	}
	
	public String getSprite() {
		return "P";
	}
}
