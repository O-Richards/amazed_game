package gameModel;

import java.util.ArrayList;

public class PlayerMobileEntity extends MobileEntity {
	private static final boolean DEBUG = true;
	private ArrayList<Entity> inventory;
	private int keyCode = -1;
	private int noTreasure = 0;
	// private PlayerState playerState;
	
	public PlayerMobileEntity(MobileEntityBuilder builder) {
		super(builder);
		this.inventory = new ArrayList<Entity>();
	}
	
	/* (non-Javadoc)
	 * @see GameMain.Entity#setCoord(GameMain.Coord)
	 * The added functionality of the overridden method is that it updates the coords of all items in the inventory
	 */
	@Override
	public void setCoord(Coord coord) {
		super.setCoord(coord);
		for (Entity item : this.inventory) {
			item.setCoord(this.getCoord());
		}
	}
	
	//Given an item finds the item in the inventory an uses it: 
	@Override
	public boolean use(Action a) {
		 System.out.println("Player using " + a);
		//Looks through all elements of the arrayList to find if there is an item of that type:
		for (Entity inventoryItem : inventory) {
			if (inventoryItem.use(a)) return true;
		}
		return false;
	}
	
	public int getNoTreasure() {
		return this.noTreasure;
	}

	@Override
	public Coord nextCoord() {
		Coord retVal = this.getCoord(this.getDirection());
		if (DEBUG) {
			System.out.println("Player.nextCoord: Returning " + retVal.toString());
		}
		return retVal;
	}

	@Override
	public String getSprite() {
		return "P";
	}

	@Override
	public boolean pickup(Entity item) {
		inventory.add(item);
		item.applyToPlayer(this);
		return true;
	}
	
	
	public String inventoryString() {
		String out = "";
		for (Entity e : inventory) {
			out += e.getSprite();
		}
		return out;
	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		System.out.println("keyCode = " + keyCode);
	}

	@Override
	public boolean isPlayer() {
		return true;
	}
	
	public static PlayerMobileEntity build() {
		Entity baseEntity = new BasicEntity.BasicEntityBuilder("P")
				.withAlive(true)
				.build();
		
		MobileEntityBuilder builder = new MobileEntityBuilder(baseEntity)
				.withIsPlayer(true)
				.withKilledBy(KillAction.ENEMY)
				.withMovement(new PlayerMovement());
		
		return new PlayerMobileEntity(builder);
	}

	public void incrementTreasureNo() {
		this.noTreasure++;
	}
	
}
