package GameMain;

public class UnlitBombUsableEntity extends UsableEntity {
	
	UnlitBombUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
		LitBombEntity litBomb = new LitBombEntity(adjTiles[1][1].getCoord());
		litBomb.getSprite();
		litBomb.explode(adjTiles);
	}

	@Override
	public String getSprite() {
		return "b";
	}
	
	

}
