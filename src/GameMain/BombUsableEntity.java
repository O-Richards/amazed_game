package GameMain;

public class BombUsableEntity extends UsableEntity {
	
	private Integer startTick;
	private Integer duration = 3;
	private boolean explode = false;
	private boolean timerStarted = false;
	
	BombUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public void tick(Integer tickNum) {
		if (explode) {
			if (!timerStarted) {
				startTick = tickNum;
				timerStarted = true;
			}
			System.out.println(startTick + duration - tickNum);
			if (startTick + duration <= tickNum) {
				this.explodeBomb();
			}
		}
	}
	
	@Override
	public Boolean use(Direction playerDirection) {
		this.entityMover.placeEntity(this, this.getCoord());
		this.explode = true;
		return false;
	}

	private void explodeBomb() {
		this.entityMover.killEnemyEntities(this.getCoord(Direction.UP));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.DOWN));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.LEFT));
		this.entityMover.killEnemyEntities(this.getCoord(Direction.RIGHT));
		this.entityMover.removeEntity(this, this.getCoord());
	}
	
	@Override
	public String getSprite() {
		return "b";
	}
}
