package gameModel;

public class EmptyTile extends Tile {
	
	private WinCondition winCondition;
	
	public EmptyTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord, enemyCondition);
		this.winCondition = winCondition;
	}

	@Override
	protected void updateWinCondition() {
		if (this.getItem().equals((new TreasureEntity(this.getCoord())))) {
			this.winCondition.setType(WinType.TREASURE);
		} else {
			this.winCondition.setType(WinType.WIN);
		}
	}
}
