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
	
	//No idea how we're able to know the items name
	public boolean useItem(UsableEntity item, Tile[][] adjTiles) {
		/*if (inventory.contains(item)) {
			item.use(this.getDirection(), adjTiles);
			inventory.remove(item);
			return true;
		}*/
		//Looks through all elements of the arrayList to find if there is an item of that type:
		for (UsableEntity inventoryItem : inventory) {
			/*if(item instanceof inventoryItem.getClass().getName()) {
				
			}*/
			//Alternatively we can simply check if two sprite values match: 
			if(item.getSprite().equals(inventoryItem.getSprite())) {
				inventoryItem.use(getDirection(), adjTiles);
				if (inventoryItem.noOfUsesLeft() == 0) {
					inventory.remove(inventoryItem);
				}
				break;
			}
			
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
