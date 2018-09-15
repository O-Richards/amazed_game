package GameMain;

public class UnlitBombUsableEntity extends UsableEntity {
	
	UnlitBombUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction playerDirection) {
		LitBombEntity litBomb = new LitBombEntity(this.getCoord());
		litBomb.getSprite();
		litBomb.explode();
		return false;
	}

	@Override
	public String getSprite() {
		return "b";
	}
	
	

}
