package GameMain;

public class UnlitBombUsableEntity extends UsableEntity {
	
	UnlitBombUsableEntity(Tile tile) {
		super(tile);
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		return Collision.MOVE;
	}

	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
		LitBombEntity litBomb = new LitBombEntity(adjTiles[1][1]);
		litBomb.getSprite();
		litBomb.explode(adjTiles);
	}

	@Override
	public String getSprite() {
		return "b";
	}
	
	

}
