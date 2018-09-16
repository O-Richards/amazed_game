package GameMain;

public class UnlitBombUsableEntity extends UsableEntity {
	
	UnlitBombUsableEntity(Coord coord) {
		super(coord);
	}

	@Override
	public Boolean use(Direction playerDirection) {
		LitBombEntity litBomb = new LitBombEntity(this.getCoord());
		//litBomb.getSprite();
		//litBomb.explode();
		KillerEntity killEntity1 = new KillerEntity(this.getCoord(Direction.UP), true, true);
		KillerEntity killEntity2 = new KillerEntity(this.getCoord(Direction.DOWN), true, true);
		KillerEntity killEntity3 = new KillerEntity(this.getCoord(Direction.LEFT), true, true);
		KillerEntity killEntity4 = new KillerEntity(this.getCoord(Direction.RIGHT), true, true);
		this.entityMover.placeEntity(killEntity1, this.getCoord(Direction.UP));
		this.entityMover.placeEntity(killEntity2, this.getCoord(Direction.DOWN));
		this.entityMover.placeEntity(killEntity3, this.getCoord(Direction.LEFT));
		this.entityMover.placeEntity(killEntity4, this.getCoord(Direction.RIGHT));

		return false;
	}

	@Override
	public String getSprite() {
		return "b";
	}
	
	

}
