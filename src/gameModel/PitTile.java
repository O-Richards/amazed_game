package gameModel;

public class PitTile extends Tile {

	public PitTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMobileEntity(MobileEntity entity) throws EntityPlacementException {
		if (entity.canFly()) {
			super.addMobileEntity(entity);
		} else {
			//TODO: This may fail when using the invincibility potion
			entity.kill();
		}
		
	}
	public String getSprite() {
		return "O";
	}

}
