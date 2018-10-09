package gameModel.mobileEntity;

import java.util.ArrayList;
import java.util.Iterator;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.BasicEntity;
import gameModel.entity.Entity;
import gameModel.entity.BasicEntity.BasicEntityBuilder;
import gameModel.usable.Usable;
import gameModel.usable.UseAction;

public class PlayerMobileEntity extends MobileEntity {
	private static final boolean DEBUG = true;
	private ArrayList<Usable> inventory;
	private int keyCode = -1;
	private int noTreasure = 0;
	// private PlayerState playerState;
	
	public PlayerMobileEntity(MobileEntityBuilder builder) {
		super(builder);
		this.inventory = new ArrayList<Usable>();
	}
	
	/* (non-Javadoc)
	 * @see GameMain.Entity#setCoord(GameMain.Coord)
	 * The added functionality of the overridden method is that it updates the coords of all items in the inventory
	 */
	@Override
	public void setCoord(Coord coord) {
		super.setCoord(coord);
	}
	
	//Given an item finds the item in the inventory an uses it: 
	public boolean use(UseAction a) {
		 System.out.println("Player using " + a);
		//Looks through all elements of the arrayList to find if there is an item of that type:
		for (Iterator<Usable> it = inventory.iterator(); it.hasNext();) {
			Usable inventoryItem = it.next();
			if (inventoryItem.use(a)) {
				it.remove();
				return true;
			}
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
	public boolean pickup(Usable item) {
		inventory.add(item);
		item.applyToPlayer(this);
		return true;
	}
	
	
	public String inventoryString() {
		String out = "";
		for (Usable e : inventory) {
			out += UseAction.toString(e.getUseAction()) + ", ";
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
	
	public static PlayerMobileEntity build(Coord c) {
		Entity baseEntity = new BasicEntity.BasicEntityBuilder("P", c)
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
	public static class PlayerMobileEntityBuilder {
		Coord c;
		String sprite;
		EntityMover entityMover;
		
		public PlayerMobileEntityBuilder(String sprite, Coord c, EntityMover entityMover) {
			this.c = c;
			this.sprite = sprite;
			this.entityMover = entityMover;
		}
		
		public PlayerMobileEntity build() {
			Entity baseEntity = new BasicEntity.BasicEntityBuilder(sprite, c)
					.withAlive(true)
					.withEntityMover(entityMover)
					.build();
			
			MobileEntityBuilder builder = new MobileEntityBuilder(baseEntity)
					.withIsPlayer(true)
					.withKilledBy(KillAction.ENEMY)
					.withMovement(new PlayerMovement());
			
			return new PlayerMobileEntity(builder);
		}
	}
}
