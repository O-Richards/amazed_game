package gameModel.tile;

import gameModel.Coord;
import gameModel.EntityMover;
import gameModel.entity.VisType;
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
	public void updateWinCondition() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addMobileEntity(MobileEntity e) throws EntityPlacementException {
		throw new EntityPlacementException("Entites cannot travverse walls");
	}
	
	@Override
	public VisType getVisType() {
		return VisType.WALL;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
}
