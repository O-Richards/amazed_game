package gameModel;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	private int keyCode = -1;
	// private PlayerState playerState;
	
	public PlayerMobileEntity(Coord coord) {
		super(coord);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
		this.inventory = new ArrayList<UsableEntity>();
	}
	
	/* (non-Javadoc)
	 * @see GameMain.Entity#setCoord(GameMain.Coord)
	 * The added functionality of the overridden method is that it updates the coords of all items in the inventory
	 */
	@Override
	public void setCoord(Coord coord) {
		super.setCoord(coord);
		for (UsableEntity item : this.inventory) {
			item.setCoord(this.getCoord());
		}
	}
	
	//Given an item finds the item in the inventory an uses it: 
	public boolean useItem(UsableEntity item) {
		 System.out.println("Player using " + item.getSprite());
		//Looks through all elements of the arrayList to find if there is an item of that type:
		for (UsableEntity inventoryItem : inventory) {
			if(item.equals(inventoryItem)) {
				Boolean hasUsesLeft = inventoryItem.use(getDirection());
				if (!hasUsesLeft) {
					inventory.remove(inventoryItem);
				}
				break;
			}
			
		}
		return false;
	}
	

	PlayerMobileEntity(Coord coord, Movement movement) {
		super(coord, movement);
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
	public Coord nextCoord() {
		return this.getCoord(this.getDirection());
	}

	@Override
	public String getSprite() {
		return "P";
	}

	@Override
	public boolean pickup(UsableEntity item) {
		inventory.add(item);
		item.applyToPlayer(this);
		return true;
	}
	
	
	public String inventoryString() {
		String out = "";
		for (UsableEntity e : inventory) {
			out += e.getSprite();
		}
		return out;
	}

	
	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		System.out.println("keyCode = " + keyCode);
	}
	
}
