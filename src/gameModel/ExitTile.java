package gameModel;

public class ExitTile extends Tile {
	
	private WinCondition winCondition;

	public ExitTile(Coord coord, WinCondition winCondition, EntityMover entityMover) {
		super(coord, entityMover);
		this.winCondition = winCondition;
	}

	@Override
	protected void updateWinCondition() {
		// TODO Auto-generated method stub
		if (this.getPlayer() != null) {
			this.winCondition.setType(WinType.WIN);
		} else {
			this.winCondition.setType(WinType.EXIT);
		}
	}
	
	public String getSprite() {
		return "!";
	}
}
