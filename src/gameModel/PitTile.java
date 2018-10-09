package gameModel;

public class PitTile extends Tile {
	private static boolean DEBUG = true;
	
	public PitTile(Coord coord, EntityMover entityMover) {
		super(coord, entityMover);
		// TODO Auto-generated constructor stub
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
	public String getSprite() {
		return "O";
	}

}
