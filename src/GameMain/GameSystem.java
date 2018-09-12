package GameMain;

import java.util.ArrayList;
import java.util.List;

public class GameSystem {
	private List<MobileEntity> mobileEntities;
	private Level level;
	
	public GameSystem() {
		this.mobileEntities = new ArrayList<MobileEntity>();
		//TODO: Setup Level
	}
	
	public void tick() {
		for (MobileEntity entity : this.mobileEntities) {
			Coord nextLoc = entity.nextCoord(level.getPlayerCoord());
			level.moveMobileEntity(entity, nextLoc);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
