package GameMain;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	// private PlayerState playerState;
	// private Integer treasure;
	
	PlayerMobileEntity(Tile tile) {
		super(tile);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
		this.inventory = new ArrayList<UsableEntity>();
		// TODO Auto-generated constructor stub
	}
	
	//Used for consumables as it removes upon use:
	//No idea how we're able to know the items name
	public boolean useItem(UsableEntity item, Tile[][] adjTiles) {
		if (inventory.contains(item)) {
			item.use(this.getDirection(), adjTiles);
			inventory.remove(item);
			return true;
		}
		return false;
	}
	/*
	//Used for swords:
	public boolean useSword(Tile[][] adjTiles) {
		if (inventory.contains(INSTANCEOF)) {
			item.use(this.getDirection(), adjTiles);
			inventory.remove(item);
			return true;
		}
		return false;
	}*/

	
	
	PlayerMobileEntity(Tile tile, Movement movement) {
		super(tile, movement);
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
		this.iterateOverITEMS();
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
