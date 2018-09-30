package gameModel;

public class HoverPotion extends UsableEntity {

	public HoverPotion(Coord coord) {
		super(coord);
		// TODO Auto-generated constructor stub
	}

	public void applyToPlayer(PlayerMobileEntity player) {
	//Applying decorator pattern to the player's movements
	//i.e. we wrap the player's movement with a hover bonus
	player.setMovement(new HoverBonusMovement(player.getMovement()));
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
