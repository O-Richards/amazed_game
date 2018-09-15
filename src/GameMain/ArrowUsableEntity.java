package GameMain;

public class ArrowUsableEntity extends UsableEntity{

	public ArrowUsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Boolean use(Direction direction) {
		Tile target;
		/*switch (direction) {
		case UP:	
			target = adjTiles[1][0];
			break;
		case DOWN:
			target = adjTiles[1][2];
			break;
		case LEFT:
			target = adjTiles[0][1];
			break;
		case RIGHT:
			target = adjTiles[2][1];
			break;
		default:
			target = adjTiles[1][1];
		}*/
		target.addEntity(new KillerEntity(target.getCoord(), false, true));
		return false;
	}
	
	public String getSprite() {
		return ">";
	}

	
}
