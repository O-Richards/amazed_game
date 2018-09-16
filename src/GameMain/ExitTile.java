package GameMain;

public class ExitTile extends Tile {
	
	private WinCondition winCondition;

	public ExitTile(Coord coord, WinCondition enemyCondition, WinCondition winCondition) {
		super(coord, enemyCondition);
		this.winCondition = winCondition;
	}

	@Override
	protected void updateWinCondition() {
		// TODO Auto-generated method stub
		System.out.println(this.containsEntity(new PlayerMobileEntity(this.getCoord())));
		if (this.containsEntity(new PlayerMobileEntity(this.getCoord()))) {
			this.winCondition.setType(WinType.WIN);
		} else {
			this.winCondition.setType(WinType.EXIT);
		}
	}
	
	public String getSprite() {
		return "!";
	}
}
