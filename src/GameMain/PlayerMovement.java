package GameMain;

public class PlayerMovement extends EntityTrackingMovement {

	public PlayerMovement(Entity entity) {
		super(entity);
	}
	
	/**
	 * @return true if the MobileEntity is able to push another entity e.g. player pushing boulders
	 */
	public boolean pushEntity() {
		return true;
	}

	

}
