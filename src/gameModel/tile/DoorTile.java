package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.VisType;
import gameModel.mobileEntity.MobileEntity;

public class DoorTile extends Tile {
	
	private static int doorCodeGenerator = 1;
	private int doorCode;
	private boolean open = false;

	public DoorTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
		this.doorCode = DoorTile.generateDoorCode();
	}
	public DoorTile(Tile oldParent) {
		super(oldParent);
		this.doorCode = DoorTile.generateDoorCode();
	}

	
	private static synchronized int generateDoorCode() {
		return doorCodeGenerator++;
	}
	
	@Override
	public void updateWinCondition() {
	}
	
	@Override
	public boolean traversable() {
		return this.open == true && super.traversable();
	}
	
	@Override 
	public void placeMobileEntity(MobileEntity e) throws EntityPlacementException {
		throw new EntityPlacementException("Cannot place entites on doors");
	}
	
	@Override
	public void addMobileEntity(MobileEntity e) throws EntityPlacementException {
		this.open |= (e.getKeyCode() == this.doorCode);
		if (!this.open) {
			throw new EntityPlacementException("Door is closed");
		}
		super.addMobileEntity(e);
	}

	@Override
	public VisType getVisType() {
		return VisType.DOOR;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
}
