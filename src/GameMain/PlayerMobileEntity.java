package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	// private PlayerState playerState;
	private boolean hover = false;

	
	PlayerMobileEntity(Tile tile) {
		super(tile);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
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
	
	
	PlayerMobileEntity(Tile tile, Movement movement) {
		super(tile, movement);
	}
	
	public int noTreasure() {
		int treasure = 0;
		for (UsableEntity u: inventory) {
			if (u instanceof TreasureEntity) {
				treasure++;
			}
		}
		return treasure;
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
