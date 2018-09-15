package GameMain;

public class KillerEntity extends Entity {

	private boolean killPlayer;
	private boolean killEnemies;
	
	KillerEntity(Coord coord, boolean killPlayer, boolean killEnemies) {
		super(coord);
		this.killPlayer = killPlayer;
		this.killEnemies = killEnemies;
	}
	

	@Override
	public Collision collide(MobileEntity hitter) {
		// TODO Auto-generated method stub
		return null;
	}

}
