package GameMain;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private ArrayList<UsableEntity> inventory;
	private boolean alive = true;
	private int keyCode = -1;
	// private PlayerState playerState;
	
	PlayerMobileEntity(Coord coord) {
		super(coord);
		Movement movement = new PlayerMovement(this);
		this.setMovement(movement);
		this.inventory = new ArrayList<UsableEntity>();
		// TODO Auto-generated constructor stub
	}
	
	public boolean useItem(UsableEntity item) {
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
