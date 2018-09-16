package GameMain;

public class ArrowUsableEntity extends UsableEntity{
	private Coord currentTargetCoord; 
	public ArrowUsableEntity(Coord coord) {
		super(coord);
	}
	@Override
	public Boolean use(Direction direction) {
		//Keeps looping until it kills something: 
		currentTargetCoord = this.getCoord();
		
		while(!this.entityMover.killEnemyEntities(currentTargetCoord)) {
			//if the next coordinate is valid 
			currentTargetCoord = currentTargetCoord.add(direction);
			//Still horrible: should change? 
			if(this.entityMover.checkSpecialTile(currentTargetCoord, new WallTile(currentTargetCoord))) {
				break;
			}
		}
		return false;
	}

	public String getSprite() {
		return ">";
	}

	
}
