package GameMain;

import java.util.List;

public class PlayerMobileEntity extends MobileEntity {
	private List<Usable> inventory;
	// private PlayerState playerState;
	private Integer treasure;
	private boolean hover = false;
	
	PlayerMobileEntity(Tile tile) {
		super(tile, null);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
	}
	
	PlayerMobileEntity(Tile tile, Movement movement) {
		super(tile, movement);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.MOVE;
	}

	@Override
	public Coord nextCoord() {
		return this.getCoord(this.getDirection());
	}
	
	public String getSprite() {
		return "P";
	}
}
