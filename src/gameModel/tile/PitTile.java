package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.KillAction;
import gameModel.entity.VisType;
import gameModel.mobileEntity.MobileEntity;

public class PitTile extends Tile {
	private static boolean DEBUG = true;
	
	public PitTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
	}
	public PitTile(Tile oldParent) {
		super(oldParent);
	}

	@Override
	public void addMobileEntity(MobileEntity entity) throws EntityPlacementException {
		if (entity.canFly()) {
			if (DEBUG) System.out.println("PitTile.addMobileEntity: calling super");
			super.addMobileEntity(entity);
		} else {
			//TODO: This may fail when using the invincibility potion
			if (DEBUG) System.out.println("PitTile.addMobileEntity: killing entity");
			entity.kill(KillAction.SUPER_KILL);
		}
		
	}
	
	@Override
	public boolean traversable() {
		return false;
	}
	@Override
	public VisType getVisType() {
		VisType baseVis = super.getVisType();
		if (baseVis == VisType.EMPTY_TILE) {
			return VisType.PIT;
		} 
		return baseVis;
	}


}
