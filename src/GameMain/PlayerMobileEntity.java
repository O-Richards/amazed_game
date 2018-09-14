package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	// private PlayerState playerState;
	// private Integer treasure;
	private boolean hover = false;
	
	PlayerMobileEntity(Tile tile) {
		super(tile);
		this.inventory = new ArrayList<UsableEntity>();
		// TODO Auto-generated constructor stub
	}
	
	public boolean useItem(UsableEntity item, Tile[][] adjTiles) {
		if (inventory.contains(item)) {
			item.use(this.getDirection(), adjTiles);
			inventory.remove(item);
			return true;
		}
		return false;
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

	@Override
	public boolean pickup(UsableEntity item) {
		inventory.add(item);
		return true;
	}
	
	public String inventoryString() {
		String out = "";
		for (UsableEntity e : inventory) {
			out += e.getSprite();
		}
		return out;
	}
}
