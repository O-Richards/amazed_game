package GameMain;

public class HoverPotion extends UsableEntity {

	HoverPotion(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collision collide(MobileEntity hitter, boolean recall) {
		if (hitter.pickup(this)) {
			this.removeFromTile();
			//Applying decorator pattern to the player's movements
			//i.e. we wrap the player's movement with a hover bonus
			hitter.setMovement(new HoverBonusMovement(hitter.getMovement()));
		}
		return Collision.MOVE;
	}

	@Override
	public Boolean use(Direction playerDirection) {
		return false;
	}
	
	@Override
	public String getSprite() {
		return "H";
	}

}
