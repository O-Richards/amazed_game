package GameMain;

public class HoverPotion extends UsableEntity {

	HoverPotion(Tile tile) {
		super(tile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
			hitter.setMovement(new HoverBonusMovement(hitter.getMovement()));
		}
		return Collision.MOVE;		
	}

	@Override
	public void use(Direction playerDirection, Tile[][] adjTiles) {
	}
	
	@Override
	public String getSprite() {
		return "H";
	}

}
