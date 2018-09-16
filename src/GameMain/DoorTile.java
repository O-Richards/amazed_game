package GameMain;

public class DoorTile extends Tile {

	private static int doorCodeGenerator = 1;
	
	private static synchronized int generateDoorCode() {
		return doorCodeGenerator++;
	}

	private int doorCode;
	//Generates a door tile: 
	public DoorTile(Coord coord) {
		super(coord);
		this.doorCode = DoorTile.generateDoorCode();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.getKeyCode() == doorCode) {
			return Collision.MOVE;
		} else {
			return Collision.NOMOVE;
		}
	}

	@Override
	public String getSprite() {
		return "D";
	}
}
