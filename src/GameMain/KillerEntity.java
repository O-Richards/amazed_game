package GameMain;

public class KillerEntity extends Entity {

	private boolean killPlayer;
	private boolean killEnemies;
	private int noOfTicks; 
	KillerEntity(Coord coord, boolean killPlayer, boolean killEnemies) {
		super(coord);
		System.out.println("Killer entity placed at " + coord);
		this.killPlayer = killPlayer;
		this.killEnemies = killEnemies;
		noOfTicks = 2; 
	}
	

	@Override
	public void tick(Integer tickNum) {

		 if(noOfTicks > 0) {
			 noOfTicks --; 
		 }else{
			 this.removeFromTile(); 
		 }
	}
	@Override
	public Collision collide(MobileEntity hitter) {
		if (killPlayer) hitter.killPlayer();
		if (killEnemies) hitter.killEnemy();
		return Collision.MOVE;
	}


	@Override
	public String getSprite() {
		// TODO Auto-generated method stub
		return "X";
	}

}
