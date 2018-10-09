package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.mobileEntity.MobileEntity;

public class DoorTile extends Tile {
	
	private static int doorCodeGenerator = 1;
	private int doorCode;
	private boolean open = false;

	public DoorTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
		this.doorCode = DoorTile.generateDoorCode();
	}
	
	private static synchronized int generateDoorCode() {
		return doorCodeGenerator++;
	}
	
	@Override
	protected void updateWinCondition() {
	}
	
	@Override
	public boolean traversable() {
		return this.open == true && super.traversable();
	}
	
	@Override
	public void addMobileEntity(MobileEntity e) throws EntityPlacementException {
		this.open |= (e.getKeyCode() == this.doorCode);
		if (!this.open) {
			throw new EntityPlacementException("Door is closed");
		}
		super.addMobileEntity(e);
	}
	
/*
	@Override
	public Collision collideExt(MobileEntity hitter, Collision col) {
		if (hitter.getKeyCode() == doorCode || open == true) {
			open = true;
			return col;
		} else {
			return Collision.NOMOVE;
		}
	}
	*/

	@Override
	public String getSprite() {
		return "D";
	}
}
