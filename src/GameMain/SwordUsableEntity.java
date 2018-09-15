package GameMain;


public class SwordUsableEntity extends UsableEntity {
	private int noOfUses = 5; 

	SwordUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction direction) {
		Tile target;
		// Coordinates of current location: 
		this.getCoord(); 
		/*switch (direction) {
		case UP:
			target = adjTiles[0][0];
			break;
		case DOWN:
			target = adjTiles[0][2];
			break;
		case LEFT:
			target = adjTiles[1][0];
			break;
		case RIGHT:
			target = adjTiles[1][2];
			break;
		default:
			target = adjTiles[1][1];
		}*/
		target.addEntity(new KillerEntity(target.getCoord().add(direction), false, true));
		noOfUses --; 
		return noOfUses < 0;
	}
		

	public String getSprite() {
		return "S";
	}
}
