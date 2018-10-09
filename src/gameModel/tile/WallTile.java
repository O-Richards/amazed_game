package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.mobileEntity.MobileEntity;

public class WallTile extends Tile {

	public WallTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
	}

	public String getSprite() {
		return "W";
	}
	
	@Override
	public boolean traversable() {
		return false;
	}

	@Override
	protected void updateWinCondition() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addMobileEntity(MobileEntity e) throws EntityPlacementException {
		throw new EntityPlacementException("Entites cannot travverse walls");
	}
}
