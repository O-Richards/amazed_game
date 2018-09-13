package GameMain;

public class KillerEntity extends Entity {

	private boolean killPlayer;
	private boolean killEnemies;
	
	KillerEntity(Tile tile, boolean killPlayer, boolean killEnemies) {
		super(tile);
		this.killPlayer = killPlayer;
		this.killEnemies = killEnemies;
	}
	
	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO kill object in my square
		return null;
	}

}
