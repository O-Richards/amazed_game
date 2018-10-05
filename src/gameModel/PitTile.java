package gameModel;

public class PitTile extends Tile {

	public PitTile(Coord coord, WinCondition enemyCondition, EntityMover entityMover) {
		super(coord, enemyCondition, entityMover);
		// TODO Auto-generated constructor stub
	}
<<<<<<< HEAD
	/*
	@Override
	public Collision collideExt(MobileEntity hitter, Collision col) {
		if (hitter.canFly()) {
			return col;
		} else {
			hitter.kill();
			return Collision.NOMOVE;
		} 
	}*/
=======
	
//	@Override
//	public Collision collideExt(MobileEntity hitter, Collision col) {
//		if (hitter.canFly()) {
//			return col;
//		} else {
//			hitter.kill();
//			return Collision.NOMOVE;
//		} 
//	}
>>>>>>> f658e60d5a004dd56d927e6db9f28652f1f77ebf
	
	public String getSprite() {
		return "O";
	}

	@Override
	protected void updateWinCondition() {
		
	}

}
