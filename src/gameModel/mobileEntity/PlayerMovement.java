package gameModel.mobileEntity;

import gameModel.Coord;

public class PlayerMovement extends EntityTrackingMovement {
	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	public boolean pushEntity() {
		return true;
	}
	
	@Override
	public Coord nextCoord() {
		Coord ret = super.nextCoord();
		this.setDirection(Direction.CENTRE);
		return ret;
	}
}
