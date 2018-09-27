package gameController;

import java.util.Observer;

import gameModel.Action;
import gameModel.Coord;
import gameModel.Direction;

public interface LevelController {
	int getNumRows();
	int getNumCols();
	void newMaze(int numRows, int numCols);
	/**
	 * @param entityGraphic The front-end observer for the new entity
	 * @param entityType The type of the new entity
	 * @param x the row to place the new entity on
	 * @param y the column to place the new entity on
	 * @return
	 */
	boolean placeEntity(Observer entityGraphic, EntityType entityType, int x, int y);
	boolean removeEntity(EntityType entityType, int x, int y);
	boolean clearTile(int x, int y);
	boolean hasLost();
	boolean hasWon();
	void setPlayerDirection(Direction direction);
	void setPlayerSpeed(int speed);
	boolean useItem(Action item);
}
