package GameMain;

import java.util.ArrayList;
import java.util.List;

public class GameSystem {


	private List<MobileEntity> mobileEntities;
	private Level level;
	
	public GameSystem() {
		this.mobileEntities = new ArrayList<MobileEntity>();
		//Setup level with default size
		this.level = new Level();
		System.out.println("aMMMMMMMMMMMazing times starting...");
	}
	
	public boolean placeEntity(Entity entity, Coord coord) {
		level.addEntity(coord);
	}
	
	public void tick() {
		for (MobileEntity entity : this.mobileEntities) {
			Coord nextLoc = entity.nextCoord(level.getPlayerCoord());
			level.moveMobileEntity(entity, nextLoc);
		}
		this.level.tick();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
