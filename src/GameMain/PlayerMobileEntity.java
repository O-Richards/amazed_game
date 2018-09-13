package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private Coord nextCoord;
	PlayerMobileEntity(Tile tile) {
		super(tile);
		this.nextCoord = tile.getCoord();
	}

	private ArrayList<Usable> inventory;
	// private PlayerState playerState;
	private Integer treasure;
	private boolean hover = false;
	
	public boolean useItem(Usable u) {
		if (inventory.contains(u)) {
			u.use();
			return true;
		}
		return false;
	}

	public void setNextCoord(Coord coord) {
		this.nextCoord = coord;
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
		return this.nextCoord;
	}
	
}
