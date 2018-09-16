package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	private boolean alive = true;
	private int keyCode = -1;
	// private PlayerState playerState;
	
	public PlayerMobileEntity(Coord coord) {
		super(coord);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
		this.inventory = new ArrayList<UsableEntity>();
		// TODO Auto-generated constructor stub
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
	public Collision collide(MobileEntity hitter, boolean recall) {
		if (recall) hitter.collide(this, false);
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

	@Override
	public boolean killPlayer() {
		if (this.canDie()) {
			this.entityMover.removeEntity(this, this.getCoord());
			this.alive = false;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean killEnemy() {
		return false;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		System.out.println("keyCode = " + keyCode);
	}
	
}
