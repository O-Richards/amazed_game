package GameMain;

public class UnlitBombUsableEntity extends UsableEntity {
	
	UnlitBombUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction playerDirection) {
		this.entityMover.killEnemyEntities(this.getCoord(Direction.UP));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.DOWN));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.LEFT));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.RIGHT));		
		return false;
	}

	@Override
	public String getSprite() {
		return "b";
	}
	
	

}
