package gameModel;

public class EnemyMobileEntity extends MobileEntity {
	private WinCondition winCondition;
	
	public EnemyMobileEntity(Coord coord) {
		super(coord);
	}
	
	public void setWinCondition(WinCondition winCondition) {
		this.winCondition = winCondition;
	}

	@Override
	public boolean pickup(UsableEntity item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSprite() {
		return "E";
	}
	
	@Override 
	public boolean kill() {
		if (super.kill()) {
			if (winCondition != null) this.winCondition.setType(WinType.WIN);
			return true;
		} 
		return false;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}
	
	
	/* Left here for reference, should be replaced by this.nextCoord()
	@Override
	public void move() {
		Coord goal = this.level.getPlayer().getCoord();
		AStarSearch astar = new AStarSearch(this.level.getMap(),this,goal);
		//hmm? pop first coord from list?
		Coord nextCoord = astar.findPath().get(0);
		this.entityMover.moveEntity(this, nextCoord);
	}
	*/
}
