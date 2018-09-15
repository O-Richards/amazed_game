package GameMain;

public class DoorTile extends Tile {
	
	private static int doorCode = 1;

	public DoorTile(Coord coord) {
		super(coord);
		doorCode++;
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
