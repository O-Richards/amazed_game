package GameMain;

public class ArrowUsableEntity extends UsableEntity{

	public ArrowUsableEntity(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Boolean use(Direction direction) {
		KillerEntity killEntity  =new KillerEntity(this.getCoord(direction), false, true);
		return false;
	}
	
	public String getSprite() {
		return ">";
	}

	
}
