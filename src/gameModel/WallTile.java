package gameModel;

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
	public void addPlayer(PlayerMobileEntity e) throws EntityPlacementException {
		throw new EntityPlacementException("Player cannot travverse walls");
	}
		
	@Override
	public void addEnemy(MobileEntity e) throws EntityPlacementException {
		throw new EntityPlacementException("Enemies cannot travverse walls");
	}
}
