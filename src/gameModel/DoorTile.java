package gameModel;

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
