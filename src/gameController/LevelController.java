package gameController;

import gameModel.Action;
import gameModel.Direction;
import gameModel.EntityType;

public interface LevelController {
	EntityType[][] getMap();
	int getNumRows();
	int getNumCols();
	void newMaze(int numRows, int numCols);
	boolean placeEntity(EntityType entityType, int x, int y);
	boolean removeEntity(EntityType entityType, int x, int y);
	boolean clearTile(int x, int y);
	boolean hasLost();
	boolean hasWon();
	void setPlayerDirection(Direction direction);
	void setPlayerSpeed(int speed);
	boolean useItem(Action item);
}
